package com.unipac.sqlite_persistenciadados.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.unipac.sqlite_persistenciadados.Infra.HelperDB;
import com.unipac.sqlite_persistenciadados.Models.Contato;


import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    public static final String TABLE_CONTATO = "contato";

    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String TELEFONE = "telefone";

    public static final String DATABASE_CREATE = "create table "
            + TABLE_CONTATO + "( "
            + ID + " integer primary key autoincrement, "
            + NOME + " text not null, "
            + TELEFONE + " text not null);";

    private HelperDB helperDB;

    private String[] allColumns = {
            ContatoDAO.ID,
            ContatoDAO.NOME,
            ContatoDAO.TELEFONE
    };

    public ContatoDAO(Context context){
        helperDB = new HelperDB(context);
    }

    public boolean salvar(Contato contato){
        SQLiteDatabase database = helperDB.getWritableDatabase();

        ContentValues values = new ContentValues();
        if(contato.getId() != 0){
            values.put(ContatoDAO.ID, contato.getId());
        }
        values.put(ContatoDAO.NOME, contato.getNome());
        values.put(ContatoDAO.TELEFONE, contato.getTelefone());

        long insertId = database.insert(ContatoDAO.TABLE_CONTATO, null, values);

        return insertId > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    public Contato buscarPorId(long id){
        SQLiteDatabase database = helperDB.getReadableDatabase();

        Cursor cursor = database.query(ContatoDAO.TABLE_CONTATO, allColumns, ContatoDAO.ID + "='"
                + id + "'", null, null,null,null);
        cursor.moveToFirst();

        return cursorToObject(cursor);
    }


    public boolean deletarContato(Contato contato){
        SQLiteDatabase database = helperDB.getWritableDatabase();

        ContentValues values = new ContentValues();
        if(contato.getId() != 0){
            values.put(ContatoDAO.ID, contato.getId());
        }
        values.put(ContatoDAO.NOME, contato.getNome());
        values.put(ContatoDAO.TELEFONE, contato.getTelefone());

        long insertId = database.insert(ContatoDAO.TABLE_CONTATO, null, values);

        return insertId > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    public ArrayList<Contato> buscarTodos(){
        SQLiteDatabase database = helperDB.getReadableDatabase();

        Cursor cursor = database.query(ContatoDAO.TABLE_CONTATO, allColumns, null, null,null,null,null);
        ArrayList<Contato> contatos = new ArrayList<>();

        if (cursor.moveToFirst()) {
            int indId = cursor.getColumnIndex(ContatoDAO.ID);
            int indNome = cursor.getColumnIndex(ContatoDAO.NOME);
            int indTelefone = cursor.getColumnIndex(ContatoDAO.TELEFONE);

            do {
                Contato contato = new Contato();

                contato.setId(cursor.getLong(indId));
                contato.setNome(cursor.getString(indNome));
                contato.setTelefone(cursor.getString(indTelefone));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contatos;
    }

    public boolean verificarSeContatoExiste(String telefone){
        SQLiteDatabase database = helperDB.getReadableDatabase();

        Cursor cursor = database.query(ContatoDAO.TABLE_CONTATO, allColumns, ContatoDAO.TELEFONE + "='"
                + telefone + "'", null, null,null,null);

        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    private Contato cursorToObject(Cursor cursor){
        Contato contato = new Contato();
        contato.setId(cursor.getLong(0));
        contato.setNome(cursor.getString(1));
        contato.setTelefone(cursor.getString(2));
        return contato;
    }
}