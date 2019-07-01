package com.unipac.sqlite_persistenciadados.Models;

import java.io.Serializable;

public class Contato implements Serializable {

public static final String TABLE_CONTATO = "contato";
    public static final String ID = "id";
    public static final String NOME = "nome";
    public static final String TELEFONE = "telefone";

    public static final String DATABASE_CREATE = "create table"
            + TABLE_CONTATO + "( "
            + ID + "integer primary key autoincrement, "
            + NOME + "text not null, "
            + TELEFONE + "text not null);";

    int id;
    String nome;
    String telefone;

    public Contato() {
    }

    public Contato(int id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }

    public Contato(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
