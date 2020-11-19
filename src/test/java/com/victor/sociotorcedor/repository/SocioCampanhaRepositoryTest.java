package com.victor.sociotorcedor.repository;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.victor.sociotorcedor.entity.Socio;
import com.victor.sociotorcedor.entity.SocioCampanha;
import com.victor.sociotorcedor.generator.SocioCampanhaGenerator;
import com.victor.sociotorcedor.generator.SocioGenerator;

@DataJpaTest
class SocioCampanhaRepositoryTest {
	
	@Autowired
	private SocioCampanhaRepository socioCampanhaRepository;
	
	@Autowired
	private SocioRepository socioRepository;
	
	@Test
	void when_save_should_findDistincIdCampanhatByIdTimeCoracao() {
		Socio socio = new SocioGenerator().generateValidSocio();
		socio.setId(null);
		this.socioRepository.save(socio);
		
		SocioCampanha socioCampanha = new SocioCampanhaGenerator().generateValidSocioCampanha(socio);
		this.socioCampanhaRepository.save(socioCampanha);
		
		List<Long> idsCampanha = this.socioCampanhaRepository.findDistincIdCampanhatByIdTimeCoracao(socio.getIdTimeCoracao());
		
		Assertions.assertEquals(socioCampanha.getId().getIdCampanha(), idsCampanha.get(0));
	}
	
	@Test
	void when_save_should_findAllIdCampanhaByIdIdSocio() {
		Socio socio = new SocioGenerator().generateValidSocio();
		socio.setId(null);
		this.socioRepository.save(socio);
		
		SocioCampanha socioCampanha = new SocioCampanhaGenerator().generateValidSocioCampanha(socio);
		this.socioCampanhaRepository.save(socioCampanha);
		
		List<Long> idsCampanha = this.socioCampanhaRepository.findAllIdCampanhaByIdIdSocio(socio.getId());
		
		Assertions.assertEquals(socioCampanha.getId().getIdCampanha(), idsCampanha.get(0));
	}

}
