package com.manager.user.controller;

import com.manager.user.domain.Boleto;
import com.manager.user.domain.Pessoa;
import com.manager.user.dto.BoletoDTO;
import com.manager.user.dto.PessoaDTO;
import com.manager.user.service.BoletoService;
import com.manager.user.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value= "/boleto")
public class BoletoController {

    @Autowired
    private BoletoService boletoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Boleto> findById(@PathVariable Long id) {
        Boleto obj = boletoService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<BoletoDTO>> findAll() {
        List<Boleto>list = boletoService.findAll();
        List<BoletoDTO> listDTO = list.stream().map(obj -> new BoletoDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<Boleto> create(@Valid @RequestBody Boleto obj) {
        obj= boletoService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BoletoDTO> update(@Valid @PathVariable Long id, @RequestBody BoletoDTO objDto) {
        Boleto newObj = boletoService.update(id, objDto);
        return ResponseEntity.ok().body(new BoletoDTO(newObj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boletoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
