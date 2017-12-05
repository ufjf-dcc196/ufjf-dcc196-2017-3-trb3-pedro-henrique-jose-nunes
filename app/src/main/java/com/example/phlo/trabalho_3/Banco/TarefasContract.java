package com.example.phlo.trabalho_3.Banco;

import android.provider.BaseColumns;

/**
 * Created by phlo on 01/12/17.
 */

public final class TarefasContract {
    public static final String TEXT_TYPE = " VARCHAR(45)";
    public static final String INT_TYPE = " INTEGER";
    public static final String SEP = ",";
    public static final String SQL_CREATE_TAREFAS= "CREATE TABLE " + Tarefas.TABLE_NAME + " (" +
            Tarefas._ID + INT_TYPE +" PRIMARY KEY AUTOINCREMENT" + SEP +
            Tarefas.COLUMN_NAME_TITULO + TEXT_TYPE + SEP +
            Tarefas.COLUMN_NAME_Descricao + TEXT_TYPE + SEP +
            Tarefas.COLUMN_NAME_Dificuldade + INT_TYPE   + SEP+
            Tarefas.COLUMN_NAME_ESTADO + TEXT_TYPE + ")";
    public static final String SQL_DROP_Tarefas = "DROP TABLE IF EXISTS " + Tarefas.TABLE_NAME;
    public TarefasContract() {
    }

    public static final class Tarefas implements BaseColumns {
        public static final String TABLE_NAME = "tarefas";
        public static final String COLUMN_NAME_TITULO = "titulo";
        public static final String COLUMN_NAME_Descricao = "descricao";
        public static final String COLUMN_NAME_Dificuldade = "dificuldade";
        public static final String COLUMN_NAME_ESTADO = "estado";
    }
}
