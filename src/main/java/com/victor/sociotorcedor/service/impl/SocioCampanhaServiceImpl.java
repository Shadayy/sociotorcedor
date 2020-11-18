package com.victor.sociotorcedor.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.victor.sociotorcedor.adapter.CampanhaAdapter;
import com.victor.sociotorcedor.dto.CampanhaDTO;
import com.victor.sociotorcedor.entity.Socio;
import com.victor.sociotorcedor.entity.SocioCampanha;
import com.victor.sociotorcedor.repository.SocioCampanhaRepository;
import com.victor.sociotorcedor.service.SocioCampanhaService;

public class SocioCampanhaServiceImpl implements SocioCampanhaService {
	
	private CampanhaAdapter campanhaAdapter;
	private SocioCampanhaRepository socioCampanhaRepository;
	
	@Override
	public List<CampanhaDTO> associarCampanhasFaltantes(Socio socio) {
		List<CampanhaDTO> todasCampanhas = listarCampanhasTimeCoracao(socio.getIdTimeCoracao());
		List<Long> campanhasAssociadas = this.socioCampanhaRepository.findAllIdCampanhaByIdIdSocio(socio.getId());
		
		List<CampanhaDTO> campanhasNaoAssociadas = todasCampanhas.stream()
			.filter(campanhaDTO ->{
				return !(campanhasAssociadas.contains(campanhaDTO.getId()));
			}).collect(Collectors.toList());
		
		associarCampanha(socio, campanhasNaoAssociadas);
		
		return campanhasNaoAssociadas;
	}

	@Override
	public List<CampanhaDTO> associarTodasCampanhas(Socio socio) {
		List<CampanhaDTO> todasCampanhas = listarCampanhasTimeCoracao(socio.getIdTimeCoracao());
		
		associarCampanha(socio, todasCampanhas);
		
		return todasCampanhas;
	}
	
	@HystrixCommand(fallbackMethod = "fallbackListaCampanhas")
	private List<CampanhaDTO> listarCampanhasTimeCoracao(Long idTimeCoracao) {
		return this.campanhaAdapter.listarCampanhas(idTimeCoracao);
	}
	
	@SuppressWarnings("unused")
	private List<CampanhaDTO> fallbackListaCampanhas(Long idTimeCoracao){
		List<Long> idsCampanha = this.socioCampanhaRepository.findDistincIdCampanhatByIdTimeCoracao(idTimeCoracao);
		
		return idsCampanha.stream()
			.map(idCampanha -> new CampanhaDTO(idCampanha)
		).collect(Collectors.toList());
	}
	
	private void associarCampanha(Socio socio, List<CampanhaDTO> campanhasDTO) {
		campanhasDTO.forEach(campanhaDTO ->{
			SocioCampanha socioCampanha = new SocioCampanha(socio.getId(), campanhaDTO.getId(), socio.getIdTimeCoracao());
			this.socioCampanhaRepository.save(socioCampanha);
		});
	}
}
