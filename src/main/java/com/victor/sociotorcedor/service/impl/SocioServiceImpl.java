package com.victor.sociotorcedor.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.victor.sociotorcedor.adapter.CampanhaAdapter;
import com.victor.sociotorcedor.dto.CampanhaDTO;
import com.victor.sociotorcedor.dto.SocioDTO;
import com.victor.sociotorcedor.entity.Socio;
import com.victor.sociotorcedor.entity.SocioCampanha;
import com.victor.sociotorcedor.repository.SocioCampanhaRepository;
import com.victor.sociotorcedor.repository.SocioRepository;
import com.victor.sociotorcedor.service.SocioService;

@Service
@Transactional
public class SocioServiceImpl implements SocioService {
	
	private CampanhaAdapter campanhaAdapter;
	private SocioRepository socioRepository;
	private SocioCampanhaRepository socioCampanhaRepository;
	
	@Autowired
	public SocioServiceImpl(CampanhaAdapter campanhaAdapter, SocioRepository socioRepository, SocioCampanhaRepository socioCampanhaRepository) {
		this.campanhaAdapter = campanhaAdapter;
		this.socioRepository = socioRepository;
		this.socioCampanhaRepository = socioCampanhaRepository;
	}

	@Override
	public Long criar(SocioDTO socioDTO) {
		Socio socio = new Socio(socioDTO);
		
		this.socioRepository.save(socio);
		
		return socio.getId();
	}

	@HystrixCommand(fallbackMethod = "fallbackListaCampanhas")
	@Override
	public List<CampanhaDTO> listarCampanhas(Long idSocio) {
		Socio socio = getSocioOrThrow(idSocio);
		return this.campanhaAdapter.listarCampanhas(socio.getIdTimeCoracao());
	}

	@Override
	public void associarCampanha(Long idSocio, Long idCampanha) {
		Socio socio = getSocioOrThrow(idSocio);
		SocioCampanha socioCampanha = new SocioCampanha(idSocio, idCampanha, socio.getIdTimeCoracao());
		this.socioCampanhaRepository.save(socioCampanha);
	}
	
	private Socio getSocioOrThrow(Long idSocio) {
		Optional<Socio> optionalSocio = this.socioRepository.findById(idSocio);
		
		if(!optionalSocio.isPresent()) {
			throw new EntityNotFoundException();
		}
		
		return optionalSocio.get();
	}
	
	public List<CampanhaDTO> fallbackListaCampanhas(Long idSocio){
		Socio socio = getSocioOrThrow(idSocio);
		
		List<Long> idsCampanha = this.socioCampanhaRepository.findDistincIdCampanhatByIdTimeCoracao(socio.getIdTimeCoracao());
		
		return idsCampanha.stream()
			.map(idCampanha -> new CampanhaDTO(idCampanha)
		).collect(Collectors.toList());
	}

}
