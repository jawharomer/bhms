package com.joh.bhms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "B_VENDORS")
public class Vendor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "I_VENDOR")
	private Integer id;

	@NotBlank(message = "Full Name is blank")
	@Column(name = "VENDOR_NAME")
	private String name;

	@NotBlank(message = "Phone is blank")
	@Column(name = "PHONE")
	private String phone;

	@NotBlank(message = "Address is blank")
	@Column(name = "ADDRESS")
	private String address;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return "Vendor [id=" + id + ", name=" + name + ", phone=" + phone + ", address=" + address + ", note=" + note
				+ "]";
	}

}
