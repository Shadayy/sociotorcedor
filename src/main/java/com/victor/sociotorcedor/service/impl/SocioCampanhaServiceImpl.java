package com.victor.sociotorcedor.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victor.sociotorcedor.adapter.CampanhaAdapter;
import com.victor.sociotorcedor.dto.CampanhaDTO;
import com.victor.sociotorcedor.entity.Socio;
import com.victor.sociotorcedor.entity.SocioCampanha;
import com.victor.sociotorcedor.repository.SocioCampanhaRepository;
import com.victor.sociotorcedor.service.SocioCampanhaService;

@Service
@Transactional
public class SocioCampanhaServiceImpl implements SocioCampanhaService {
	
	private CampanhaAdapter campanhaAdapter;
	private SocioCampanhaRepository socioCampanhaRepository;
	
	@Autowired
	public SocioCampanhaServiceImpl(CampanhaAdapter campanhaAdapter, SocioCampanhaRepository socioCampanhaRepository) {
		this.campanhaAdapter = campanhaAdapter;
		this.socioCampanhaRepository = socioCampanhaRepository;
	}
	
	@Override
	public List<CampanhaDTO> associarCampanhasFaltantes(Socio socio) {
		List<CampanhaDTO> todasCampanhas = listarCampanhasTimeCoracao(socio.getIdTimeCoracao());
		List<Long> campanhasAssociadas = this.socioCampanhaRepository.findAllIdCampanhaByIdIdSocio(socio.getId());
		
		List<CampanhaDTO> campanhasNaoAssociadas = todasCampanhas.stream()
			.filter(campanhaDTO ->
				!(campanhasAssociadas.contains(campanhaDTO.getId()))
			).collect(Collectors.toList());
		
		associarCampanha(socio, campanhasNaoAssociadas);
		
		return campanhasNaoAssociadas;
	}

	@Override
	public List<CampanhaDTO> associarTodasCampanhas(Socio socio) {
		List<CampanhaDTO> todasCampanhas = listarCampanhasTimeCoracao(socio.getIdTimeCoracao());
		
		associarCampanha(socio, todasCampanhas);
		
		return todasCampanhas;
	}
	
	public List<CampanhaDTO> listarCampanhasTimeCoracao(Long idTimeCoracao) {
		return this.campanhaAdapter.listarCampanhas(idTimeCoracao);
	}
	
	private void associarCampanha(Socio socio, List<CampanhaDTO> campanhasDTO) {
		campanhasDTO.forEach(campanhaDTO ->{
			SocioCampanha socioCampanha = new SocioCampanha(socio.getId(), campanhaDTO.getId(), socio.getIdTimeCoracao());
			this.socioCampanhaRepository.save(socioCampanha);
		});
	}
}
