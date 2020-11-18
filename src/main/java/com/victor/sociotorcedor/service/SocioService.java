package com.victor.sociotorcedor.service;

import java.util.List;

import com.victor.sociotorcedor.dto.CampanhaDTO;
import com.victor.sociotorcedor.dto.SocioDTO;

public interface SocioService {

	List<CampanhaDTO> criar(SocioDTO socioDTO);
}
