package com.mercadolivre.mercadolivre.produto.pergunta;

public class SendEmail {
    private String enviadoPor;
    private String enviadoPara;
    private String titulo;
    private String corpoEmail;

    public SendEmail(String enviadoPor, String enviadoPara, String titulo, String corpoEmail) {
        this.enviadoPor = enviadoPor;
        this.enviadoPara = enviadoPara;
        this.titulo = titulo;
        this.corpoEmail = corpoEmail;
    }

    public String getEnviadoPor() {
        return enviadoPor;
    }

    public String getEnviadoPara() {
        return enviadoPara;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCorpoEmail() {
        return corpoEmail;
    }

    public SendEmail enviarEmail(){
        return this;
    }

    @Override
    public String toString() {
        return "SendEmail{" +
                "enviadoPor='" + enviadoPor + '\'' +
                ", enviadoPara='" + enviadoPara + '\'' +
                ", titulo='" + titulo + '\'' +
                ", corpoEmail='" + corpoEmail + '\'' +
                '}';
    }
}
