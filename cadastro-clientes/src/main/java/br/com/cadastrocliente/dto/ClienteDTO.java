package br.com.cadastrocliente.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.cadastrocliente.domain.Cliente;

public class ClienteDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=2, max=120, message="O nome deve conter entre 5 a 120 caracteres")
	private String nomeCompleto;
	
	public ClienteDTO() {}
	
	public ClienteDTO(Cliente obj) {
		this.id = obj.getId();
		this.nomeCompleto = obj.getNomeCompleto();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nome) {
		this.nomeCompleto = nome;
	}
	

}
