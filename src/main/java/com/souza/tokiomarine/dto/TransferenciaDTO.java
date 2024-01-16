package com.souza.tokiomarine.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.souza.tokiomarine.model.Transferencia;

public class TransferenciaDTO {

	@NotBlank(message = "campo conta origem não pode ser nulo")
	private String contaOrigem;

	@NotBlank(message = "campo conta destino não pode ser nulo")
	private String contaDestino;

	@NotNull(message = "campo data transferência não pode ser nulo")
	private LocalDate dataTransferencia;

	private LocalDate dataAgendamento;

	@NotNull(message = "campo valor não pode ser nulo")
	private BigDecimal valor;
	
	@NotNull(message = "campo dia não pode ser nulo")
	private int dia;
	
	public TransferenciaDTO() {

	}

	public TransferenciaDTO(Transferencia transferencia) {
		this.contaDestino = transferencia.getContaDestino();
		this.contaOrigem = transferencia.getContaOrigem();
		this.dataAgendamento = LocalDate.now();
		this.dataTransferencia = transferencia.getDataTransferencia();
		this.dia = transferencia.getDia();
		this.valor = transferencia.getValor();
	}

	public String getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(String contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public String getContaDestino() {
		return contaDestino;
	}

	public void setContaDestino(String contaDestino) {
		this.contaDestino = contaDestino;
	}

	public LocalDate getDataTransferencia() {
		return dataTransferencia;
	}

	public void setDataTransferencia(LocalDate dataTransferencia) {
		this.dataTransferencia = dataTransferencia;
	}

	public LocalDate getDataAgendamento() {
		return dataAgendamento;
	}

	public void setDataAgendamento(LocalDate dataAgendamento) {
		this.dataAgendamento = dataAgendamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

}
