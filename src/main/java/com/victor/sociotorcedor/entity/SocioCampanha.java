package com.victor.sociotorcedor.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Table(name = "SOCIOCAMPANHA")
@Data
@Entity
public class SocioCampanha {
	
	@EmbeddedId
	private SocioCampanhaID id;
	
	public SocioCampanha() {
		
	}
	
	public SocioCampanha(Long idSocio, Long idCampanha, Long idTimeCoracao) {
		setId(new SocioCampanhaID(idSocio, idCampanha, idTimeCoracao));
	}
}
