package com.souza.tokiomarine.controller;


import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.souza.tokiomarine.dto.TransferenciaDTO;
import com.souza.tokiomarine.model.Transferencia;
import com.souza.tokiomarine.response.Response;
import com.souza.tokiomarine.service.TransferenciaService;

@RestController
@RequestMapping("transferencias")
public class TransferenciaController {

	private static final Logger log = LoggerFactory.getLogger(TransferenciaController.class);
	
	@Autowired
	private TransferenciaService transferenciaService;
	
	@CrossOrigin(origins = "http://localhost:5173")
	@PostMapping
	public ResponseEntity<Response<TransferenciaDTO>> salvar(@Valid @RequestBody TransferenciaDTO transferenciaDTO, BindingResult result) {
		Response<TransferenciaDTO> response = new Response<TransferenciaDTO>();
		
		response.setData(transferenciaDTO);

		Transferencia transferencia = this.converterDtoParaTransferencia(transferenciaDTO);

		if (result.hasErrors()) {
			log.error("Erro no agendamento: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		if(transferencia.getDia() > 50) {
			response.getErrors().add("Dias fora do range de calculo.");
			return ResponseEntity.badRequest().body(response);
		}
		
		this.transferenciaService.salvar(transferencia);

		response.setData(transferenciaDTO);

		return ResponseEntity.ok(response);
	}

	@CrossOrigin(origins = "http://localhost:5173")
	@GetMapping
	public ResponseEntity<List<TransferenciaDTO>> listar() {
		List<TransferenciaDTO> transferenciaDTO = transferenciaService.listar();

		return ResponseEntity.ok(transferenciaDTO);
	}
	
	
	private Transferencia converterDtoParaTransferencia(TransferenciaDTO transferenciaDTO) {
		Transferencia transferencia = new Transferencia();
		transferencia.setContaDestino(transferenciaDTO.getContaDestino());
		transferencia.setContaOrigem(transferenciaDTO.getContaOrigem());
		transferencia.setDataAgendamento(transferenciaDTO.getDataAgendamento());
		transferencia.setDataTransferencia(transferenciaDTO.getDataTransferencia());
		transferencia.setDia(transferenciaDTO.getDia());
		transferencia.setValor(transferenciaDTO.getValor());
		return transferencia;
	}

}
