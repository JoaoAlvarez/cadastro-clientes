package br.com.cadastrocliente.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cadastrocliente.domain.Cliente;
import br.com.cadastrocliente.domain.enums.TipoSexo;
import br.com.cadastrocliente.dto.ClienteDTO;
import br.com.cadastrocliente.dto.ClienteNewDTO;
import br.com.cadastrocliente.repositories.ClienteRepository;
import br.com.cadastrocliente.services.execptions.DataIntegrityException;
import br.com.cadastrocliente.services.execptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repositorio;

	/** Metodo utilizado para consultar cliente pelo ID */
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repositorio.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	/** Metodo utilizado para consultar cliente pelo nomeCompleto */
	public List<Cliente> findByNomeCompleto(String nomeCompleto) {
		Optional<List<Cliente>> obj = repositorio.findByNomeCompletoIgnoreCaseContaining(nomeCompleto);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado com nome: " + nomeCompleto));
	}

	/** Metodo utilizado para inserir um obj Cliente */
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repositorio.save(obj);
	}

	/** Metodo utilizado para atualizar um obj Cliente */
	public Cliente update(Cliente obj) {
		/* Caso nao encontre o id do obj ele retorna uma excecao */
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repositorio.save(newObj);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		// Foi requerido apenas a troca de nome
		newObj.setNomeCompleto(obj.getNomeCompleto());
	}

	/** Metodo utilizado para remover um obj Cliente */
	public void delete(Integer id) {
		/* Caso nao encontre o id do obj ele retorna uma excecao */
		find(id);
		try {
			repositorio.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			/* Caso tenha algum coisa relacionada com o cliente futuramente */
			throw new DataIntegrityException("Não é possivel remover Cliente");
		}
	}

	/** Metodo utilizado para listar todos os Cliente salvos */
	public List<Cliente> findAll() {
		return repositorio.findAll();
	}

	/** Metodo utiliado converter um objeto ClienteDTO para Cliente */
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNomeCompleto(), null, 0, null, null);
	}

	/** Metodo utiliado converter um objeto ClienteNewDTO para Cliente */
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNomeCompleto(), objDto.getDataNascimento(), 23,TipoSexo.toEnum(objDto.getSexo()),
				objDto.getCidade());
		return cli;
	}

}