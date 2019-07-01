package com.unipac.sqlite_persistenciadados.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.unipac.sqlite_persistenciadados.DatabaseHandler;
import com.unipac.sqlite_persistenciadados.Models.Contato;


import java.util.ArrayList;
import java.util.List;

import static com.unipac.sqlite_persistenciadados.Models.Contato.*;

public class ContatoDAO {
    private DatabaseHandler dbHandler = null;

    private String[] allCollumns = {Contato.ID, Contato.NOME, Contato.TELEFONE};

    public ContatoDAO(Context context){
        dbHandler = new DatabaseHandler(context);
    }

    //Adicionar novo Contato
    public boolean addContato(Contato contato){
        SQLiteDatabase db = dbHandler.getWritableDatabase(); //abre o banco para leitura e gravação

        ContentValues values = new ContentValues(); //cria o objeto para preencher valores nas tabelas

        values.put(contato.NOME, contato.getNome()); //Contato Nome
        values.put(contato.TELEFONE, contato.getTelefone()); //Contato Telefone

        //Inserindo a linha
        long insertId = db.insert(contato.TABLE_CONTATO, null, values);
        return insertId > 0 ? Boolean.TRUE : Boolean.FALSE;

//        db.close();

    }

    //Pega um contato pelo seu telefone
    public Contato getContato(String telefone){
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        Cursor cursor = db.query(Contato.TABLE_CONTATO, allCollumns, TELEFONE + " = " +
                telefone, null,null,null,null);
        cursor.moveToFirst();

        Contato contato = new Contato();

        contato.setId(cursor.getInt(0));
        contato.setNome(cursor.getString(1));
        contato.setTelefone(cursor.getString(2));

        return contato;

    }

    //Pega todos os contatos
    public List<Contato> getAllContatos(){
        //Abrir o banco para leitura
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTATO, allCollumns, null, null, null,null, null,null);

        List<Contato>contatoList = new ArrayList<>();

//        String selectQuery = "SELECT * FROM" + contato.TABLE_NAME;
//        Cursor cursor = db.rawQuery(selectQuery, null);
        //Pega os valores resultantes e seta no objeto, movendo sempre para o próximo cursor.
        if(cursor.moveToFirst()){
            int idxId = cursor.getColumnIndex(Contato.ID);
            int idxNome = cursor.getColumnIndex(Contato.NOME);
            int idxTelefone = cursor.getColumnIndex(TELEFONE);

            do{
                Contato contato = new Contato();
                contatoList.add(contato);

                contato.setId(Integer.parseInt(cursor.getString(idxId)));
                contato.setNome(cursor.getString(idxNome));
                contato.setTelefone(cursor.getString(idxTelefone));

                //Adiciona o contato na lista de contatos.
                contatoList.add(contato);
            }while (cursor.moveToNext());
        }
        cursor.close();
        //Retorna a lista de contatos
        return contatoList;

    }

    //Pega o numero de contatos (Quantidade de contatos)
    public int getContatosCount(){
        String countQuery = "SELECT * FROM " + Contato.TABLE_CONTATO;

        //Abrir o banco para leitura
        SQLiteDatabase db = dbHandler.getReadableDatabase();

        //Passa a query para ser executada
        Cursor cursor = db.rawQuery(countQuery, null);

        //Retorna o total de contatos.
        return cursor.getCount();
    }



    //Atualizar o contato
    public int updateContato(Contato contato) {
        SQLiteDatabase db = dbHandler.getWritableDatabase(); //Abre o banco para leitura e gravação

        ContentValues values = new ContentValues();
        values.put(contato.NOME, contato.getNome());
        values.put(contato.TELEFONE, contato.getTelefone());

        //Faz update no registro
        return db.update(TABLE_CONTATO, values, contato.ID + " =? ",
                new String[] {String.valueOf(contato.getId())});
    }


//    Deletando o contato por ID
    public void deleteContato(Contato contato){
        SQLiteDatabase db = dbHandler.getWritableDatabase(); //Abre o banco para leitura e gravação
// **Erro
        db.delete(TABLE_CONTATO, contato.ID + "=?",
                        new String[]{String.valueOf(contato.getId())});

        db.close(); // fecha o banco
//
//
//    }


}}