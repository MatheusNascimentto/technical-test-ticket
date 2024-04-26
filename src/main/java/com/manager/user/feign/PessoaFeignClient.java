package com.manager.user.feign;

import com.manager.user.domain.Pessoa;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PessoaService", url = "http://localhost:8080/pessoa")
public interface PessoaFeignClient {

    @GetMapping("/pessoa/{id}")
    List<Pessoa> getPessoaById(@PathVariable("id") Long id);

}
