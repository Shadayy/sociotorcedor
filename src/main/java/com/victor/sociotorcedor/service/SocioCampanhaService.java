package com.victor.sociotorcedor.service;

import java.util.List;

import com.victor.sociotorcedor.dto.CampanhaDTO;
import com.victor.sociotorcedor.entity.Socio;

public interface SocioCampanhaService {

	List<CampanhaDTO> associarCampanhasFaltantes(Socio socio);

	List<CampanhaDTO> associarTodasCampanhas(Socio socio);

}
