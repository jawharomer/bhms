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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "VISIT_PAYMENTS")
public class VisitPayment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "I_VISIT_PAYMENT")
	private Integer id;

	// Do not put in toString()
	@ManyToOne()
	@JoinColumn(name = "I_PATIENT_VISIT", nullable = false)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private PatientVisit patientVisit;

	@NotNull(message = "payment amount is null")
	@Column(name = "PAYMENT_AMOUNT")
	private Double paymentAmount;

	@Column(name = "NOTE")
	private String note;

	@Column(name = "PAYMENT_TIME", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date time;

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "VISIT_PAYMENT_PATIENT_DOCTORS", joinColumns = {
			@JoinColumn(name = "I_VISIT_PAYMENT") }, inverseJoinColumns = { @JoinColumn(name = "I_PATIENT_DOCTOR") })
	private List<PatientDoctor> patientDoctors = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPatientVisit(PatientVisit patientVisit) {
		this.patientVisit = patientVisit;
	}

	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public PatientVisit getPatientVisit() {
		return patientVisit;
	}

	public List<PatientDoctor> getPatientDoctors() {
		return patientDoctors;
	}

	public void setPatientDoctors(List<PatientDoctor> patientDoctors) {
		this.patientDoctors = patientDoctors;
	}

	@Override
	public String toString() {
		return "VisitPayment [id=" + id + ", paymentAmount=" + paymentAmount + ", note=" + note + ", time=" + time
				+ "]";
	}

}
