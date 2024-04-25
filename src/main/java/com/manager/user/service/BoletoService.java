package com.manager.user.service;

import com.manager.user.domain.Boleto;
import com.manager.user.domain.Pessoa;
import com.manager.user.dto.BoletoDTO;
import com.manager.user.repository.BoletoRepository;
import com.manager.user.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoletoService {

    @Autowired
    private BoletoRepository repository;

    public Boleto findById(Long id) {
        Optional<Boleto> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pessoa.class.getName()));
    }

    public List<Boleto> findAll() {
        return repository.findAll();
    }


    public Boleto create(Boleto obj) {
        obj.setId(null);
        return repository.save(obj);

    }

    public Boleto update(Long id, BoletoDTO objDto) {
        Boleto obj = findById(id);
        obj.setValor(objDto.getValor());
        obj.setValorPago(objDto.getValorPago());
        obj.setDataPagamento(objDto.getDataPagamento());
        obj.setDataVencimento(objDto.getDataVencimento());
        obj.setStatus(objDto.getStatus());
        obj.setPessoa(objDto.getPessoa());

        return repository.save(obj);
    }

    public void delete(Long id) {
        Boleto obj = findById(id);
        repository.delete(obj);
    }

}
