package com.victor.sociotorcedor.controller;

import static org.assertj.core.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.victor.sociotorcedor.dto.SocioDTO;
import com.victor.sociotorcedor.generator.SocioDTOGenerator;
import com.victor.sociotorcedor.service.SocioService;

@WebMvcTest(controllers = SocioController.class)
class SocioControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private SocioService socioService;
	
	@Test
	void when_post_socio_without_error_then_returnCreated() {
		SocioDTO socioDTO = new SocioDTOGenerator().generateValidSocioDTO();
		
		performAndExpect(
				post("/socio"),
				status().isCreated(), 
				socioDTO
		);
	}
	
	@Test
	void when_post_socio_with_error_then_returnBadRequest() throws Exception {
		performAndExpect(
				post("/socio"),
				status().isBadRequest(), 
				new SocioDTOGenerator().getInvalidListSocioDTO()
		);
	}
	
	private void performAndExpect(MockHttpServletRequestBuilder mockRequestBuilder, ResultMatcher resultMatcher, Object object) {
		try {
			this.mockMvc.perform(
					mockRequestBuilder
					.contentType("application/json")
					.content(this.objectMapper.writeValueAsString(object))
				)
				.andExpect(
						resultMatcher
				)
				.andReturn()
				;
		} catch (Exception e) {
			fail("Exception in performPostAndExpectBadRequest");
			throw new RuntimeException(e);
		}
	}
}
