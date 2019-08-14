package br.com.cadastrocliente.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.cadastrocliente.domain.Cidade;
import br.com.cadastrocliente.dto.CidadeDTO;
import br.com.cadastrocliente.services.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {

	@Autowired
	CidadeService service;

	@RequestMapping(value = "/{nome}", method = RequestMethod.GET)
	public ResponseEntity<List<Cidade>> find(@PathVariable String nome) {
		List<Cidade> cidades = service.findByNome(nome);
		return ResponseEntity.ok().body(cidades);
	}
	
	@RequestMapping(value = "/estado/{nome}", method = RequestMethod.GET)
	public ResponseEntity<List<Cidade>> findByEstado(@PathVariable String nome) {
		List<Cidade> cidades = service.findByEstado(nome);
		return ResponseEntity.ok().body(cidades);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CidadeDTO newObj) {
		Cidade obj = service.fromDTO(newObj);
		obj = service.insert(obj);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

}
