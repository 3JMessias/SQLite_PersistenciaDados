package com.unipac.sqlite_persistenciadados;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.unipac.sqlite_persistenciadados.Adapter.ContatoAdapter;
import com.unipac.sqlite_persistenciadados.DAO.ContatoDAO;
import com.unipac.sqlite_persistenciadados.Models.Contato;

import java.util.ArrayList;
import java.util.Collection;

public class ListaContatosActivity extends AppCompatActivity {

    TextView txtNome;
    TextView txtTelefone;
    Button btnVoltar;

    AlertDialog alertDialog;
    private ArrayList<Contato> listadeContatos = new ArrayList<>();
    private ContatoDAO contatoDAO = null;
    private ArrayAdapter adapter;
    private ListView contatoList;
    private long idContato;
    long idContatoS;
    Contato contato = new Contato();
    Contato contatoSelecionado;
    private String telefone;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_contatos);

        contatoDAO = new ContatoDAO(getBaseContext());
        contatoList = findViewById(R.id.LV_ListarContatos);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idContatoS = (long) extras.get("idContatoSelec");
        }

        btnVoltar = findViewById(R.id.lc_btnVoltar);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaContatosActivity.this, MainActivity.class);
                intent.putExtra("idContato", idContato);
                startActivity(intent);
            }
        });
    }
        @Override
        protected void onResume() {
            super.onResume();
            ArrayList<Contato> listContato = contatoDAO.buscarTodos();
//            Collection.sort(listContato);

            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                idContato = extras.getLong("idContatoSelec");
//                contatoSelecionado = (Contato) extras.get("contatoSelec");
        }

        if(!listContato.isEmpty()){
        ArrayAdapter adapter = new ContatoAdapter(ListaContatosActivity.this, listContato);
        contatoList.setAdapter(adapter);

            contatoList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Contato contatoSelecionado = (Contato) contatoList.getItemAtPosition(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(ListaContatosActivity.this);
                builder.setTitle("Deletar");
                builder.setMessage("Deseja apagar este contato?");
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        contatoDAO.deletarContato(contatoDAO.buscarPorId(contatoSelecionado.getId()));
                        Toast.makeText(getApplicationContext(), "Contato foi deletado!" + arg1, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(ListaContatosActivity.this, ListaContatosActivity.class);
                        intent.putExtra("idContatoSelec", idContato);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });
                alertDialog = builder.create();
                alertDialog.show();
                return true;
            }
        });
    } else {
        contatoList.setAdapter(null);
        Toast.makeText(getApplicationContext(), "Não há contatos cadastrados!", Toast.LENGTH_LONG).show();

    }
    }

}

