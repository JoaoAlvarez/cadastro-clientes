package br.com.cadastrocliente.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cadastrocliente.domain.Estado;
import br.com.cadastrocliente.repositories.EstadoRepository;
import br.com.cadastrocliente.services.execptions.ObjectNotFoundException;

@Service
public class EstadoService {
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado findByNome(String nome) {
		Optional<Estado> obj = estadoRepository.findByNomeIgnoreCase(nome);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Estado "+nome+" não foi encontrado"));
	}
	 
}