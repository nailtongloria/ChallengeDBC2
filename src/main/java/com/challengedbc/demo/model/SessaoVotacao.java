package com.challengedbc.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SessaoVotacao {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String voto;
	@ManyToOne(cascade = CascadeType.MERGE)
	private Associado associado;
	@ManyToOne(cascade = CascadeType.MERGE)
	private Pauta pauta;
	
	public SessaoVotacao() {
	
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVoto() {
		return voto;
	}
	public void setVoto(String voto) {
		this.voto = voto;
	}
	public Associado getAssociado() {
		return associado;
	}
	public void setAssociado(Associado associado) {
		this.associado = associado;
	}
	public Pauta getPauta() {
		return pauta;
	}
	public void setPauta(Pauta pauta) {
		this.pauta = pauta;
	}
	

}
