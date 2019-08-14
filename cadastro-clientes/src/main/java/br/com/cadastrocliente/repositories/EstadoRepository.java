package br.com.cadastrocliente.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.cadastrocliente.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado,Integer>{
	
	@Transactional(readOnly=true)
	Optional<Estado> findByNomeIgnoreCase(String nome);
}
