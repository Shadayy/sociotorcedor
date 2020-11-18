package com.victor.sociotorcedor.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victor.sociotorcedor.dto.CampanhaDTO;
import com.victor.sociotorcedor.dto.SocioDTO;
import com.victor.sociotorcedor.entity.Socio;
import com.victor.sociotorcedor.repository.SocioRepository;
import com.victor.sociotorcedor.service.SocioCampanhaService;
import com.victor.sociotorcedor.service.SocioService;

@Service
@Transactional
public class SocioServiceImpl implements SocioService {
	
	private SocioRepository socioRepository;
	private SocioCampanhaService socioCampanhaService;
	
	@Autowired
	public SocioServiceImpl(SocioRepository socioRepository, SocioCampanhaService socioCampanhaService) {
		this.socioRepository = socioRepository;
		this.socioCampanhaService = socioCampanhaService;
	}

	@Override
	public List<CampanhaDTO> criar(SocioDTO socioDTO) {
		
		Optional<Socio> optionalSocio = this.socioRepository.findByEmail(socioDTO.getEmail());
		
		if(optionalSocio.isPresent()) {
			return this.socioCampanhaService.associarCampanhasFaltantes(optionalSocio.get());
		}
		
		Socio socio = new Socio(socioDTO);
		this.socioRepository.save(socio);
		
		return this.socioCampanhaService.associarTodasCampanhas(socio);
	}
}
