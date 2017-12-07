package com.example.phlo.trabalho_3.Banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.phlo.trabalho_3.Banco.TarefasContract;

/**
 * Created by phlo on 01/12/17.
 */

public class dbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 6;
    public static final String DATABASE_NAME = "Tabelas.db";

    public dbHelper(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TarefasContract.SQL_CREATE_TAREFAS);
        db.execSQL((EtiquetasContract.SQL_CREATE_ETIQUETAS));
        db.execSQL(EtiquetaTarefaContract.SQL_CREATE_EtiquetaTarefa);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TarefasContract.SQL_DROP_Tarefas);
        db.execSQL((EtiquetasContract.SQL_DROP_ETIQUETAS));
        db.execSQL(EtiquetaTarefaContract.SQL_DROP_EtiquetaTarefa);
        onCreate(db);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion,newVersion);
    }

}
