package br.unigran.menulateral.model;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import br.unigran.menulateral.R;

public class TextoCH extends Fragment {

    public static TextoCH newInstance() {
        return new TextoCH();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exibirtexto, container, false);

        Texto texto = (Texto) getArguments().getSerializable("detail");





        TextView descriptionTextView = view.findViewById(R.id.descricao);
        descriptionTextView.setText(texto.getDescricao());

        return view;
    }
}
