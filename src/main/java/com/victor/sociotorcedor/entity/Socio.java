package com.victor.sociotorcedor.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.victor.sociotorcedor.dto.SocioDTO;

import lombok.Data;

@Data
@Entity
public class Socio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Nome da campanha é obrigatório")
	@Size(max = 255, message = "O nome da campanha é limitado em 255 caracteres")
	private String nome;
	
	@NotBlank(message = "Nome da campanha é obrigatório")
	@Size(max = 255, message = "O nome da campanha é limitado em 255 caracteres")
	private String email;
	
	@NotNull(message = "ID do Time do Coração é obrigatório")
	@Column(name= "IDTIMEDOCORACAO")
	private Long idTimeCoracao;
	
	@NotNull(message = "Data de nascimento é obrigatório")
	@Column(name= "DATANASCIMENTO")
	private Long dataNascimento;
	
	public Socio() {
		
	}
	
	public Socio(SocioDTO socioDTO) {
		setDataNascimento(socioDTO.getDataNascimento());
		setEmail(socioDTO.getEmail());
		setIdTimeCoracao(socioDTO.getIdTimeCoracao());
		setNome(socioDTO.getNome());
	}

}
