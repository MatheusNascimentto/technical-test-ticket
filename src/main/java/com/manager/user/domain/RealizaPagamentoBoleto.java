package com.manager.user.domain;

public class RealizaPagamentoBoleto {

    private String mensagem;

    public RealizaPagamentoBoleto() {
    }

    public RealizaPagamentoBoleto(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }


}
