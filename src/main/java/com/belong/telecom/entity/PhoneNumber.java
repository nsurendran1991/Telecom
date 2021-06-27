package com.belong.telecom.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="phone_number")
@NamedQuery(name = "Customer.findByPhoneNumber",
query = "select phone from PhoneNumber phone where phone.phoneNumber = ?1")
public class PhoneNumber {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String phoneNumber;
	private String country;
	private String countryCode;
	private boolean isActivated;
	
	public PhoneNumber() {
		
	}
	
	public PhoneNumber(final int id, final String phoneNumber, final String country, final String countryCode, final boolean isActivated) {
		this.isActivated =isActivated;
		this.country = country;
		this.countryCode = countryCode;
		this.phoneNumber = phoneNumber;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public boolean isActivated() {
		return isActivated;
	}
	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}
}
