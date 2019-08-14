package br.com.cadastrocliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({"br.com.cadastrocliente.resources","br.com.cadastrocliente.services","br.com.cadastrocliente.repositories","br.com.cadastrocliente.domain","br.com.cadastrocliente"})
public class CadastroClienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroClienteApplication.class, args);
	}

}
