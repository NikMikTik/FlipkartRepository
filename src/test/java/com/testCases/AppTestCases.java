package com.testCases;

import org.assertj.core.api.Assertions;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.core.ParameterizedTypeReference;

import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

import com.flipkart.FlipkartMerchantProductListingApplication;
import com.flipkart.dto.MerchantDto;

import com.flipkart.repository.MerchantRepository;
import com.flipkart.service.MerchantService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FlipkartMerchantProductListingApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(FlipkartMerchantProductListingApplication.class)
public class AppTestCases {

	@LocalServerPort
	Integer port;

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	MerchantService merchantService;
	
	@MockBean
	MerchantRepository merchantRepository;

	ModelMapper modelMapper = new ModelMapper();

	@Test
	public void fetchAllMerchants() {
		MerchantDto merchantDto1 = new MerchantDto();
		merchantDto1.setMerchantId(1);
		merchantDto1.setMerchantName("Nikku");
		merchantDto1.setMerchantEmail("nikku@gamilcom");
		merchantDto1.setMerchantPassword("qwe");
		merchantDto1.setMerchantConfirmPassword("qwe");

		MerchantDto merchantDto2 = new MerchantDto();
		merchantDto2.setMerchantId(1);
		merchantDto2.setMerchantName("Mikku");
		merchantDto2.setMerchantEmail("mikku@gamilcom");
		merchantDto2.setMerchantPassword("qwee");
		merchantDto2.setMerchantConfirmPassword("qwee");

		List<MerchantDto> merchantList = new ArrayList<>();
		merchantList.add(merchantDto1);
		merchantList.add(merchantDto2);

		Mockito.when(merchantService.fetchAllUser()).thenReturn(merchantList);
		String url = "http://localhost:" + port + "/flipkart/allMerchants";

		List<MerchantDto> response = restTemplate
				.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<MerchantDto>>() {
				}).getBody();

		assertNotNull(response);
		Assertions.assertThat(response.size()).isEqualTo(2);
		Assertions.assertThat(response.get(0).getMerchantName()).isEqualTo("Nikku");
		Assertions.assertThat(response.get(1).getMerchantName()).isEqualTo("Mikku");

	}

	
	
/*	@Test(expected = FlipkartException.class)
	public void fetchAllMerchantsTrueCheck() {
	
		Merchant merchant = new Merchant();
		MerchantDto merchantDto = new MerchantDto();

		merchant.setMerchantId(1);
		merchant.setMerchantName("Nikku");
		merchant.setMerchantEmail("nikku@gamilcom");
		merchant.setMerchantPassword("qwe");
		merchant.setMerchantConfirmPassword("qwe");
		
		List<Merchant> merchantList=new ArrayList<>();
		merchantList.add(merchant);
		
		merchantDto=modelMapper.map(merchant, MerchantDto.class);
		
		List<MerchantDto> merchantDtoList=new ArrayList<>();
		merchantDtoList.add(merchantDto);

		Mockito.when(merchantRepository.findAll()).thenReturn(merchantList);
		Assert.
		
	}*/

}
