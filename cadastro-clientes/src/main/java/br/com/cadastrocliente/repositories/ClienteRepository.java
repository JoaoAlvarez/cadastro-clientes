package br.com.cadastrocliente.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cadastrocliente.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer>{

	@Transactional(readOnly=true)
	Optional<List<Cliente>> findByNomeCompletoIgnoreCaseContaining(String nomeCompleto);

}
