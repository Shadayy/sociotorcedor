package com.victor.sociotorcedor.generator;

import com.victor.sociotorcedor.entity.Socio;
import com.victor.sociotorcedor.entity.SocioCampanha;
import com.victor.sociotorcedor.entity.SocioCampanhaID;

public class SocioCampanhaGenerator {
	
	public SocioCampanha generateValidSocioCampanha(Socio socio) {
		SocioCampanhaID socioCampanhaID = new SocioCampanhaID();
		socioCampanhaID.setIdCampanha(AtomicIdGenerator.getInstance().getNewId());
		socioCampanhaID.setIdSocio(socio.getId());
		socioCampanhaID.setIdTimeCoracao(socio.getIdTimeCoracao());
		
		SocioCampanha socioCampanha = new SocioCampanha();
		socioCampanha.setId(socioCampanhaID);
		
		return socioCampanha;
	}
}
