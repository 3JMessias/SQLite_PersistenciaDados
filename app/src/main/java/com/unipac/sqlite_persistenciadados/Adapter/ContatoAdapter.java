package com.unipac.sqlite_persistenciadados.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.unipac.sqlite_persistenciadados.Models.Contato;
import com.unipac.sqlite_persistenciadados.R;

import java.util.ArrayList;
import java.util.List;

public class ContatoAdapter extends ArrayAdapter<Contato> {
    private final Context context;
    private final ArrayList<Contato> elementos;

    public ContatoAdapter(Context context, ArrayList<Contato> elementos){
        super(context, R.layout.linha_contato, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contato contato = elementos.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha_contato, null);

        TextView tNome = (TextView) rowView.findViewById(R.id.main_edtTelefone);
        TextView tTelefone = (TextView) rowView.findViewById(R.id.main_edtTelefone);


        tNome.setText(elementos.get(position).getNome());
        tTelefone.setText(elementos.get(position).getTelefone());

        return rowView;
    }
}