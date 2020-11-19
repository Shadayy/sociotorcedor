package com.victor.sociotorcedor.repository;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.victor.sociotorcedor.entity.Socio;
import com.victor.sociotorcedor.generator.SocioGenerator;

@DataJpaTest
class SocioRepositoryTest {
	
	@Autowired
	private SocioRepository socioRepository;
	
	@Test
	void when_save_should_findByEmail() {
		Socio socio = new SocioGenerator().generateValidSocio();
		socio.setId(null);
		this.socioRepository.save(socio);
		
		Optional<Socio> optionalSocio = this.socioRepository.findByEmail(socio.getEmail());
		
		Assertions.assertAll(
				() -> Assertions.assertTrue(optionalSocio.isPresent()),
				() -> Assertions.assertEquals(socio, optionalSocio.get())
		);
	}

}
