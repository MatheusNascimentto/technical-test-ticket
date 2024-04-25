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
public interface PessoaService {


    @Autowired
    PessoaRepository repository = null;

    public default Pessoa findById(Long id) {
        Optional<Pessoa> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pessoa.class.getName()));
    }

    public default List<Pessoa> findAll() {
        return repository.findAll();
    }


    public default Pessoa create(Pessoa obj) {
        obj.setId(null);
        return repository.save(obj);

    }

    public default Pessoa update(Long id, PessoaDTO objDto) {
        Pessoa obj = findById(id);
        obj.setNome(objDto.getNome());
        obj.setCpf(objDto.getCpf());
        obj.setDataNascimento(objDto.getDataNascimento());
        obj.setEndereco(objDto.getEndereco());

        return repository.save(obj);
    }

    public default void delete(Long id) {
        Pessoa obj = findById(id);
        repository.delete(obj);
    }


}
