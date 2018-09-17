package com.joh.bhms.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "PATIENT_VISITS")
public class PatientVisit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "I_PATIENT_VISIT")
	private Integer id;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "VISIT_DATE", updatable = false)
	private Date visitDate;

	@Column(name = "NOTE")
	private String note;

	@Column(name = "PAYMENT_AMOUNT")
	private Double paymentAmount;

	@OneToMany()
	@JoinTable(name = "PATIENT_VISIT_DOCTORS", joinColumns = @JoinColumn(name = "I_VISIT"), inverseJoinColumns = @JoinColumn(name = "I_DOCTOR"))
	private List<Doctor> doctor;

	@OneToMany(mappedBy = "patientVisit")
	private List<PatientOperation> patientOperations;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public List<Doctor> getDoctor() {
		return doctor;
	}

	public void setDoctor(List<Doctor> doctor) {
		this.doctor = doctor;
	}

	public List<PatientOperation> getPatientOperations() {
		return patientOperations;
	}

	public void setPatientOperations(List<PatientOperation> patientOperations) {
		this.patientOperations = patientOperations;
	}

	@Override
	public String toString() {
		return "PatientVisit [id=" + id + ", visitDate=" + visitDate + ", note=" + note + ", paymentAmount="
				+ paymentAmount + ", doctor=" + doctor + ", patientOperations=" + patientOperations + "]";
	}

}
