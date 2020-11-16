package com.victor.sociotorcedor.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class SocioCampanhaID implements Serializable {
	@NotNull
	@Column(name = "IDSOCIO")
	private Long idSocio;
	
	@NotNull
	@Column(name = "IDCAMPANHA")
	private Long idCampanha;
	
	@NotNull
	@Column(name = "IDTIMEDOCORACAO")
	private Long idTimeCoracao;
	
	private static final long serialVersionUID = 1L;
}
