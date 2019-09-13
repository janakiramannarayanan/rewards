package com.group.rewards.code.sample.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="TRANSACTION")
public class Transaction extends EntityCreateUpdate {

	@Id
	@Column(name="TRAN_ID")
	private String id;

	@ManyToOne
	@JoinColumn(name = "CUST_ID")
	private Customer customer;

	@Column(name="ACS_TRANS_ID")
	private String transactionId;

	@Column(name="MERCHANT_NAME")
	private String merchantName;

	@Column(name="AMOUNT")
	private Float transactionAmount;

	@Column(name="CURRENCY_CODE")
	private String currencyCode;

	@Column(name="STATUS")
	private String status;

	public String getId() {
		return id;
	}

	@Override
	protected void setId(String id) {
		this.id = id;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public Float getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Float transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
