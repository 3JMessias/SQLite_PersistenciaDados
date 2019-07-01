package com.unipac.sqlite_persistenciadados;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.unipac.sqlite_persistenciadados.DAO.ContatoDAO;
import com.unipac.sqlite_persistenciadados.Models.Contato;

public class MainActivity extends AppCompatActivity {
    private EditText varNome;
    private EditText varTelefone;
    private Button bSalvar;
    private Button bCancelar;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        varNome = findViewById(R.id.main_edtNome);
        varTelefone = findViewById(R.id.main_edtTelefone);
        bSalvar = findViewById(R.id.main_btnSalvar);
        bCancelar = findViewById(R.id.main_btnCancelar);



        bSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    ContatoDAO contatoDAO = new ContatoDAO(getBaseContext());
                    String nome = varNome.getText().toString();
                    String telefone = varTelefone.getText().toString();
                    Contato contato = new Contato(nome, telefone);
                    if(!nome.isEmpty() && !telefone.isEmpty()){
                        contatoDAO.addContato(contato);
                        Toast.makeText(getApplicationContext(), "Salvando novo Contato", Toast.LENGTH_SHORT).show();
                    }

            }

        });

        bCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                varNome.setText("");
                varTelefone.setText("");
                varNome.requestFocus();

            }
        });

    }}
