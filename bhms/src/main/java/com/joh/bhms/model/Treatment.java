package com.joh.bhms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PATIENT_TREATMENTS")
public class Treatment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "I_TREATMENT")
	private Integer id;

	@Column(name = "TREATMENT_NAME")
	private String name;

	@Column(name = "NOTE")
	private String note;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Treatment [id=" + id + ", name=" + name + ", note=" + note + "]";
	}

}
