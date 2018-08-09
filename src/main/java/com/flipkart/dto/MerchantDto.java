package com.flipkart.dto;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.flipkart.model.MerchantAddress;

public class MerchantDto {
	private int merchantId;
	@NotNull(message="Merchant Name cannot be Blank")
	private String merchantName;
	@Column(unique = true)
	@Email(message="Enter Unique Email Id Ex: abc@xyz.pqr")
	@NotNull(message="Merchant Email cannot be Blank")
	private String merchantEmail;
	@NotNull(message="Merchant Phone No. cannot be Blank")
	private long merchantphoneNo;
	@NotNull(message="Merchant Password cannot be Blank")
	private String merchantPassword;
	@Transient
	@NotNull(message="Merchant Password cannot be Blank")
	private String merchantConfirmPassword;

	@OneToOne(cascade = CascadeType.ALL)
	private MerchantAddress merchantAddress;
	
	public int getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getMerchantEmail() {
		return merchantEmail;
	}
	public void setMerchantEmail(String merchantEmail) {
		this.merchantEmail = merchantEmail;
	}
	public long getMerchantphoneNo() {
		return merchantphoneNo;
	}
	public void setMerchantphoneNo(long merchantphoneNo) {
		this.merchantphoneNo = merchantphoneNo;
	}
	public String getMerchantPassword() {
		return merchantPassword;
	}
	public void setMerchantPassword(String merchantPassword) {
		this.merchantPassword = merchantPassword;
	}
	public String getMerchantConfirmPassword() {
		return merchantConfirmPassword;
	}
	public void setMerchantConfirmPassword(String merchantConfirmPassword) {
		this.merchantConfirmPassword = merchantConfirmPassword;
	}
	
	public MerchantAddress getMerchantAddress() {
		return merchantAddress;
	}
	public void setMerchantAddress(MerchantAddress merchantAddress) {
		this.merchantAddress = merchantAddress;
	}
	
	
}
