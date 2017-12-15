package com.example.phlo.myapplication.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by phlo on 22/10/17.
 */

public class LivroModel {
    private String titulo;
    private String editora;
    private int ano;



public LivroModel(String titulo, String editora, int ano) {
        this.titulo = titulo;
        this.editora = editora;
        this.ano = ano;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
