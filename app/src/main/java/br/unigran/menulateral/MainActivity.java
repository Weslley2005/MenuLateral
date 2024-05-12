package br.unigran.menulateral;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import br.unigran.menulateral.model.Texto;
import br.unigran.menulateral.model.TextoCH;
import br.unigran.menulateral.model.TextoListar;
import br.unigran.menulateral.telas.Licao;
import br.unigran.menulateral.telas.LicaoFrances;
import br.unigran.menulateral.telas.Quiz;
import br.unigran.menulateral.telas.QuizFrances;
import br.unigran.menulateral.telas.TarefaDiaria;

public class MainActivity extends AppCompatActivity implements TextoListar.OnListSelected {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView= findViewById(R.id.navView);
        toggle= new ActionBarDrawerToggle(this,drawerLayout,R.string.fechar,R.string.abrir);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_root, new TextoListar(), "textoListar")
                    .commit();
        }

    }
    @Override
    public void onSelected(Texto texto) {
        Bundle args = new Bundle();
        args.putSerializable("detail", texto);

        TextoCH fragment = new TextoCH();
        fragment.setArguments(args);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_root, fragment, "fragmentDetail")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }



    public void telaLicao(MenuItem item) {
        Intent intent = new Intent(this, Licao.class);
        startActivity(intent);
    }

    public void telaQuiz(MenuItem item) {
        Intent intent = new Intent(this, Quiz.class);
        startActivity(intent);
    }

    public void telaLicaoFrances(MenuItem item) {
        Intent intent = new Intent(this, LicaoFrances.class);
        startActivity(intent);
    }

    public void telaQuizFrances(MenuItem item) {
        Intent intent = new Intent(this, QuizFrances.class);
        startActivity(intent);
    }

    public void telaTarefa(MenuItem item) {
        Intent intent = new Intent(this, TarefaDiaria.class);
        startActivity(intent);
    }




}