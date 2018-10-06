package com.joh.bhms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "VISIT_PAYMENTS")
public class VisitPayment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "I_VISIT_PAYMENT")
	private Integer id;

	// Do not put in toString()
	// add @JsonIgnore on getter
	@ManyToOne()
	@JoinColumn(name = "I_PATIENT_VISIT", nullable = false)
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

	@JsonIgnore
	public PatientVisit getPatientVisit() {
		return patientVisit;
	}

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

	@Override
	public String toString() {
		return "VisitPayment [id=" + id + ", paymentAmount=" + paymentAmount + ", note=" + note + ", time=" + time
				+ "]";
	}

}
