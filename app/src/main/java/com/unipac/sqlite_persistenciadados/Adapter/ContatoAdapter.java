//package com.unipac.sqlite_persistenciadados.Adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import com.unipac.sqlite_persistenciadados.Models.Contato;
//import com.unipac.sqlite_persistenciadados.R;
//
//import java.util.List;
//
//public class ContatoAdapter extends BaseAdapter {
//    private List<Contato> contatoList = null;
//    private Context context = null;
//
//    public ContatoAdapter(List<Contato> contatoList, Context context) {
//        this.contatoList = contatoList;
//        this.context = context;
//    }
//
//    @Override
//    public int getCount() {
//        if (!contatoList.isEmpty()) {
//            return contatoList.size();
//        }
//        return 0;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        if (!contatoList.isEmpty()) {
//            return contatoList.get(position);
//        }
//        return 0;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        if (!contatoList.isEmpty()) {
//            return contatoList.get(position).getId();
//        }
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        Contato contato = contatoList.get(position);
//
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.contato_item, null);
//
////        TextView alunoDesc = (TextView) view.findViewById(R.id.contato_desc);
//
//        StringBuffer sb = new StringBuffer(1200);
//        sb.append(contato.getNome());
//        sb.append(" - ");
//        sb.append(contato.getTelefone());
////        alunoDesc.setText(sb.toString());
//
//        return view;
//    }
//}