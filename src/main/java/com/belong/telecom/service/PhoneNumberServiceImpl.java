package com.belong.telecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belong.telecom.entity.PhoneNumber;
import com.belong.telecom.exception.PhoneNumberNotFoundException;
import com.belong.telecom.repository.PhoneNumberRepository;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService{
	
	@Autowired
	private PhoneNumberRepository phoneNumberRepository;
	
	public List<PhoneNumber> findAllPhoneNumbers(){
		return phoneNumberRepository.findAll();
	}
	
	public PhoneNumber save(final PhoneNumber phoneNumber) {
		return phoneNumberRepository.save(phoneNumber);
	}
	
	public PhoneNumber activatePhoneNumber(final String phoneNumber, final boolean isActivated) throws PhoneNumberNotFoundException {
		PhoneNumber phone= phoneNumberRepository.findByPhoneNumber(phoneNumber);
		if(null != phone) {
			phone.setActivated(isActivated);
			return phoneNumberRepository.save(phone);
			
		}else {
			throw new PhoneNumberNotFoundException("Phone number is not found");
		}
		
	}
	

}
