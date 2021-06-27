package com.belong.telecom.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.belong.telecom.entity.PhoneNumber;
import com.belong.telecom.repository.PhoneNumberRepository;
import com.belong.telecom.service.PhoneNumberService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureJsonTesters
@WebMvcTest(PhoneNumberController.class)
public class PhoneNumberControllerTests {

	@Autowired
    private MockMvc mvc;

	@MockBean
    private PhoneNumberService service;
	
	@MockBean
    private PhoneNumberRepository repository;
	
	 @Autowired
	 private JacksonTester<List<PhoneNumber>> phoneNumberResponse;
	 
	 @Test
	    public void testGetAllPhoneNumbers() throws Exception {
	        // given
			List<PhoneNumber> phoneNumberList = new ArrayList<>();		
			PhoneNumber phoneNumber1 = new PhoneNumber(1, "0422465122", "Australia", "+61", false);    			
	    	phoneNumberList.add(phoneNumber1);
	    	PhoneNumber phoneNumber2 = new PhoneNumber(1, "9746791294", "India", "+91", false);    			
	    	phoneNumberList.add(phoneNumber2);
		    
		    
	        // when
	    	Mockito.when(repository.findAll())
		      .thenReturn(phoneNumberList);
		    Mockito.when(service.findAllPhoneNumbers())
		      .thenReturn(phoneNumberList);
	        MockHttpServletResponse response = mvc.perform(
	                get("/phoneNumbers")
	                        .accept(MediaType.APPLICATION_JSON))
	                .andReturn().getResponse();

	        // then
	        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	        assertThat(response.getContentAsString()).isEqualTo(
	        		phoneNumberResponse.write(phoneNumberList).getJson()
	        );
	    }
	 
	 @Test
	    public void testActivatePhoneNumber() throws Exception {
	        // given			
			PhoneNumber phoneNumber1 = new PhoneNumber(1, "0422465122", "Australia", "+61", false);    		
			PhoneNumber phoneNumber2 = new PhoneNumber(1, "0422465122", "Australia", "+61", true);   
		    
	        // when
	    	Mockito.when(repository.save(phoneNumber1))
		      .thenReturn(phoneNumber2);
		    Mockito.when(service.activatePhoneNumber(phoneNumber2.getPhoneNumber(), true))
		      .thenReturn(phoneNumber2);
	        MockHttpServletResponse response = ((ResultActions) ((MockHttpServletRequestBuilder) mvc.perform(
	                post("/phoneNumbers/activate").content(mapToJson(phoneNumber1))))
	                        .accept(MediaType.APPLICATION_JSON))
	                .andReturn().getResponse();

	        // then
	        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	        assertThat(response.getContentAsString()).isEqualTo(
	        		mapToJson(phoneNumber2));
	    }
	 
	 protected String mapToJson(Object obj) throws JsonProcessingException {
	      ObjectMapper objectMapper = new ObjectMapper();
	      return objectMapper.writeValueAsString(obj);
	   }
	 
	 protected <T> T mapFromJson(String json, Class<T> clazz)
		      throws JsonParseException, JsonMappingException, IOException {
		      
		      ObjectMapper objectMapper = new ObjectMapper();
		      return objectMapper.readValue(json, clazz);
		   }
}
