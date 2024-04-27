package com.manager.user.service;


import com.manager.user.domain.Boleto;
import com.manager.user.repository.BoletoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;

import static com.manager.user.domain.StatusBoleto.PAGO;
import static com.manager.user.domain.StatusBoleto.PENDENTE;

@Service
public class DBservice implements CommandLineRunner {
    @Autowired
    private BoletoRepository boletoRepository;

    public void instanciaBaseDeDados() {

        // ====================== BOLETO =============================

        LocalDate localDateBoleto = LocalDate.now();

        Boleto b1 = new Boleto();
        b1.setPessoaId(1L);
        b1.setStatus(PAGO);
        b1.setValorPago(100.00);
        b1.setValor(100.00);
        b1.setDataVencimento(localDateBoleto);
        b1.setDataPagamento(localDateBoleto);

        Boleto b2 = new Boleto();
        b2.setPessoaId(1L);
        b2.setStatus(PENDENTE);
        b2.setValorPago(200.00);
        b2.setValor(200.00);
        b2.setDataVencimento(localDateBoleto);
        b2.setDataPagamento(localDateBoleto);

        this.boletoRepository.saveAll(Arrays.asList(b1, b2));

    }

    @Override
    public void run(String... args) throws Exception {

        //====================== BOLETO =============================

        LocalDate localDateBoleto = LocalDate.now();

        Boleto b1 = new Boleto();
        b1.setPessoaId(1L);
        b1.setStatus(PAGO);
        b1.setValorPago(100.00);
        b1.setValor(100.00);
        b1.setDataVencimento(localDateBoleto);
        b1.setDataPagamento(localDateBoleto);

        Boleto b2 = new Boleto();
        b1.setPessoaId(1L);
        b2.setStatus(PENDENTE);
        b2.setValorPago(200.00);
        b2.setValor(200.00);
        b2.setDataVencimento(localDateBoleto);
        b2.setDataPagamento(localDateBoleto);

        this.boletoRepository.saveAll(Arrays.asList(b1, b2));

    }
}
