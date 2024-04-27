package com.manager.user.controller;

import com.manager.user.domain.Boleto;
import com.manager.user.domain.Pessoa;
import com.manager.user.domain.RealizaPagamentoBoleto;
import com.manager.user.domain.StatusBoleto;
import com.manager.user.dto.BoletoDTO;
import com.manager.user.service.BoletoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.net.URI;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BoletoControllerTest {

    @Mock
    private BoletoService boletoService;

    @InjectMocks
    private BoletoController boletoController;

    Boleto boleto;

    Pessoa pessoa;

    @BeforeEach
    public void setUp() {
        pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Matheus");
        pessoa.setCpf("12345678900");

        boleto = new Boleto();
        boleto.setId(1L);
        boleto.setValor(100.0);
        boleto.setValorPago(0.0);
        boleto.setDataPagamento(null);
        boleto.setDataVencimento(LocalDate.now());
        boleto.setStatus(StatusBoleto.PENDENTE);
        boleto.setPessoaId(1L);

    }

    @Test
    public void deveBuscarBoletoPorIdComSucesso() {
        Long id = 1L;
        Boleto boleto = new Boleto();
        boleto.setId(id);

        when(boletoService.findById(id)).thenReturn(boleto);

        ResponseEntity<Boleto> response = boletoController.findById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(boleto, response.getBody());
        verify(boletoService, times(1)).findById(id);
    }

    @Test
    public void deveBuscarTodosBoletosComSucesso() {
        Boleto boleto1 = new Boleto();
        Boleto boleto2 = new Boleto();
        List<Boleto> boletos = Arrays.asList(boleto1, boleto2);

        when(boletoService.findAll()).thenReturn(boletos);

        ResponseEntity<List<BoletoDTO>> response = boletoController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(boletoService, times(1)).findAll();
    }

    @Test
    void deveCadastrarBoletoComSucesso() {
        when(boletoService.create(boleto)).thenReturn(boleto);
        URI location = URI.create("/boleto/1");
        ResponseEntity<Boleto> response = boletoController.create(boleto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(location, response.getHeaders().getLocation());
        verify(boletoService, times(1)).create(boleto);
    }

    @Test
    public void deveBuscarBoletoEAtualizarComSucesso() {
        Long id = 1L;
        BoletoDTO boletoDTO = new BoletoDTO();
        Boleto boleto = new Boleto();
        when(boletoService.update(id, boletoDTO)).thenReturn(boleto);
        ResponseEntity<BoletoDTO> response = boletoController.update(id, boletoDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(new BoletoDTO(boleto), response.getBody());
        verify(boletoService, times(1)).update(id, boletoDTO);
    }

    @Test
    public void deveVerificarSeFoiRealizadoPagamentoBoletoComSucesso() {
        Long id = 1L;
        double valorPago = 100.0;
        LocalDate dataPagamento = LocalDate.now();
        RealizaPagamentoBoleto realizaPagamento = new RealizaPagamentoBoleto("Pagamento realizado com sucesso.");
        when(boletoService.realizarPagamento(id, valorPago, dataPagamento)).thenReturn(realizaPagamento);

        ResponseEntity<RealizaPagamentoBoleto> response = boletoController.realizarPagamento(id, valorPago, dataPagamento);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(realizaPagamento, response.getBody());
        verify(boletoService, times(1)).realizarPagamento(id, valorPago, dataPagamento);
    }

    @Test
    public void deveBuscarBoletoEDeletarComSucesso() {
        Long id = 1L;
        ResponseEntity<Void> response = boletoController.delete(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(boletoService, times(1)).delete(id);
    }

}
