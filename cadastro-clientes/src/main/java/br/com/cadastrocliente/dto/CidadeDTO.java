package br.com.cadastrocliente.dto;

import java.io.Serializable;

import br.com.cadastrocliente.domain.Estado;
import br.com.cadastrocliente.services.validation.CidadeDTOInsert;

@CidadeDTOInsert
public class CidadeDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private Estado estado;
	
	public CidadeDTO() {}
	
	public CidadeDTO(Integer id, String nome, Estado estado) {
		super();
		this.id = id;
		this.nome = nome;
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
}
