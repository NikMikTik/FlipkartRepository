package com.flipkart.controller;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flipkart.dto.MerchantDto;
import com.flipkart.dto.ResponseDto;
import com.flipkart.exception.FlipkartException;
import com.flipkart.model.Merchant;
import com.flipkart.service.MerchantService;

@RestController
@RequestMapping("/flipkart")
public class AppController {

	Logger logger = LoggerFactory.getLogger(Merchant.class);
	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private MerchantService merchantService;
	
	//SIGNIN
	@RequestMapping(value = "/signIn", method = RequestMethod.POST)
	public ResponseDto login(@RequestBody MerchantDto merchantDto)  {
		ResponseDto merchant = merchantService.loginFunction(merchantDto);
		if (merchant == null)
			throw new FlipkartException("Cannot log in.. Not Authorised.."); 
		else
			return merchant;
	}

	//SIGNUP	
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public String registration(@RequestBody MerchantDto merchantDto) {
		String messgae = merchantService.registrationFunction(merchantDto);
		if (messgae == null)
			return "Cannot Register this seller..";
		else
			return "Registered Successfully";
	}
	
	//LOGOUT
	@RequestMapping(value = "/logout", method = RequestMethod.DELETE)
	public ResponseDto logout(@RequestBody MerchantDto merchantDto,HttpServletRequest request) {
		return merchantService.logoutFunction(merchantDto,request);
		
	}


}
