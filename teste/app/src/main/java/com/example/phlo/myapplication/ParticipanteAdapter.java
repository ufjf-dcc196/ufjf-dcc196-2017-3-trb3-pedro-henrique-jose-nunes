package com.example.phlo.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.phlo.myapplication.Banco.BibliotecaContract;
import com.example.phlo.myapplication.Banco.BibliotecaDbHelper;
import com.example.phlo.myapplication.Model.ParticipanteModel;

import java.util.ArrayList;

/**
 * Created by phlo on 08/12/17.
 */

public class ParticipanteAdapter  extends CursorAdapter {
    private BibliotecaDbHelper bibliohelper;
    public ParticipanteAdapter(Context context, Cursor c) {
        super(context, c,0);
        bibliohelper= new BibliotecaDbHelper(context);

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.listar_participante,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtparticipante=(TextView)view.findViewById(R.id.participante);



        String nome =cursor.getString(cursor.getColumnIndexOrThrow(BibliotecaContract.Participante.COLUMN_NAME_NOME));

        txtparticipante.setText(nome);
    }
    public void atualiza(){
        this.changeCursor(bibliohelper.atualizaParticipantes());
    }
    public String Entrada(int i){
        return bibliohelper.consultaEntrada(i);
    }
    public String saida(int i){
        return bibliohelper.consultaSaida(i);
    }
    public void SetEntrada(String hora,int id){
        bibliohelper.entrada(hora,id);
    }
    public void SetSaida(String hora,int id){
        bibliohelper.saida(hora,id);
    }

    public  ParticipanteModel getParticipante(int i) {
        return bibliohelper.Participantes(i);
    }

}

