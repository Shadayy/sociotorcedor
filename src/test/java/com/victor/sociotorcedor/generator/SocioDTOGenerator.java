package com.victor.sociotorcedor.generator;

import java.util.ArrayList;
import java.util.List;

import com.victor.sociotorcedor.dto.SocioDTO;

public class SocioDTOGenerator {

	public SocioDTO generateValidSocioDTO() {
		SocioDTO socioDTO = new SocioDTO();
		socioDTO.setDataNascimento(AtomicIdGenerator.getInstance().getNewId());
		socioDTO.setEmail("email-"+AtomicIdGenerator.getInstance().getNewId());
		socioDTO.setIdTimeCoracao(AtomicIdGenerator.getInstance().getNewId());
		socioDTO.setNome("Socio - "+ AtomicIdGenerator.getInstance().getNewId());
		
		return socioDTO;
	}

	public List<SocioDTO> getInvalidListSocioDTO() {
		List<SocioDTO> sociosDTO = new ArrayList<>();
		
		SocioDTO socioDTO = generateValidSocioDTO();
		socioDTO.setDataNascimento(null);
		sociosDTO.add(socioDTO);
		
		socioDTO = generateValidSocioDTO();
		socioDTO.setEmail(null);
		sociosDTO.add(socioDTO);
		
		socioDTO = generateValidSocioDTO();
		socioDTO.setIdTimeCoracao(null);
		sociosDTO.add(socioDTO);
		
		socioDTO = generateValidSocioDTO();
		socioDTO.setNome(null);
		sociosDTO.add(socioDTO);
		
		return sociosDTO;
	}

}
