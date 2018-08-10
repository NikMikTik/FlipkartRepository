package com.flipkart.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.flipkart.dto.MerchantDto;
import com.flipkart.dto.ResponseDto;
import com.flipkart.exception.FlipkartException;
import com.flipkart.model.Merchant;
import com.flipkart.repository.MerchantRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Transactional
public class MerchantServiceImpl implements  MerchantService {

	Logger logger = LoggerFactory.getLogger(MerchantServiceImpl.class);
	ModelMapper modelMapper = new ModelMapper();
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private MerchantRepository merchantRepository;
	
	
	@Autowired
	private IRedisService iredis;
	
	

	
	@Override
	public ResponseDto loginFunction(MerchantDto merchantDto) {
		ResponseDto response = new ResponseDto();
		Merchant merchant=merchantRepository.findByMerchantEmail(merchantDto.getMerchantEmail());
		if(merchantRepository.findByMerchantEmail(merchantDto.getMerchantEmail())==null)
		{response.setCode(HttpStatus.UNAUTHORIZED.value());
		response.setMessage("Not a valid Email..");
		response.setResponse("Access Denied");
		return response;
		}
		if(bCryptPasswordEncoder.matches(merchantDto.getMerchantPassword(), merchant.getMerchantPassword())) {
		String token=null;
		try {
			 token = Jwts.builder().setSubject("flipkart"+merchant.getMerchantName()+"flipkart").claim("scope", "self groups/admins")
						.signWith(SignatureAlgorithm.HS256, "secret".getBytes("UTF-8"))
						.setIssuedAt(new Date(System.currentTimeMillis()))
						.setExpiration(new Date(System.currentTimeMillis() + 10000)).compact();
			iredis.setValue(token,merchant.getMerchantEmail());
			response.setCode(HttpStatus.OK.value());
			response.setMessage("Merchant successfully logged In");
			response.setResponse("Access Given");
			response.setToken(token);
			return response;
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		}
		else
		{response.setCode(HttpStatus.UNAUTHORIZED.value());
		response.setMessage("Email/Password Not valid");
		response.setResponse("Access Denied");
		return response;
		}
		return response;
		


		
	}

	@Override
	public String registrationFunction(MerchantDto merchantDto) {
		Merchant merchant = new Merchant();
		merchant = modelMapper.map(merchantDto, Merchant.class);
		if (merchantDto.getMerchantPassword().equals(merchantDto.getMerchantConfirmPassword())) {
			merchant.setMerchantPassword(bCryptPasswordEncoder.encode(merchantDto.getMerchantPassword()));
			merchantRepository.save(merchant);
			return "Registered Successfully";
		} else {
			return "Cannot Register this seller";
		}

	}

	@Override
	public List<MerchantDto> fetchAllUser() {
		List<Merchant> merchantList = merchantRepository.findAll();
		List<MerchantDto> merchantDtoList = new ArrayList<>();
		MerchantDto merchantDto = new MerchantDto();
		for (Merchant merchant : merchantList) {
			merchantDto = modelMapper.map(merchant, MerchantDto.class);
			merchantDtoList.add(merchantDto);
		}
		if (merchantDtoList.isEmpty()) {
			throw new FlipkartException("No Merchants Registered..");
		}
		return merchantDtoList;

	}

	@Override
	public ResponseDto logoutFunction(MerchantDto merchantDto,HttpServletRequest request) {
		String token=request.getHeader("Authorization");
		iredis.deleteValue(token);
		ResponseDto responseDto=new ResponseDto();
		responseDto.setCode(HttpStatus.OK.value());
		responseDto.setMessage("Successfully logged out");
		responseDto.setToken("Token Deleted");
		responseDto.setResponse("To get Access, Login Again");
		return responseDto;
		
		
	}

	
}
