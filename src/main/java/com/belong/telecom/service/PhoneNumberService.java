package com.belong.telecom.service;

import java.util.List;

import com.belong.telecom.entity.PhoneNumber;
import com.belong.telecom.exception.PhoneNumberNotFoundException;

public interface PhoneNumberService {
	
	public List<PhoneNumber> findAllPhoneNumbers();
	public PhoneNumber save(final PhoneNumber phoneNumber);
	public PhoneNumber activatePhoneNumber(final String phoneNumber, final boolean isActivated) throws PhoneNumberNotFoundException;

}
