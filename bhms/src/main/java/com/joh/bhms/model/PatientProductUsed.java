package com.joh.bhms.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PATIENT_PRODUCT_USED")
public class PatientProductUsed {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "I_PATIENT_PRODUCT_USED")
	private Integer id;

	@ManyToOne()
	@JoinColumn(name = "I_PRODUCT")
	private Product product;

	@Column(name = "QUANTITY")
	private Integer quantity;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "PDU_ORDER_DETAILS", joinColumns = @JoinColumn(name = "I_PATIENT_PRODUCT_USED"), inverseJoinColumns = @JoinColumn(name = "I_ORDER_DETAIL"))
	public List<OrderDetail> orderDetailIds = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<OrderDetail> getOrderDetailIds() {
		return orderDetailIds;
	}

	public void setOrderDetailIds(List<OrderDetail> orderDetailIds) {
		this.orderDetailIds = orderDetailIds;
	}

	@Override
	public String toString() {
		return "PatientProductUsed [id=" + id + ", product=" + product + ", quantity=" + quantity + ", orderDetailIds="
				+ orderDetailIds + "]";
	}

}
