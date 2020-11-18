package com.victor.sociotorcedor.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.victor.sociotorcedor.dto.CampanhaDTO;
import com.victor.sociotorcedor.dto.SocioDTO;
import com.victor.sociotorcedor.service.SocioService;

@RestController
@RequestMapping("/socio")
public class SocioController {

	private SocioService socioService;
	
	@Autowired
	public SocioController(SocioService socioService) {
		this.socioService = socioService;
	}
	
	@PostMapping
	public ResponseEntity<List<CampanhaDTO>> criar(@Valid @RequestBody SocioDTO socioDTO) {
		return new ResponseEntity<List<CampanhaDTO>>(socioService.criar(socioDTO), HttpStatus.CREATED);
	}
}
