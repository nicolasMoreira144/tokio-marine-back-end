package com.souza.tokiomarine.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.souza.tokiomarine.controller.TransferenciaController;
import com.souza.tokiomarine.dto.TransferenciaDTO;
import com.souza.tokiomarine.model.Transferencia;
import com.souza.tokiomarine.repository.TransferenciaRepository;

@Service
public class TransferenciaService {
	
	
	private static final Logger log = LoggerFactory.getLogger(TransferenciaController.class);
	
	@Autowired	
	private TransferenciaRepository transferenciaRepository;
	
	
	public Transferencia salvar(Transferencia transferencia) {
		try {
			transferencia.setValor(this.calculoTaxa(transferencia.getValor(), transferencia.getDia()));
		} catch (Exception e) {
			log.error("Exception : {}", e);
		}
		return transferenciaRepository.save(transferencia);		
	}
	
	public List<TransferenciaDTO> listar() {
		 return transferenciaRepository.findAll().stream().map(i -> new TransferenciaDTO(i)).collect(Collectors.toList());		
	}
	
	private BigDecimal calculoTaxa(BigDecimal valor, int dia) throws Exception {
		
		if(dia == 0) {
			valor = new BigDecimal(valor.doubleValue() - (valor.doubleValue() * (2.5 / 100)));
		}else if(dia > 1 && dia <= 10) {
			
		}else if(dia > 11 && dia <= 20) {
			valor = new BigDecimal(valor.doubleValue() - (valor.doubleValue() * (8.2 / 100)));
		}else if(dia > 21 && dia <= 30) {
			valor = new BigDecimal(valor.doubleValue() - (valor.doubleValue() * (6.9 / 100)));
		}else if(dia > 31 && dia <= 40) {
			valor = new BigDecimal(valor.doubleValue() - (valor.doubleValue() * (4.7 / 100)));
		}else if(dia > 41 && dia <= 50) {
			valor = new BigDecimal(valor.doubleValue() - (valor.doubleValue() * (1.7 / 100)));
		}else {
			throw new Exception("Dias fora do range de calculo.");
		}
	
		return valor;
	}

}
