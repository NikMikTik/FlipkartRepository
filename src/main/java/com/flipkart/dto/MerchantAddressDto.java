package com.flipkart.dto;

import javax.validation.constraints.NotNull;

public class MerchantAddressDto {
	private int addressId;
	@NotNull(message="Adsress cannot be Blank")
	private String name;
	@NotNull(message="Street cannot be Blank")
	private String streetAddress;
	@NotNull(message="City cannot be Blank")
	private String city;
	@NotNull(message="State cannot be Blank")
	private String state;
	@NotNull(message="zipcode cannot be Blank")
	private long zipCode;
	@NotNull(message="Country cannot be Blank")
	private String country;
	
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public long getZipCode() {
		return zipCode;
	}
	public void setZipCode(long zipCode) {
		this.zipCode = zipCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	

}
