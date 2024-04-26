package com.manager.user.controller;

import com.manager.user.domain.Pessoa;
import com.manager.user.dto.PessoaDTO;
import com.manager.user.service.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PessoaControllerTest {

    @Mock
    private PessoaService pessoaService;

    @InjectMocks
    private PessoaController pessoaController;

    Pessoa pessoa;

    @BeforeEach
    public void setUp() {
        pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Matheus");
        pessoa.setCpf("12345678900");
    }

    @Test
    void deveBuscarPessoaPorIdComSucesso() {
        when(pessoaService.findById(1L)).thenReturn(pessoa);
        ResponseEntity<Pessoa> response = pessoaController.findById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pessoa, response.getBody());
        verify(pessoaService, times(1)).findById(1L);
    }

    @Test
    void deveBuscarTodasPessoasComSucesso() {
        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(pessoa);
        when(pessoaService.findAll()).thenReturn(pessoas);
        ResponseEntity<List<PessoaDTO>> response = pessoaController.findAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(pessoaService, times(1)).findAll();
    }

    @Test
    void deveCadastrarPessoaComSucesso() {
        when(pessoaService.create(pessoa)).thenReturn(pessoa);
        URI location = URI.create("/pessoa/1");
        ResponseEntity<Pessoa> response = pessoaController.create(pessoa);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(location, response.getHeaders().getLocation());
        verify(pessoaService, times(1)).create(pessoa);
    }

    @Test
    void deveBuscarPessoaEAtualizarComSucesso() {
        PessoaDTO pessoaDTO = new PessoaDTO(pessoa);
        when(pessoaService.update(1L, pessoaDTO)).thenReturn(pessoa);
        ResponseEntity<PessoaDTO> response = pessoaController.update(1L, pessoaDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(pessoaDTO, response.getBody());
        verify(pessoaService, times(1)).update(1L, pessoaDTO);
    }

    @Test
    void deveBuscarPessoaEDeletarComSucesso() {
        ResponseEntity<Void> response = pessoaController.delete(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(pessoaService, times(1)).delete(1L);
    }

}
