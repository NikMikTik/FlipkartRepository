package com.flipkart.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flipkart.dto.MerchantDto;
import com.flipkart.dto.ResponseDto;
import com.flipkart.exception.FlipkartException;

public interface MerchantService {
	
	public ResponseDto loginFunction(MerchantDto merchantDto);

	public String registrationFunction(MerchantDto merchantDto);

	public List<MerchantDto> fetchAllUser() ;

	public ResponseDto logoutFunction(MerchantDto merchantDto, HttpServletRequest request);
	

}
