package com.belong.telecom.entity;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({ @NamedQuery(name = "Customer.findByName",
query = "select customer from Customer customer where customer.name = ?1"),
		@NamedQuery(name = "Customer.getCustomerPhoneNumber",
		query = "select customer.phoneNumbers from Customer customer where customer.name = ?1")	})

public class Customer {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String addressLine1;
	private String addressLine2;
	
	public Customer() {
		
	}
	
	public Customer(final int id, final String name, final String addressLine1, final String addressLine2, final List<PhoneNumber> phoneNumbers) {
		super();
		this.id = id;
		this.name = name;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.phoneNumbers = phoneNumbers;
	}
	@OneToMany(fetch= FetchType.LAZY, cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
	private List<PhoneNumber> phoneNumbers;
	
	public int getId() {
		return id;
	}
	public void setId(final int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(final String name) {
		this.name = name;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(final String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(final String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(final List<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	
	
}
