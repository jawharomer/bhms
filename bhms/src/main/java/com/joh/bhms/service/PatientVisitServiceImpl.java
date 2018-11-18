package com.joh.bhms.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.joh.bhms.dao.OperationDAO;
import com.joh.bhms.dao.OrderDetailDAO;
import com.joh.bhms.dao.PatientOperationDAO;
import com.joh.bhms.dao.PatientVisitDAO;
import com.joh.bhms.dao.VisitPaymentDAO;
import com.joh.bhms.exception.CusDataIntegrityViolationException;
import com.joh.bhms.model.AttachedFile;
import com.joh.bhms.model.Operation;
import com.joh.bhms.model.OrderDetail;
import com.joh.bhms.model.PatientOperation;
import com.joh.bhms.model.PatientProductUsed;
import com.joh.bhms.model.PatientVisit;
import com.joh.bhms.model.VisitPayment;

@Service
public class PatientVisitServiceImpl implements PatientVisitService {
	@Autowired
	private PatientVisitDAO patientVisitDAO;

	@Autowired
	private PatientOperationDAO patientOperationDAO;

	@Autowired
	private AttachedFileService attachedFileService;

	@Autowired
	private OrderDetailDAO orderDetailDAO;

	@Autowired
	private OperationDAO operationDAO;

	@Autowired
	private VisitPaymentDAO visitPaymentDAO;

	@Override
	public PatientVisit findOne(int id) {
		return patientVisitDAO.findOne(id);
	}

	@Override
	public Iterable<PatientVisit> findAllByTimeBetween(Date from, Date to) {
		return patientVisitDAO.findAllByTimeBetween(from, to);
	}

	@Override
	public Iterable<PatientVisit> findAllByNextSessionLessThanEqual(Date to) {
		return patientVisitDAO.findAllByNextSessionLessThanEqual(to);
	}

	@Transactional
	@Override
	public PatientVisit save(PatientVisit patientVisit) {
		final PatientVisit savePV = patientVisitDAO.save(patientVisit);
		patientVisit.getPatientOperations().stream().forEach(e -> {
			e.setPatientVisit(savePV);
			patientOperationDAO.save(e);
		});
		return patientVisit;
	}

	@Transactional
	@Override
	public PatientVisit update(PatientVisit patientVisit) {
		PatientVisit oldPatientVisit = patientVisitDAO.findOne(patientVisit.getId());
		if (oldPatientVisit == null) {
			throw new EntityNotFoundException();
		}

		// Keep All Doctors from All Today Payments
		List<VisitPayment> visitPayments = visitPaymentDAO.findAllAtToday(patientVisit.getId());
		visitPayments.stream().forEach(e -> {
			e.setPatientDoctors(new ArrayList<>(patientVisit.getPatientDoctors()));
			visitPaymentDAO.save(e);
		});

		// Remove old PatientOperations
		oldPatientVisit.getPatientOperations().stream().forEach(e -> {
			patientOperationDAO.delete(e.getId());
		});

		// Prevent update AttachedFiles
		patientVisit.setAttachedFiles(patientVisitDAO.findOne(patientVisit.getId()).getAttachedFiles());

		// Prevent update direct patientProductUsed
		patientVisit.setPatientProductUseds(patientVisitDAO.findOne(patientVisit.getId()).getPatientProductUseds());

		final PatientVisit savePV = patientVisitDAO.save(patientVisit);

		patientVisit.getPatientOperations().stream().forEach(e -> {
			e.setPatientVisit(savePV);
			patientOperationDAO.save(e);

			if (operationDAO.findFirstByName(e.getOperation()) == null) {
				Operation operation = new Operation();
				operation.setName(e.getOperation());
				operation.setPrice(e.getPrice());
				operationDAO.save(operation);
			}

		});
		return patientVisit;
	}

	@Transactional
	@Override
	public void addAttachedFile(int id, MultipartFile file) throws IOException {
		AttachedFile attachedFile = attachedFileService.save(file);
		PatientVisit patientVisit = patientVisitDAO.findOne(id);
		patientVisit.getAttachedFiles().add(attachedFile);
		patientVisitDAO.save(patientVisit);
	}

	@Transactional
	@Override
	public void deleteAttachedFile(int id, int attachedFileId) {

		AttachedFile attachedFile = attachedFileService.findOne(attachedFileId);

		PatientVisit patientVisit = patientVisitDAO.findOne(id);
		patientVisit.getAttachedFiles().remove(attachedFile);
		patientVisitDAO.save(patientVisit);
		attachedFileService.delete(attachedFile.getId());
	}

	@Transactional
	@Override
	public void addPatientProductUsed(int id, PatientProductUsed patientProductUsed) {
		PatientVisit patientVisit = patientVisitDAO.findOne(id);

		double cost = 0;
		for (int i = 0; i < patientProductUsed.getQuantity(); i++) {

			OrderDetail orderDetail = orderDetailDAO.findByProductCode(patientProductUsed.getProduct().getCode());

			if (orderDetail == null) {
				throw new CusDataIntegrityViolationException(
						"out of stock with code=" + patientProductUsed.getProduct().getCode());
			}

			cost += (orderDetail.getPaymentAmount() / orderDetail.getQuantity());

			orderDetailDAO.stockDown(orderDetail.getId());
			patientProductUsed.getOrderDetailIds().add(orderDetail);
		}

		Double roundredCost = BigDecimal.valueOf(cost).setScale(3, RoundingMode.HALF_UP).doubleValue();
		patientProductUsed.setCost(roundredCost);

		patientVisit.getPatientProductUseds().add(patientProductUsed);

		patientVisitDAO.save(patientVisit);
	}

	@Transactional
	@Override
	public void deletePatientProductUsed(int id, int productUsedId) {
		PatientVisit patientVisit = patientVisitDAO.findOne(id);

		PatientProductUsed patientProductUsed = patientVisit.getPatientProductUseds().stream()
				.filter(e -> e.getId() == productUsedId).findFirst().get();

		patientProductUsed.getOrderDetailIds().stream().forEach(e -> orderDetailDAO.stockUp(e.getId()));

		patientVisit.getPatientProductUseds().remove(patientProductUsed);

		patientVisitDAO.save(patientVisit);

	}

	@Override
	public List<PatientVisit> findAllByPatientId(int id) {
		return patientVisitDAO.findAllByPatientId(id);
	}

}
