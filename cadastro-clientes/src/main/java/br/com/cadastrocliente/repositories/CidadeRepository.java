package br.com.cadastrocliente.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cadastrocliente.domain.Cidade;
import br.com.cadastrocliente.domain.Estado;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade,Integer>{
	
	@Transactional(readOnly=true)
	Optional<List<Cidade>> findByEstado(Estado estado);
	
	@Transactional(readOnly=true)
	Optional<List<Cidade>> findByNomeIgnoreCaseContaining(String nome);
}
