package com.victor.sociotorcedor.generator;

import java.util.ArrayList;
import java.util.List;

import com.victor.sociotorcedor.dto.CampanhaDTO;

public class CampanhaDTOGenerator {

	public List<CampanhaDTO> generateValidCampanhaDTO(int quantidade) {
		List<CampanhaDTO> campanhasDTO = new ArrayList<>();
		
		for (int i = 0; i < quantidade; i++) {
			CampanhaDTO campanhaDTO = new CampanhaDTO();
			campanhaDTO.setId(AtomicIdGenerator.getInstance().getNewId());
			
			campanhasDTO.add(campanhaDTO);
		}
		
		return campanhasDTO;
	}

}
