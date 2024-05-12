package br.unigran.menulateral.model;

import android.media.Image;

import com.google.firebase.database.ValueEventListener;

public class Tarefa {
    private String resposta;
    private Image img;

    public static void addValueEventListener(ValueEventListener erroDeConexão) {
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public String toString() {
        return resposta ;
    }
}
