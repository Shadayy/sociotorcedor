package com.victor.sociotorcedor.adapter.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.victor.sociotorcedor.adapter.CampanhaAdapter;
import com.victor.sociotorcedor.dto.CampanhaDTO;

@Component
public class CampanhaAdapterImpl implements CampanhaAdapter {

	@Value("${endpoint.campanha.listar}")
	private String listarCampanhasUrl;
	
	@Override
	public List<CampanhaDTO> listarCampanhas(Long idTimeCoracao) {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<List<CampanhaDTO>> response = restTemplate.exchange(
				String.format(listarCampanhasUrl, idTimeCoracao),
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<CampanhaDTO>>() { });
		
		return response.getBody();
	}

}
