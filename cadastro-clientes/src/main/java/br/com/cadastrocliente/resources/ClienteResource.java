package br.com.cadastrocliente.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.cadastrocliente.domain.Cliente;
import br.com.cadastrocliente.dto.ClienteDTO;
import br.com.cadastrocliente.dto.ClienteNewDTO;
import br.com.cadastrocliente.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/id/{id}",  method=RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		Cliente c = service.find(id);
		return ResponseEntity.ok().body(c);
	}
	
	@RequestMapping(value="/nome",method=RequestMethod.GET)
	public ResponseEntity<List<Cliente>> findByNomeCompleto(@RequestBody ClienteDTO objDto) {
		Cliente obj = service.fromDTO(objDto);
		List<Cliente> clienteList = service.findByNomeCompleto(obj.getNomeCompleto());
		return ResponseEntity.ok().body(clienteList);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto){
		Cliente obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto){
		Cliente obj = service.fromDTO(objDto);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}",  method=RequestMethod.DELETE)
	public ResponseEntity<Cliente> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> listCliente = service.findAll();
		List<ClienteDTO> listDto = listCliente
				.stream().map(obj -> new ClienteDTO(obj))
				.collect(Collectors.toList()); 
		
		return ResponseEntity.ok().body(listDto);
	}

	
}
