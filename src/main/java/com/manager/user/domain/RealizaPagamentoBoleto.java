package com.manager.user.domain;

public class RealizaPagamentoBoleto {

    private String mensagem;
    //private boolean pagamentoEfetuado;

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
