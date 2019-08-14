package br.com.cadastrocliente.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.cadastrocliente.domain.Cidade;
import br.com.cadastrocliente.domain.Cliente;
import br.com.cadastrocliente.domain.Estado;
import br.com.cadastrocliente.domain.enums.TipoSexo;
import br.com.cadastrocliente.repositories.CidadeRepository;
import br.com.cadastrocliente.repositories.ClienteRepository;
import br.com.cadastrocliente.repositories.EstadoRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void run(String... args) throws Exception {

		
		estadoRepository.deleteAll();
		cidadeRepository.deleteAll();
		clienteRepository.deleteAll();
		
		Estado e1 = new Estado(null, "Pernambuco");
		Estado e2 = new Estado(null, "São Paulo");
		Estado e3 = new Estado(null, "Rio de Janeiro");
		Estado e4 = new Estado(null, "Ceará");
		Estado e5 = new Estado(null, "Amazonas");
		estadoRepository.saveAll(Arrays.asList(e1,e2,e3,e4,e5));
		
		Cidade c1 = new Cidade(null, "Recife", e1);
		Cidade c2 = new Cidade(null, "Olinda", e1);
		Cidade c3 = new Cidade(null, "Campinas", e2);
		Cidade c4 = new Cidade(null, "Copacabana", e3);
		Cidade c5 = new Cidade(null, "Fortaleza", e4);
		Cidade c6 = new Cidade(null, "Manaus", e5);
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3,c4,c5,c6));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Cliente cli = new Cliente(null, "João Rafael Alvarez Toscano de Barros", sdf.parse("27/02/1996"), 23, TipoSexo.HOMEM, c1);
		
		clienteRepository.saveAll(Arrays.asList(cli));
		

	}

}
