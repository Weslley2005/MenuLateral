package br.unigran.menulateral.telas;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;
import java.util.List;
import br.unigran.menulateral.model.Tarefa;
import br.unigran.menulateral.R;

public class TarefaDiaria extends AppCompatActivity {

    ImageView img ;
    TextView resposta;
    Button salvar;
    FloatingActionButton fb;
    List dados;
    ListView listagem;
    ArrayAdapter listaAdapter;

    DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa_diaria);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        img = findViewById(R.id.imgId2);
        resposta = findViewById(R.id.idResposta);
        dados = new LinkedList();
        listagem = findViewById(R.id.idListagem);
        listaAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, dados);
        listagem.setAdapter(listaAdapter);
        ler();
    }

    public void acao(View v){
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        someActivityResultLauncher.launch(it);

    }
    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // Here, no request code
                        Intent data = result.getData();
                        Bundle extras = data.getExtras();
                        Bitmap imagem = (Bitmap) extras.get("data");

                        img.setImageBitmap(imagem);
                    }
                }
            });
    public void salvar(View view) {
        Tarefa t = new Tarefa();
        t.setResposta(resposta.getText().toString());
        DatabaseReference Tarefa1 = databaseReference.child("Tarefa1");
        Tarefa1.push().setValue(t);
        Toast.makeText(getApplicationContext(), "Salvo", Toast.LENGTH_SHORT).show();
        limparcampos();
    }

    public void ler() {
        DatabaseReference Tarefa1 = databaseReference.child("Tarefa1");
        Tarefa1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dados.clear();
                for (DataSnapshot item : snapshot.getChildren()) {
                    Tarefa tarefa = item.getValue(Tarefa.class);
                    dados.add(tarefa);
                    listaAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getApplicationContext(), "erro de conexão", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void limparcampos() {
        resposta.setText("");

    }

}