package com.manager.user.service;


import com.manager.user.domain.Pessoa;
import com.manager.user.dto.PessoaDTO;
import com.manager.user.repository.PessoaRepository;
import com.manager.user.service.exception.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {

    @InjectMocks
    PessoaService pessoaService;
    @Mock
    PessoaRepository pessoaRepository;
    Pessoa pessoa;

    @BeforeEach
    public void setUp() {
        pessoa = mock(Pessoa.class);
    }

    @Test
    public void deveBuscarPessoaPorIdComSucesso() {
        Long id = 1L;
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        pessoa.setNome("João");
        when(pessoaRepository.findById(id)).thenReturn(Optional.of(pessoa));
        Pessoa result = pessoaService.findById(id);

        assertEquals(id, result.getId());
        assertEquals("João", result.getNome());
    }

    @Test
    public void deveBuscarVerificarSeIdEstaValido() {
        Long id = 1L;
        when(pessoaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> {
            pessoaService.findById(id);
        });
    }

    @Test
    public void deveBuscarTodasPessoasComSucesso() {
        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(new Pessoa());
        pessoas.add(new Pessoa());
        when(pessoaRepository.findAll()).thenReturn(pessoas);
        List<Pessoa> result = pessoaService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    public void deveCadastrarPessoaComSucesso() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Matheus");
        when(pessoaRepository.save(pessoa)).thenAnswer(invocation -> {
            Pessoa savedPessoa = invocation.getArgument(0);
            savedPessoa.setId(1L);
            return savedPessoa;
        });
        Pessoa result = pessoaService.create(pessoa);

        assertNotNull(result.getId(), "O ID não deve ser nulo");
        assertTrue(result.getId() > 0, "O ID deve ser maior que zero");
        assertEquals("Matheus", result.getNome());
    }

    @Test
    public void deveBuscarPessoaEAtualizarComSucesso() {
        Long id = 1L;
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setNome("Matheus");

        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        pessoa.setNome("Matheus Nascimento");

        when(pessoaRepository.findById(id)).thenReturn(Optional.of(pessoa));
        when(pessoaRepository.save(pessoa)).thenReturn(pessoa);
        Pessoa updatedPessoa = pessoaService.update(id, pessoaDTO);

        assertEquals(id, updatedPessoa.getId());
        assertEquals("Teste Atualizado", updatedPessoa.getNome());
    }

    @Test
    public void deveBuscarPessoaEDeletarComSucesso() {
        Long id = 1L;
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        when(pessoaRepository.findById(id)).thenReturn(Optional.of(pessoa));
        pessoaService.delete(id);

        verify(pessoaRepository, times(1)).delete(pessoa);
    }

    @Test
    public void deveBuscarExclusaoEVerificarSeNaoEstaNula() {
        Long id = 1L;
        when(pessoaRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundException.class, () -> {
            pessoaService.delete(id);
        });

        verify(pessoaRepository, times(1)).findById(id);
        verify(pessoaRepository, never()).delete(any(Pessoa.class));
    }

}
