package com.joh.bhms.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "B_ORDER_DETAILS")
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "I_ORDER_DETAIL")
	private Integer id;

	@NotNull(message = "product is null")
	@Valid()
	@ManyToOne()
	@JoinColumn(name = "I_PRODUCT", nullable = false)
	protected Product product;

	@NotNull(message = "quantity is null")
	@Column(name = "QUANTITY")
	private Integer quantity;

	@NotNull(message = "payment amount is null")
	@Column(name = "paymentAmount")
	private Double paymentAmount;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "PRODUCTION_DATE")
	@Temporal(TemporalType.DATE)
	private Date productionDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "EXPIRATION_DATE")
	@Temporal(TemporalType.DATE)
	private Date expirationDate;

	@Column(name = "SOLD_AMOUNT")
	private Integer soldAmount;

	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "I_ORDER", nullable = false)
	private Order order;

	@PrePersist
	public void prePersis() {
		if (this.soldAmount == null) {
			this.soldAmount = 0;
		}
	}
	
	///

	
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

	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Integer getSoldAmount() {
		return soldAmount;
	}

	public void setSoldAmount(Integer soldAmount) {
		this.soldAmount = soldAmount;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", product=" + product + ", quantity=" + quantity + ", paymentAmount="
				+ paymentAmount + ", productionDate=" + productionDate + ", expirationDate=" + expirationDate
				+ ", soldAmount=" + soldAmount + "]";
	}

}
