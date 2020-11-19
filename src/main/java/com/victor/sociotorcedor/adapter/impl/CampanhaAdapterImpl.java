package com.victor.sociotorcedor.adapter.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.victor.sociotorcedor.adapter.CampanhaAdapter;
import com.victor.sociotorcedor.dto.CampanhaDTO;
import com.victor.sociotorcedor.repository.SocioCampanhaRepository;

@Component
public class CampanhaAdapterImpl implements CampanhaAdapter {

	@Value("${endpoint.campanha.listar}")
	private String listarCampanhasUrl;
	
	private RestTemplate restTemplate;
	
	private SocioCampanhaRepository socioCampanhaRepository;
	
	@Autowired
	public CampanhaAdapterImpl(RestTemplate restTemplate, SocioCampanhaRepository socioCampanhaRepository) {
		this.restTemplate = restTemplate;
		this.socioCampanhaRepository = socioCampanhaRepository;
	}
	
	@Override
	@HystrixCommand(fallbackMethod = "fallbackListaCampanhas")
	public List<CampanhaDTO> listarCampanhas(Long idTimeCoracao) {
		ResponseEntity<List<CampanhaDTO>> response = restTemplate.exchange(
				String.format(listarCampanhasUrl, idTimeCoracao),
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<CampanhaDTO>>() { });
		
		return response.getBody();
	}
	
	public List<CampanhaDTO> fallbackListaCampanhas(Long idTimeCoracao){
		List<Long> idsCampanha = this.socioCampanhaRepository.findDistincIdCampanhatByIdTimeCoracao(idTimeCoracao);
		
		return idsCampanha.stream()
			.map(CampanhaDTO::new
		).collect(Collectors.toList());
	}

}
