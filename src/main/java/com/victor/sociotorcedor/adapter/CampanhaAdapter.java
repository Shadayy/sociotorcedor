package com.victor.sociotorcedor.adapter;

import java.util.List;

import com.victor.sociotorcedor.dto.CampanhaDTO;

public interface CampanhaAdapter {

	List<CampanhaDTO> listarCampanhas(Long idTimeCoracao);
	
}
