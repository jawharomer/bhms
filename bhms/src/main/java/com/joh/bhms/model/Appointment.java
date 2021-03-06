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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joh.bhms.commons.CusTimeDeSerializer;

@Entity
@Table(name = "BHMS_APPOINTMENTS")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "I_APPOINTMENT")
	private Integer id;

	@NotBlank(message = "full name is blank")
	@Column(name = "FULL_NAME")
	private String fullName;

	@NotBlank(message = "phone is blank")
	@Column(name = "PHONE")
	private String phone;

	@Column(name = "VISIT_CASE")
	private String visitCase;

	@NotNull(message = "Date is  null")
	@Column(name = "APPOINTMENT_DATE")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	@NotNull(message = "Time is  null")
	@Column(name = "APPOINTMENT_TIME")
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm")
	@JsonDeserialize(using = CusTimeDeSerializer.class)
	private Date time;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getVisitCase() {
		return visitCase;
	}

	public void setVisitCase(String visitCase) {
		this.visitCase = visitCase;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", fullName=" + fullName + ", phone=" + phone + ", visitCase=" + visitCase
				+ ", date=" + date + ", time=" + time + "]";
	}

}
