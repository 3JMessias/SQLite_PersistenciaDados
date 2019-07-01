package com.unipac.sqlite_persistenciadados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
    //Definindo a versão do Banco
    private static final int DATABASE_VERSION = 1;
    //Nome do banco
    private static final String DATABASE_NAME = "controlecontatos.db";
    //Definição do nome da tabela.
    private static final String TABLE_CONTATO = "contatos";

//    public static String getKeyId() {
//        return KEY_ID;
//    }
//
//    public static String getKeyNome() {
//        return KEY_NOME;
//    }
//
//    public static String getKeyTelefone() {
//        return KEY_TELEFONE;
//    }

    //Nome das colunas a serem criadas
    private static final String KEY_ID = "id";
    private static final String KEY_NOME = "nome";
    private static final String KEY_TELEFONE = "telefone";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Criação das tabelas
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE" + TABLE_CONTATO + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NOME + " TEXT,"
                + KEY_TELEFONE + "TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // update do banco
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // deleta a tabela se ela exitir.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTATO);
        // recria a tabela depois de deletar.
        onCreate(db);

    }
}
