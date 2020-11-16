package com.victor.sociotorcedor.service;

import java.util.List;

import com.victor.sociotorcedor.dto.CampanhaDTO;
import com.victor.sociotorcedor.dto.SocioDTO;

public interface SocioService {

	Long criar(SocioDTO socioDTO);

	List<CampanhaDTO> listarCampanhas(Long idSocio);

	void associarCampanha(Long idSocio, Long idCampanha);

}
