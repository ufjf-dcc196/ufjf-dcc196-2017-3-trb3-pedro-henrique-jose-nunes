package com.example.phlo.trabalho_3.Banco;

import android.provider.BaseColumns;

/**
 * Created by phlo on 05/12/17.
 */

public class EtiquetaTarefaContract {
    public static final String TEXT_TYPE = "  VARCHAR(45)";
    public static final String SEP = ",";
    public static final String SQL_CREATE_EtiquetaTarefa= "CREATE TABLE " + EtiquetaTarefa.TABLE_NAME + " (" +
            EtiquetaTarefa._ID+ "  INTEGER PRIMARY KEY AUTOINCREMENT"+SEP+
            EtiquetaTarefa.COUMN_NAME_ETIQUETAID + TEXT_TYPE  + SEP +
            EtiquetaTarefa.COLUMN_NAME_TAREFAID  + TEXT_TYPE +")";
    public static final String SQL_DROP_EtiquetaTarefa = "DROP TABLE IF EXISTS " +EtiquetaTarefa.TABLE_NAME;
    public static final class EtiquetaTarefa implements BaseColumns{
        public static final String TABLE_NAME = "EtiquetaTarefa";
        public static final String COUMN_NAME_ETIQUETAID = "EtiquetaID";
        public static final String COLUMN_NAME_TAREFAID = "TarefaID";

    }
}
