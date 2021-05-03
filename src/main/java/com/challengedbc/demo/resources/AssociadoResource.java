package com.challengedbc.demo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challengedbc.demo.model.Associado;
import com.challengedbc.demo.model.Pauta;
import com.challengedbc.demo.repository.AssociadoRepository;
import com.challengedbc.demo.repository.PautaRepository;

@RestController
@RequestMapping(value="/api")
public class AssociadoResource {
	@Autowired
	AssociadoRepository associadoRepository;
	
	@PostMapping("/associado")
	public Associado saveAssociado(@RequestBody Associado associado) {
		return associadoRepository.save(associado);	
	}
	

}
