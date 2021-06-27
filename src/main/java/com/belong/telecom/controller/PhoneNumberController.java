package com.belong.telecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.belong.telecom.entity.PhoneNumber;
import com.belong.telecom.exception.PhoneNumberNotFoundException;
import com.belong.telecom.service.PhoneNumberService;

@RestController
public class PhoneNumberController {

	@Autowired
	private PhoneNumberService phoneNumberService;
	
	@GetMapping("/phoneNumbers")
	public ResponseEntity<List<PhoneNumber>> getAllPhoneNumbers(){
		List<PhoneNumber> phoneNumbers = phoneNumberService.findAllPhoneNumbers();
		return new ResponseEntity<List<PhoneNumber>>(phoneNumbers, HttpStatus.OK);
	}
	
	@PostMapping("/phoneNumbers/activate")
	public ResponseEntity<PhoneNumber> activatePhoneNumber(@RequestBody PhoneNumber phone ) throws PhoneNumberNotFoundException{		
		PhoneNumber phoneNumber = phoneNumberService.activatePhoneNumber(phone.getPhoneNumber(), true);
		return new ResponseEntity<PhoneNumber>(phoneNumber, HttpStatus.CREATED);
	}
}
