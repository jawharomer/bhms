package com.joh.bhms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.joh.bhms.validator.PatientValidation;

@Entity
@Table(name = "VISIT_REFERENCES")
public class VisitReference {

	@NotNull(groups = { PatientValidation.Insert.class },message="visit reference is null")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "I_VISIT_REFERENCE")
	private Integer id;

	@Column(name = "REFERENCE")
	private String reference;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	@Override
	public String toString() {
		return "VisitReference [id=" + id + ", reference=" + reference + "]";
	}

}
