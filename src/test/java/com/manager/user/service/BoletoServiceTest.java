package com.manager.user.service;

import com.manager.user.domain.Boleto;
import com.manager.user.domain.RealizaPagamentoBoleto;
import com.manager.user.domain.StatusBoleto;
import com.manager.user.dto.BoletoDTO;
import com.manager.user.repository.BoletoRepository;
import com.manager.user.service.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BoletoServiceTest {

    @InjectMocks
    BoletoService boletoService;
    @Mock
    BoletoRepository boletoRepository;
    Boleto boleto;

    @BeforeEach
    public void setUp() {
        boleto = mock(Boleto.class);
    }

    @Test
    public void deveBuscarBoletosPorIdComSucesso() {
        Long id = 1L;
        Boleto boleto = new Boleto();
        boleto.setId(id);
        boleto.setValor(100.0);

        when(boletoRepository.findById(id)).thenReturn(Optional.of(boleto));

        Boleto result = boletoService.findById(id);

        assertEquals(id, result.getId());
        assertEquals(100.0, result.getValor());
    }

    @Test
    public void deveBuscarVerificarSeIdEstaValido() {
        Long id = 1L;

        when(boletoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> {
            boletoService.findById(id);
        });
    }

    @Test
    public void deveBuscarTodosBoletosComSucesso() {
        List<Boleto> boleto = new ArrayList<>();
        boleto.add(new Boleto());
        boleto.add(new Boleto());

        when(boletoRepository.findAll()).thenReturn(boleto);

        List<Boleto> result = boletoService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    public void deveCadastrarBoletoComSucesso() {
        Boleto boleto = new Boleto();
        boleto.setValor(100.0);

        when(boletoRepository.save(boleto)).thenAnswer(invocation -> {
            Boleto savedBoleto = invocation.getArgument(0);
            savedBoleto.setId(1L);
            return savedBoleto;
        });

        Boleto result = boletoService.create(boleto);

        assertNotNull(result.getId());
        assertEquals(boleto, result);
    }

    @Test
    public void deveBuscarBoletoEAtualizarComSucesso() {
        Long id = 1L;
        BoletoDTO boletoDTO = new BoletoDTO();
        boletoDTO.setValor(150.0);
        boletoDTO.setValorPago(150.0);
        boletoDTO.setDataPagamento(LocalDate.now());
        boletoDTO.setDataVencimento(LocalDate.now().plusDays(5));
        boletoDTO.setStatus(StatusBoleto.PENDENTE);
        Boleto boleto = new Boleto();
        boleto.setId(id);
        boleto.setValor(100.0);

        when(boletoRepository.findById(id)).thenReturn(Optional.of(boleto));
        when(boletoRepository.save(any(Boleto.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Boleto result = boletoService.update(id, boletoDTO);

        assertEquals(id, result.getId());
        assertEquals(150.0, result.getValor());
        assertEquals(150.0, result.getValorPago());
        assertEquals(LocalDate.now(), result.getDataPagamento());
        assertEquals(LocalDate.now().plusDays(5), result.getDataVencimento());
        assertEquals(StatusBoleto.PENDENTE, result.getStatus());
        verify(boletoRepository, times(1)).findById(id);
    }

    @Test
    public void deveBuscarBoletoEDeletarComSucesso() {
        Long id = 1L;
        Boleto boleto = new Boleto();
        boleto.setId(id);
        when(boletoRepository.findById(id)).thenReturn(Optional.of(boleto));

        boletoService.delete(id);

        verify(boletoRepository, times(1)).findById(id);
        verify(boletoRepository, times(1)).delete(boleto);
    }

    @Test
    public void deveBuscarExclusaoEVerificarSeNaoEstaNula() {
        Long id = 1L;
        when(boletoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> {
            boletoService.delete(id);
        });
        verify(boletoRepository, times(1)).findById(id);
        verify(boletoRepository, never()).delete(any(Boleto.class));
    }

    @Test
    public void deveVerificarSeFoiRealizadoPagamentoBoletoComSucesso() {
        Long id = 1L;
        Boleto boleto = new Boleto();
        boleto.setId(id);
        boleto.setStatus(StatusBoleto.PENDENTE);

        when(boletoRepository.save(any(Boleto.class))).thenReturn(boleto);
        when(boletoRepository.findById(id)).thenReturn(Optional.of(boleto));

        LocalDate dataPagamento = LocalDate.now();
        double valorPago = 100.0;

        RealizaPagamentoBoleto result = boletoService.realizarPagamento(id, valorPago, dataPagamento);

        assertEquals("Pagamento realizado com sucesso.", result.getMensagem());
        assertEquals(StatusBoleto.PAGO, boleto.getStatus());
        assertEquals(dataPagamento, boleto.getDataPagamento());
        assertEquals(valorPago, boleto.getValorPago());
    }

    @Test
    public void deveVerificarSePagamentoBoletoJaFoiPago() {
        Long id = 1L;
        Boleto boleto = new Boleto();
        boleto.setId(id);
        boleto.setStatus(StatusBoleto.PAGO);

        when(boletoRepository.findById(id)).thenReturn(Optional.of(boleto));

        LocalDate dataPagamento = LocalDate.now();
        double valorPago = 100.0;

        RealizaPagamentoBoleto result = boletoService.realizarPagamento(id, valorPago, dataPagamento);

        assertEquals("Boleto já foi pago.", result.getMensagem());
        assertEquals(StatusBoleto.PAGO, boleto.getStatus());
        assertNull(boleto.getDataPagamento());
        assertEquals(0.0, boleto.getValorPago());
    }

    @Test
    public void deveVerificarSePagamentoBoletoNaoEstaNulo() {
        Long id = 1L;

        when(boletoRepository.findById(id)).thenReturn(Optional.empty());

        LocalDate dataPagamento = LocalDate.now();
        double valorPago = 100.0;

        assertThrows(RuntimeException.class, () -> {
            boletoService.realizarPagamento(id, valorPago, dataPagamento);
        });
    }

}
