package br.com.cadastrocliente.services.validation;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cadastrocliente.dto.ClienteNewDTO;
import br.com.cadastrocliente.repositories.CidadeRepository;
import br.com.cadastrocliente.resources.excetion.FieldMessage;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	CidadeRepository cidadeRepository;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();
		if(objDto.getCidade() != null && cidadeRepository.findById(objDto.getCidade().getId()) == null) {
			list.add(new FieldMessage("cidade", "Cidade não existe"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
			
			
