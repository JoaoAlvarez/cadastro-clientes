package br.com.cadastrocliente.services.validation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cadastrocliente.domain.Cidade;
import br.com.cadastrocliente.domain.Estado;
import br.com.cadastrocliente.dto.CidadeDTO;
import br.com.cadastrocliente.repositories.CidadeRepository;
import br.com.cadastrocliente.repositories.EstadoRepository;
import br.com.cadastrocliente.resources.excetion.FieldMessage;

public class CidadeDTOInsertValidator implements ConstraintValidator<CidadeDTOInsert, CidadeDTO> {

	@Autowired
	EstadoRepository estadoRepository;
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	@Override
	public void initialize(CidadeDTOInsert ann) {
	}

	@Override
	public boolean isValid(CidadeDTO obj, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();
		
		Optional<Estado> estado = estadoRepository.findById(obj.getEstado().getId());
		if (!estado.isPresent()) {
			list.add(new FieldMessage("estado", "Estado não existe"));
		}else {
			List<Cidade> cidadesList = cidadeRepository.findByNomeIgnoreCaseContaining(obj.getNome()).orElse(new ArrayList<Cidade>());
			Iterator<Cidade> i = cidadesList.iterator();
			while(i.hasNext()) {
				if (i.next().getEstado().getId() == estado.get().getId()) {
					list.add(new FieldMessage("nome", "Cidade já cadastrada nesse estado"));
				}
			}
			
		}

		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
			
			
