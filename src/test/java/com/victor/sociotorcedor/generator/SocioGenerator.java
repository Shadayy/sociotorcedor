package com.victor.sociotorcedor.generator;

import com.victor.sociotorcedor.entity.Socio;

public class SocioGenerator {
	
	public Socio generateValidSocio() {
		Socio socio = new Socio();
		socio.setDataNascimento(AtomicIdGenerator.getInstance().getNewId());
		socio.setEmail("email-"+AtomicIdGenerator.getInstance().getNewId());
		socio.setIdTimeCoracao(AtomicIdGenerator.getInstance().getNewId());
		socio.setNome("Socio - "+ AtomicIdGenerator.getInstance().getNewId());
		socio.setId(AtomicIdGenerator.getInstance().getNewId());
		
		return socio;
	}

}
