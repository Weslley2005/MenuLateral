package br.unigran.menulateral.model;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.unigran.menulateral.R;

public class TextoListar extends Fragment {
    private OnListSelected listener;
    private String[] nomes;
    private String[] descricao;


    public static TextoListar newInstance() {
        return new TextoListar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listartexto, container, false);

        Context activity = getActivity();
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(new CharacterListAdapter());

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Resources resources = context.getResources();
        nomes = resources.getStringArray(R.array.nomes);
        descricao = resources.getStringArray(R.array.descricao);





        if (context instanceof OnListSelected) {
            listener = (OnListSelected) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement OnListSelected");
        }
    }

    class CharacterListAdapter extends RecyclerView.Adapter<CharacterListAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            return new ViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.itemlista, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            final Texto texto = new Texto(nomes[position], descricao[position]);
            viewHolder.bind(texto);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSelected(texto);
                }
            });
        }

        @Override
        public int getItemCount() {
            return nomes.length;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ViewHolder(View itemView) {
                super(itemView);
            }

            void bind(Texto texto) {

                TextView textView = itemView.findViewById(R.id.list_name);
                textView.setText(texto.getNome());
            }
        }
    }

    public interface OnListSelected {
        void onSelected(Texto texto);
    }
}
