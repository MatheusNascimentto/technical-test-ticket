package com.manager.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.manager.user.domain.Endereco;
import com.manager.user.domain.Pessoa;
import jakarta.persistence.Embedded;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class PessoaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @Length(min =3, max = 50, message = "O Campo Nome deve ter entre 3 e 50 caracteres!")
    private String nome;

    private String cpf;

    @JsonFormat(pattern="dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataNascimento;
    @Embedded
    private Endereco endereco;

    public PessoaDTO() {
        super();
    }

    public PessoaDTO(Pessoa obj) {
        super();
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
