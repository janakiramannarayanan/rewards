package com.group.rewards.code.sample.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMER")
public class Customer extends EntityCreateUpdate {
	@Id
	@Column(name="CUST_ID")
	private String id;

	@Column(name="NAME")
	private String customerName;

	@Column(name="PRIMARY_PHONE")
	private String customerPrimaryPhone;

	@Column(name="ALTERNATE_PHONE")
	private String customerAlternatePhone;

	@Column(name="EMAIL")
	private String customerEmail;

	public String getId() {
		return id;
	}

	@Override
	protected void setId(String id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPrimaryPhone() {
		return customerPrimaryPhone;
	}

	public void setCustomerPrimaryPhone(String customerPrimaryPhone) {
		this.customerPrimaryPhone = customerPrimaryPhone;
	}

	public String getCustomerAlternatePhone() {
		return customerAlternatePhone;
	}

	public void setCustomerAlternatePhone(String customerAlternatePhone) {
		this.customerAlternatePhone = customerAlternatePhone;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
}
