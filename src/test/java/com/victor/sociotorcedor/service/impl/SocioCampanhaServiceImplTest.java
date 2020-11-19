package com.victor.sociotorcedor.service.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.victor.sociotorcedor.adapter.CampanhaAdapter;
import com.victor.sociotorcedor.dto.CampanhaDTO;
import com.victor.sociotorcedor.entity.Socio;
import com.victor.sociotorcedor.generator.CampanhaDTOGenerator;
import com.victor.sociotorcedor.generator.SocioGenerator;
import com.victor.sociotorcedor.repository.SocioCampanhaRepository;

class SocioCampanhaServiceImplTest {
	
	@Mock
	private CampanhaAdapter campanhaAdapter;
	
	@Mock
	private SocioCampanhaRepository socioCampanhaRepository;
	
	@InjectMocks
	private SocioCampanhaServiceImpl socioCampanhaServiceImpl;
	
	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void when_associarCampanhasFaltantes_should_listarCampanhas_findAllIdCampanhaByIdIdSocio_and_save() {
		Socio socio = new SocioGenerator().generateValidSocio();
		
		List<CampanhaDTO> todasCampanhas = new CampanhaDTOGenerator().generateValidCampanhaDTO(4);
		
		List<Long> resultadoFindAllIdCampanhaByIdIdSocio = new ArrayList<>();
		resultadoFindAllIdCampanhaByIdIdSocio.add(todasCampanhas.get(0).getId());
		resultadoFindAllIdCampanhaByIdIdSocio.add(todasCampanhas.get(2).getId());
		
		List<CampanhaDTO> esperadoCampanhasAssociadas = new ArrayList<>();
		esperadoCampanhasAssociadas.add(todasCampanhas.get(1));
		esperadoCampanhasAssociadas.add(todasCampanhas.get(3));
		
		when(this.campanhaAdapter.listarCampanhas(socio.getIdTimeCoracao())).thenReturn(todasCampanhas);
		when(this.socioCampanhaRepository.findAllIdCampanhaByIdIdSocio(socio.getId())).thenReturn(resultadoFindAllIdCampanhaByIdIdSocio);
		
		List<CampanhaDTO> resultadoCampanhasAssociadas = this.socioCampanhaServiceImpl.associarCampanhasFaltantes(socio);
		
		verify(this.campanhaAdapter, times(1)).listarCampanhas(Mockito.anyLong());
		verify(this.socioCampanhaRepository, times(1)).findAllIdCampanhaByIdIdSocio(Mockito.anyLong());
		verify(this.socioCampanhaRepository, times(esperadoCampanhasAssociadas.size())).save(Mockito.any());
		
		Assertions.assertEquals(esperadoCampanhasAssociadas, resultadoCampanhasAssociadas);
	}
	
	@Test
	void when_associarTodasCampanhas_listarCampanhas_and_save() {
		Socio socio = new SocioGenerator().generateValidSocio();
		
		List<CampanhaDTO> todasCampanhas = new CampanhaDTOGenerator().generateValidCampanhaDTO(4);
		
		when(this.campanhaAdapter.listarCampanhas(socio.getIdTimeCoracao())).thenReturn(todasCampanhas);
		
		List<CampanhaDTO> resultadoCampanhasAssociadas = this.socioCampanhaServiceImpl.associarTodasCampanhas(socio);
		
		verify(this.campanhaAdapter, times(1)).listarCampanhas(Mockito.anyLong());
		verify(this.socioCampanhaRepository, times(todasCampanhas.size())).save(Mockito.any());
		
		Assertions.assertEquals(todasCampanhas, resultadoCampanhasAssociadas);
	}

}
