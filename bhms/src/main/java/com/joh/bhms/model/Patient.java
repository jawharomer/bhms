package com.joh.bhms.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.joh.bhms.validator.PatientValidation;

@Entity
@Table(name = "PATIENTS")
public class Patient {

	
	@Column(name = "I_PATIENT")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotBlank(message = "full name is balnk")
	@Column(name = "FULL_NAME", nullable = false, unique = true)
	private String fullName;

	@NotBlank(message = "arabic full name is balnk")
	@Column(name = "ARABIC_FULL_NAME", nullable = false, unique = true)
	private String arabicFullName;

	@Column(name = "PHONE")
	private String phone;

	@NotNull(message = "birthDate is blank")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "BIRTH_DATE")
	private Date birthDate;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "MARITAL_STATUS")
	private String maritalStatus;

	// Male=1
	// Female=0
	@NotNull(groups = { PatientValidation.Insert.class }, message = "gender is null")
	@Min(groups = { PatientValidation.Insert.class }, value = 0)
	@Max(groups = { PatientValidation.Insert.class }, value = 1)
	@Column(name = "GENDER")
	private Integer gender;

	@Column(name = "ENROLLMENT_TIME", insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date time;

	@Column(name = "JOB")
	private String job;

	@Column(name = "SMOKING")
	private boolean smoking;

	@Column(name = "DRINKING")
	private boolean drinking;

	@Valid()
	@NotNull(groups = { PatientValidation.Insert.class }, message = "visit reference is null")
	@ManyToOne()
	@JoinColumn(name = "I_VISIT_REFERENCE")
	private VisitReference visitReference;

	@ElementCollection
	@CollectionTable(name = "PATIENT_ALLERGIES", joinColumns = @JoinColumn(name = "I_PATIENT"))
	@Column(name = "ALLERGY")
	private List<String> allergies = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "PATIENT_CHRNOIC_DISEASES", joinColumns = @JoinColumn(name = "I_PATIENT"))
	@Column(name = "CHRONIC_DISEASE")
	private List<String> chronicDiseases = new ArrayList<>();;

	@ElementCollection
	@CollectionTable(name = "PATIENT_HISTORY_OPERATIONS", joinColumns = @JoinColumn(name = "I_PATIENT"))
	@Column(name = "HISTORY_OPERATION")
	private List<String> historyOperations = new ArrayList<>();;

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

	public String getArabicFullName() {
		return arabicFullName;
	}

	public void setArabicFullName(String arabicFullName) {
		this.arabicFullName = arabicFullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public VisitReference getVisitReference() {
		return visitReference;
	}

	public void setVisitReference(VisitReference visitReference) {
		this.visitReference = visitReference;
	}

	public List<String> getAllergies() {
		return allergies;
	}

	public void setAllergies(List<String> allergies) {
		this.allergies = allergies;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public boolean isSmoking() {
		return smoking;
	}

	public void setSmoking(boolean smoking) {
		this.smoking = smoking;
	}

	public boolean isDrinking() {
		return drinking;
	}

	public void setDrinking(boolean drinking) {
		this.drinking = drinking;
	}

	public List<String> getChronicDiseases() {
		return chronicDiseases;
	}

	public void setChronicDiseases(List<String> chronicDiseases) {
		this.chronicDiseases = chronicDiseases;
	}

	public List<String> getHistoryOperations() {
		return historyOperations;
	}

	public void setHistoryOperations(List<String> historyOperations) {
		this.historyOperations = historyOperations;
	}
	
	

	@Override
	public String toString() {
		return "Patient [id=" + id + ", fullName=" + fullName + ", arabicFullName=" + arabicFullName + ", phone="
				+ phone + ", birthDate=" + birthDate + ", address=" + address + ", maritalStatus=" + maritalStatus
				+ ", gender=" + gender + ", time=" + time + ", job=" + job + ", smoking=" + smoking + ", drinking="
				+ drinking + ", visitReference=" + visitReference + ", allergies=" + allergies + ", chronicDiseases="
				+ chronicDiseases + ", historyOperations=" + historyOperations + "]";
	}

}
