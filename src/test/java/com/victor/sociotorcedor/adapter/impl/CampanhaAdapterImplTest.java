package com.victor.sociotorcedor.adapter.impl;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.victor.sociotorcedor.dto.CampanhaDTO;
import com.victor.sociotorcedor.repository.SocioCampanhaRepository;

class CampanhaAdapterImplTest {
	
	@Mock
	private RestTemplate restTemplate;
	
	@Mock
	private SocioCampanhaRepository socioCampanhaRepository;
	
	@InjectMocks
	private CampanhaAdapterImpl campanhaAdapterImpl;
	
	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(campanhaAdapterImpl, "listarCampanhasUrl", "localhost:8080/campanha/listar/%d");
	}
	
	@SuppressWarnings("unchecked")
	@Test
	void when_listarCampanhas_should_not_transform_result() {
		CampanhaDTO campanhaDTO = new CampanhaDTO(1L);
		List<CampanhaDTO> campanhasDTO = Collections.singletonList(campanhaDTO);
		ResponseEntity<List<CampanhaDTO>> responseEntity = new ResponseEntity<List<CampanhaDTO>>(campanhasDTO, HttpStatus.OK);
		
		when(restTemplate.exchange(
				Mockito.anyString(), 
				Mockito.any(HttpMethod.class), 
				Mockito.any(),
				Mockito.any(ParameterizedTypeReference.class))
		).thenReturn(responseEntity);
		
		List<CampanhaDTO> campanhasBusca = this.campanhaAdapterImpl.listarCampanhas(1l);
		
		Assertions.assertEquals(campanhasBusca.get(0), campanhasDTO.get(0));
		
	}
	
	@Test
	void when_fallbackListaCampanhas_should_transform_findDistincIdCampanhatByIdTimeCoracao() {
		List<Long> listaIds = new ArrayList<>();
		listaIds.add(1L);
		listaIds.add(2L);
		
		when(this.socioCampanhaRepository.findDistincIdCampanhatByIdTimeCoracao(Mockito.anyLong())).thenReturn(listaIds);
		
		List<CampanhaDTO> campanhas = this.campanhaAdapterImpl.fallbackListaCampanhas(Mockito.anyLong());
		
		Assertions.assertAll(
				() -> Assertions.assertEquals(listaIds.get(0), campanhas.get(0).getId()),
				() -> Assertions.assertEquals(listaIds.get(1), campanhas.get(1).getId())
		);
	}

}
