package com.manager.user.feign;

import com.manager.user.domain.Boleto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "BoletoService", url = "/boleto")
public interface BoletoFeignClient {

    @GetMapping("/pessoa/{id}/boleto")
    List<Boleto> getBoletosPorPessoa(@PathVariable("id") Long id);

}
