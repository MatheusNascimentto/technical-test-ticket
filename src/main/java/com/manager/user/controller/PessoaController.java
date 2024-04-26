package com.manager.user.controller;

import com.manager.user.domain.Pessoa;
import com.manager.user.dto.PessoaDTO;
import com.manager.user.feign.PessoaFeignClient;
import com.manager.user.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value= "/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PessoaFeignClient pessoaClient;


    @GetMapping(value = "/{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable Long id) {
        Pessoa obj = pessoaService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<PessoaDTO>> findAll() {
        List<Pessoa>list = pessoaService.findAll();
        List<PessoaDTO> listDTO = list.stream().map(obj -> new PessoaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<Pessoa> create(@Valid @RequestBody Pessoa obj) {
        obj = pessoaService.create(obj);
        URI location = URI.create("/pessoa/" + obj.getId());
        return ResponseEntity.created(location).body(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PessoaDTO> update(@Valid @PathVariable Long id, @RequestBody PessoaDTO objDto) {
        Pessoa newObj = pessoaService.update(id, objDto);
        return ResponseEntity.ok().body(new PessoaDTO(newObj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pessoaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
