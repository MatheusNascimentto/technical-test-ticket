package com.manager.user.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CausaErroDto {

    private String campo;
    private String valor;

    public CausaErroDto() {}

    public CausaErroDto(String campo, String valor) {
        this.campo = campo;
        this.valor = valor;
    }

}
