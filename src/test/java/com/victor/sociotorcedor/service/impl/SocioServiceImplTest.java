package com.victor.sociotorcedor.service.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.victor.sociotorcedor.dto.SocioDTO;
import com.victor.sociotorcedor.entity.Socio;
import com.victor.sociotorcedor.generator.SocioDTOGenerator;
import com.victor.sociotorcedor.repository.SocioRepository;
import com.victor.sociotorcedor.service.SocioCampanhaService;

class SocioServiceImplTest {
	
	@Mock
	private SocioRepository socioRepository;
	
	@Mock
	private SocioCampanhaService socioCampanhaService;
	
	@InjectMocks
	private SocioServiceImpl socioServiceImpl;
	
	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void when_criar_with_existing_socio_should_associarCampanhasFaltantes() {
		SocioDTO socioDTO = new SocioDTOGenerator().generateValidSocioDTO();
		Socio socio = new Socio(socioDTO);
		
		when(this.socioRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(socio));
		this.socioServiceImpl.criar(socioDTO);
		
		verify(this.socioCampanhaService, times(1)).associarCampanhasFaltantes(Mockito.any());
		
		Assertions.assertAll(
				() -> Assertions.assertEquals(socioDTO.getDataNascimento(), socio.getDataNascimento()),
				() -> Assertions.assertEquals(socioDTO.getEmail(), socio.getEmail()),
				() -> Assertions.assertEquals(socioDTO.getIdTimeCoracao(), socio.getIdTimeCoracao()),
				() -> Assertions.assertEquals(socioDTO.getNome(), socio.getNome())
		);
	}
	
	@Test
	void when_criar_with_new_socio_should_save_and_associarTodasCampanhas() {
		SocioDTO socioDTO = new SocioDTOGenerator().generateValidSocioDTO();
		
		this.socioServiceImpl.criar(socioDTO);
		
		verify(this.socioRepository, times(1)).save(Mockito.any());
		verify(this.socioCampanhaService, times(1)).associarTodasCampanhas(Mockito.any());
	}

}
