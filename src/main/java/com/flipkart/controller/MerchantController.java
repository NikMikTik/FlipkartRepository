package com.flipkart.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flipkart.dto.MerchantDto;
import com.flipkart.exception.FlipkartException;
import com.flipkart.model.Merchant;
import com.flipkart.repository.MerchantRepository;
import com.flipkart.service.IRedisService;
import com.flipkart.service.MerchantService;

@RestController
@RequestMapping("/flipkart")
public class MerchantController {
	Logger logger = LoggerFactory.getLogger(Merchant.class);
	ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private MerchantRepository merchantRepository;
	
	@Autowired
	private IRedisService iredis;
	
	//GET ALL MERCHANTS LIST
	@RequestMapping(value = "/allMerchants", method = RequestMethod.GET)
	public List<MerchantDto> retrieveAllMerchants(HttpServletRequest request) {
		if(iredis.checkToken(request)==true)
		return merchantService.fetchAllUser();
		else
		return null;
	}


}
