package com.challengedbc.demo.resources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challengedbc.demo.model.Associado;
import com.challengedbc.demo.model.Pauta;
import com.challengedbc.demo.model.SessaoVotacao;
import com.challengedbc.demo.repository.AssociadoRepository;
import com.challengedbc.demo.repository.PautaRepository;
import com.challengedbc.demo.repository.SessaoVotacaoRepository;

import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/api")
public class SessaoVotacaoResource {

	@Autowired
	SessaoVotacaoRepository sessaoRepository;
	@Autowired
	AssociadoRepository associadoRepository;
	@Autowired
	PautaRepository pautaRepository;
	List<String> primeirahora = new ArrayList();
	List<String> horaminutosarray = new ArrayList();
	int cont_sim = 0;
	int cont_nao = 0;
	int minutos_default;

	@PostMapping("/sessao")
	public String savePatient(@RequestBody SessaoVotacao sessao) throws ObjectNotFoundException, ParseException {
	
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		String horasistema = sdf.format(gc.getTime());
		primeirahora.add(horasistema);
		SimpleDateFormat formato = new SimpleDateFormat("hh:mm:ss");
		Date data_system = formato.parse(horasistema);
		if(sessao.getPauta().getMinutos()==0) {
			 minutos_default=60;
			
		}else {
			 minutos_default=sessao.getPauta().getMinutos();
			
		}
		gc.add(Calendar.SECOND, minutos_default);
		String horaminutos = sdf.format(gc.getTime());
		horaminutosarray.add(horaminutos);
		SimpleDateFormat formato1 = new SimpleDateFormat("hh:mm:ss");
		Date data_stop = formato1.parse(horaminutosarray.get(0));
		if (sessao.getPauta().isStatus() == false && data_system.getTime() >= data_stop.getTime()) {
			Pauta pauta = new Pauta();
			pauta.setId(sessao.getPauta().getId());
			pauta.setMinutos(sessao.getPauta().getMinutos());
			pauta.setStatus(true);
			pauta.setPauta(sessao.getPauta().getPauta());
			pautaRepository.save(pauta);
			List<SessaoVotacao> NVotacaoPauta = sessaoRepository.findByPauta(sessao.getPauta());
			for (SessaoVotacao v : NVotacaoPauta) {
				if (v.getPauta().getPauta().equalsIgnoreCase(sessao.getPauta().getPauta())
						&& v.getVoto().equalsIgnoreCase("sim")) {
					cont_sim = cont_sim + 1;
				}
				if (v.getPauta().getPauta().equalsIgnoreCase(sessao.getPauta().getPauta())
						&& v.getVoto().equalsIgnoreCase("nao")) {

					cont_nao = cont_nao + 1;
				}
			}
			horaminutosarray.clear();
			return "Sessão encerrada  !!! numero de votos sim "+ cont_sim +" nao "+cont_nao;
			
		} else {
			sessaoRepository.save(sessao);
			return "Sessão Aberta !!!";

		}

		// return null;

	}

}
