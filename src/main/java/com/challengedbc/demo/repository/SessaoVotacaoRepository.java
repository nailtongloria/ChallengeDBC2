package com.challengedbc.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.challengedbc.demo.model.Pauta;
import com.challengedbc.demo.model.SessaoVotacao;

public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao,Integer> {
	  List<SessaoVotacao> findByPauta(Pauta pauta);
	  
	

}
