package com.unipac.sqlite_persistenciadados.Models;

import java.io.Serializable;

public class Contato implements Serializable, Comparable<Contato> {

    private long id;
    private String nome;
    private String telefone;

    public Contato(long id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }

    public Contato() {
    }

    public Contato(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    @Override
    public int compareTo(Contato contato) {
        return getNome().compareTo(contato.getNome());
    }
}