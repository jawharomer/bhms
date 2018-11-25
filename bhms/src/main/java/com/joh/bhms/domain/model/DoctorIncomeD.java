package com.joh.bhms.domain.model;

public class DoctorIncomeD {
	private String doctorId;
	private String fullName;
	private double income;

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	@Override
	public String toString() {
		return "DoctorIncomeD [doctorId=" + doctorId + ", fullName=" + fullName + ", income=" + income + "]";
	}

}
