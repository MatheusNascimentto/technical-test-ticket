package com.manager.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.manager.user.domain.Endereco;
import com.manager.user.domain.Pessoa;
import jakarta.persistence.Embedded;
import org.hibernate.validator.constraints.Length;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

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
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.dataNascimento = obj.getDataNascimento();
        this.endereco = obj.getEndereco();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PessoaDTO pessoaDTO = (PessoaDTO) obj;
        return Objects.equals(id, pessoaDTO.id) &&
                Objects.equals(nome, pessoaDTO.nome) &&
                Objects.equals(cpf, pessoaDTO.cpf);
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
