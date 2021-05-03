package com.challengedbc.demo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challengedbc.demo.model.Pauta;
import com.challengedbc.demo.repository.PautaRepository;

@RestController
@RequestMapping(value="/api")
public class PautaResource {
	
	@Autowired
	PautaRepository pautaRepository;
	
	@PostMapping("/pauta")
	public Pauta savePatient(@RequestBody Pauta pauta) {
		
		Pauta pautaObj=new Pauta();
		pautaObj.setPauta(pauta.getPauta());
		pautaObj.setStatus(false);
		pautaObj.setMinutos(pauta.getMinutos());
		return  pautaRepository.save(pautaObj);
		
	}
	
	
	
	

}
