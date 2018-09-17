package com.joh.bhms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PATIENT_OPERATIONS")
public class PatientOperation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "I_PATIENT_OPERATION")
	private Integer id;

	@ManyToOne()
	@JoinColumn(name = "I_PATIENT_VISIT", nullable = false)
	private PatientVisit patientVisit;

	@Column(name = "OPERATION")
	private String operaion;

	@Column(name = "PRICE")
	private Double price;

	@Column(name = "NOTE")
	private String note;

}
