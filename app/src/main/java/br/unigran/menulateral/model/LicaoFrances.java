package br.unigran.menulateral.model;

public class LicaoFrances {
    private String titulo1;
    private String conteudo1;

    public LicaoFrances(String titulo, String conteudo) {
        this.titulo1 = titulo;
        this.conteudo1 = conteudo;
    }

    public String getTitulo() {
        return titulo1;
    }

    public String getConteudo() {
        return conteudo1;
    }
}
