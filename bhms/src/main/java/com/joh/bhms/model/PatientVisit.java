package com.joh.bhms.model;

import java.util.ArrayList;
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
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.joh.bhms.validator.PatientVisitValidation;

@Entity
@Table(name = "PATIENT_VISITS")
public class PatientVisit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "I_PATIENT_VISIT")
	private Integer id;

	@Valid
	@NotNull(groups = { PatientVisitValidation.Insert.class })
	@ManyToOne()
	@JoinColumn(name = "I_PATIENT", nullable = false)
	private Patient patient;

	@Column(name = "VISIT_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date time;

	@Column(name = "NOTE")
	private String note;

	@OneToMany()
	@JoinTable(name = "PATIENT_VISIT_DOCTORS", joinColumns = @JoinColumn(name = "I_VISIT"), inverseJoinColumns = @JoinColumn(name = "I_DOCTOR"))
	private List<Doctor> doctors = new ArrayList<>();

	@OneToMany(mappedBy = "patientVisit")
	private List<PatientOperation> patientOperations = new ArrayList<>();

	@OneToMany(mappedBy = "patientVisit")
	private List<VisitPayment> visitPayments = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}

	public List<PatientOperation> getPatientOperations() {
		return patientOperations;
	}

	public void setPatientOperations(List<PatientOperation> patientOperations) {
		this.patientOperations = patientOperations;
	}

	public List<VisitPayment> getVisitPayments() {
		return visitPayments;
	}

	public void setVisitPayments(List<VisitPayment> visitPayments) {
		this.visitPayments = visitPayments;
	}

	@Override
	public String toString() {
		return "PatientVisit [id=" + id + ", patient=" + patient + ", time=" + time + ", note=" + note + ", doctors="
				+ doctors + ", patientOperations=" + patientOperations + ", visitPayments=" + visitPayments + "]";
	}

}
