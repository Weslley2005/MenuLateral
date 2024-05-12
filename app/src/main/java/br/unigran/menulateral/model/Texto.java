package br.unigran.menulateral.model;

import java.io.Serializable;

public class Texto implements Serializable {
    private final String nome;
    private final String descricao;

    public Texto(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
