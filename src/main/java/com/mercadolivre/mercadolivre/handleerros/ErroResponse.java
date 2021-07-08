package com.mercadolivre.mercadolivre.handleerros;

public class ErroResponse {
    private String campo;
    private String msg;

    public ErroResponse(String campo, String msg) {
        this.campo = campo;
        this.msg = msg;
    }

    public String getCampo() {
        return campo;
    }

    public String getMsg() {
        return msg;
    }
}
