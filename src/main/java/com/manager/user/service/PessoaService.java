package com.manager.user.service;

import com.manager.user.domain.Pessoa;
import com.manager.user.dto.PessoaDTO;
import com.manager.user.service.exception.ObjectNotFoundException;
import com.manager.user.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository repository;

    public Pessoa findById(Long id) {
        Optional<Pessoa> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pessoa.class.getName()));
    }

    public List<Pessoa> findAll() {
        return repository.findAll();
    }

    public Pessoa create(Pessoa obj) {
        obj.setId(null);
        return repository.save(obj);

    }

    public Pessoa update(Long id, PessoaDTO objDto) {
        Pessoa obj = findById(id);
        obj.setNome(objDto.getNome());
        obj.setCpf(objDto.getCpf());
        obj.setDataNascimento(objDto.getDataNascimento());
        obj.setEndereco(objDto.getEndereco());

        return repository.save(obj);
    }
    public void delete(Long id) {
        Pessoa obj = findById(id);
        repository.delete(obj);
    }

}
