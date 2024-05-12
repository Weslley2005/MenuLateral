package br.unigran.menulateral.telas;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.unigran.menulateral.R;

public class LicaoFrances extends AppCompatActivity {

    private Licao[] licoes;
    private int indiceAtual;
    private int pontuacao;

    private TextView tituloTextView;
    private TextView conteudoTextView;
    private Button proximaLicaoButton;

    private static class Licao {
        private String titulo;
        private String conteudo;

        public Licao(String titulo, String conteudo) {
            this.titulo = titulo;
            this.conteudo = conteudo;
        }

        public String getTitulo() {
            return titulo;
        }

        public String getConteudo() {
            return conteudo;
        }
    }

    DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_licao_frances);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        tituloTextView = findViewById(R.id.tituloTextView1);
        conteudoTextView = findViewById(R.id.conteudoTextView1);
        proximaLicaoButton = findViewById(R.id.proximaLicaoButton1);

        licoes = new Licao[]{
                new Licao("Saudações Básicas", "Salut, Bonjour"),
                new Licao("Números de 1 a 10 ", "1-un    2-deux    3-trois    4-quatre    5-cinq    6-six    7-sept    8-huit    9-neuf    10-dix")
        };

        pontuacao = 0;
        indiceAtual = 0;

        exibirLicaoAtual();

        proximaLicaoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (indiceAtual < licoes.length - 1) {
                    indiceAtual++;
                    exibirLicaoAtual();

                } else {
                }
            }
        });
    }

    private void exibirLicaoAtual() {
        Licao licaoAtual = licoes[indiceAtual];
        tituloTextView.setText(licaoAtual.getTitulo());
        conteudoTextView.setText(licaoAtual.getConteudo());
    }

}
