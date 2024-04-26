package com.manager.user.controller;

import com.manager.user.domain.Boleto;
import com.manager.user.domain.RealizaPagamentoBoleto;
import com.manager.user.dto.BoletoDTO;
import com.manager.user.service.BoletoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.time.LocalDate;
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
        obj = boletoService.create(obj);
        URI location = URI.create("/boleto/" + obj.getId());
        return ResponseEntity.created(location).body(obj);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<BoletoDTO> update(@Valid @PathVariable Long id, @RequestBody BoletoDTO objDto) {
        Boleto newObj = boletoService.update(id, objDto);
        return ResponseEntity.ok().body(new BoletoDTO(newObj));
    }

    @PutMapping(value = "/pagamento/{id}")
    public ResponseEntity<RealizaPagamentoBoleto> realizarPagamento(@PathVariable Long id,
                                                                    @RequestParam double valorPago,
                                                                    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataPagamento) {

        RealizaPagamentoBoleto response = boletoService.realizarPagamento(id, valorPago, dataPagamento);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boletoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
