package br.com.cadastrocliente.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cadastrocliente.domain.Cidade;
import br.com.cadastrocliente.domain.Estado;
import br.com.cadastrocliente.dto.CidadeDTO;
import br.com.cadastrocliente.repositories.CidadeRepository;
import br.com.cadastrocliente.services.execptions.ObjectNotFoundException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoService estadoService;
	
	/**Metodo utilizado para consultar Cidades pelo nome*/
	public List<Cidade> findByNome(String nome) {
		Optional<List<Cidade>> obj = cidadeRepository.findByNomeIgnoreCaseContaining(nome);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Cidade "+nome+" não foi encontrada"));
	}
	
	/**Metodo utilizado para consultar */
	public List<Cidade> findByEstado(String nomeEstado) {
		Estado estado = estadoService.findByNome(nomeEstado);
		Optional<List<Cidade>> obj = cidadeRepository.findByEstado(estado);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Nenhuma cidade cadastrada no Estado "+ nomeEstado));
	}
	
	/**Metodo utilizado salvar um objeto Cidade*/
	@Transactional
	public Cidade insert(Cidade obj) {
		obj.setId(null);
		return cidadeRepository.save(obj);
	}
	
	/**Metodo utilizado para converter um CidadeDTO para Cidade*/
	public Cidade fromDTO(CidadeDTO obj) {
		return new Cidade(obj.getId(),obj.getNome(),obj.getEstado());
	}
	 
}