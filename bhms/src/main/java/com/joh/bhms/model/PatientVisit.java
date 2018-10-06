package com.joh.bhms.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;
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

	@ManyToMany()
	@JoinTable(name = "PATIENT_VISIT_DOCTORS", joinColumns = @JoinColumn(name = "I_VISIT"), inverseJoinColumns = @JoinColumn(name = "I_DOCTOR"))
	private List<Doctor> doctors = new ArrayList<>();

	@OneToMany(mappedBy = "patientVisit")
	private List<PatientOperation> patientOperations = new ArrayList<>();

	@OneToMany(mappedBy = "patientVisit")
	private List<VisitPayment> visitPayments = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "I_PATIENT_VISIT")
	private List<PatientProductUsed> patientProductUseds = new ArrayList<>();

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "PATIENT_VISIT_ATTACHED_FILES", joinColumns = @JoinColumn(name = "I_PATIENT_VISIT"), inverseJoinColumns = @JoinColumn(name = "I_ATTACHED_FILE"))
	private List<AttachedFile> attachedFiles = new ArrayList<>();

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

	public List<AttachedFile> getAttachedFiles() {
		return attachedFiles;
	}

	public void setAttachedFiles(List<AttachedFile> attachedFiles) {
		this.attachedFiles = attachedFiles;
	}

	public List<PatientProductUsed> getPatientProductUseds() {
		return patientProductUseds;
	}

	public void setPatientProductUseds(List<PatientProductUsed> patientProductUseds) {
		this.patientProductUseds = patientProductUseds;
	}

	@Override
	public String toString() {
		return "PatientVisit [id=" + id + ", patient=" + patient + ", time=" + time + ", note=" + note + ", doctors="
				+ doctors + ", patientOperations=" + patientOperations + ", visitPayments=" + visitPayments
				+ ", patientProductUseds=" + patientProductUseds + ", attachedFiles=" + attachedFiles + "]";
	}

}
