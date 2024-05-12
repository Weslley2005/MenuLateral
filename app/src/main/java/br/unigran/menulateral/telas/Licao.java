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

public class Licao extends AppCompatActivity {

    private licao[] licoes;
    private int indiceAtual;
    private int pontuacao;

    private TextView titulo;
    private TextView conteudo;
    private Button salvar;


    private static class licao {
        private String titulo;
        private String conteudo;

        public licao(String titulo, String conteudo) {
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
        setContentView(R.layout.activity_licao);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        titulo = findViewById(R.id.idTitulo);
        conteudo = findViewById(R.id.idConteudo);
        salvar = findViewById(R.id.idSalvarRes);

        licoes = new licao[]{
                new licao("Saudações Básicas", "Hello, Bonjour, Hola"),
                new licao("Números de 1 a 10 ", "1-One    2-Two    3-three    4-four    5-five    6-six    7-seven    8-eight    9-nine    10-ten")
        };

        pontuacao = 0;
        indiceAtual = 0;

        exibirLicaoAtual();

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (indiceAtual < licoes.length - 1) {
                    // Avançar para a próxima lição
                    indiceAtual++;
                    exibirLicaoAtual();


                } else {
                }
            }
        });
    }

    private void exibirLicaoAtual() {
        licao licaoAtual = licoes[indiceAtual];
        titulo.setText(licaoAtual.getTitulo());
        conteudo.setText(licaoAtual.getConteudo());
    }


}
