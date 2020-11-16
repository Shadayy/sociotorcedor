package com.victor.sociotorcedor.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SocioDTO {
	
	@ApiModelProperty(required = true)
	@NotBlank(message = "Nome da campanha é obrigatório")
	@Size(max = 255, message = "O nome da campanha é limitado em 255 caracteres")
	private String nome;
	
	@ApiModelProperty(required = true)
	@NotBlank(message = "Nome da campanha é obrigatório")
	@Size(max = 255, message = "O nome da campanha é limitado em 255 caracteres")
	private String email;
	
	@ApiModelProperty(required = true)
	@NotNull(message = "ID do Time do Coração é obrigatório")
	@Column(name= "IDTIMEDOCORACAO")
	private Long idTimeCoracao;
	
	@ApiModelProperty(required = true)
	@NotNull(message = "Data de nascimento é obrigatório")
	@Column(name= "DATANASCIMENTO")
	private Long dataNascimento;
}
