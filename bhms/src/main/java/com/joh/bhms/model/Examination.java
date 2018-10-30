package com.joh.bhms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EXAMINATIONS")
public class Examination {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "I_EXAMINATION")
	private Integer id;

	@Column(name = "EXAMINATION_NAME")
	private String name;

	@Column(name = "NORMAL")
	private String normal;

	@Column(name = "RESULT")
	private String result;

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

	public String getNormal() {
		return normal;
	}

	public void setNormal(String normal) {
		this.normal = normal;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Examination [id=" + id + ", name=" + name + ", normal=" + normal + ", result=" + result + ", note="
				+ note + "]";
	}

}
