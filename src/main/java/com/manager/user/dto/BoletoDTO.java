package com.manager.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.manager.user.domain.Boleto;
import com.manager.user.domain.Pessoa;
import com.manager.user.domain.StatusBoleto;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class BoletoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double valor;
    private double valorPago;

    @JsonFormat(pattern="dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataVencimento;

    @JsonFormat(pattern="dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataPagamento;
    private StatusBoleto status;

    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;

    public BoletoDTO() {
    }

    public BoletoDTO(Boleto obj) {
        this.id = obj.getId();
        this.valor = obj.getValor();
        this.valorPago = obj.getValorPago();
        this.dataVencimento = obj.getDataVencimento();
        this.dataPagamento = obj.getDataPagamento();
        this.status = obj.getStatus();
        this.pessoa = obj.getPessoa();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BoletoDTO boletoDTO = (BoletoDTO) obj;
        return Objects.equals(id, boletoDTO.id) &&
                Objects.equals(valor, boletoDTO.valor) &&
                Objects.equals(valorPago, boletoDTO.valorPago) &&
                Objects.equals(dataPagamento, boletoDTO.dataPagamento) &&
                Objects.equals(dataVencimento, boletoDTO.dataVencimento) &&
                Objects.equals(status, boletoDTO.status) &&
                Objects.equals(pessoa, boletoDTO.pessoa);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public StatusBoleto getStatus() {
        return status;
    }

    public void setStatus(StatusBoleto status) {
        this.status = status;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
