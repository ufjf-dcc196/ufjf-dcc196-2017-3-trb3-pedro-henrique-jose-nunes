package com.example.phlo.myapplication.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by phlo on 22/10/17.
 */

public class ParticipanteModel  {
    private String nome;
    private String email;
    private String horaEntrada;
    private String horaSaida;



    public String getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public String getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(String horaSaida) {
        this.horaSaida = horaSaida;
    }

    public ParticipanteModel(String nome, String email, String entrada, String horaSaida) {
        this.nome = nome;
        this.email = email;
        this.horaEntrada=entrada;
        this.horaSaida=horaSaida;
    }

    public ParticipanteModel() {
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
