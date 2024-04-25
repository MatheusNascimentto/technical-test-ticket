package com.manager.user.feign;

import com.manager.user.domain.Pessoa;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PessoaService", url = "/pessoa")
public interface PessoaFeignClient {

    @GetMapping("/pessoa/{id}")
    Pessoa getPessoaById(@PathVariable("id") Long id);

}
