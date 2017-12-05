package com.example.phlo.trabalho_3.Banco;

import android.provider.BaseColumns;

/**
 * Created by phlo on 05/12/17.
 */

public class EtiquetaTarefaContract {
    public static final String INT_TYPE = " INTEGER";
    public static final String SEP = ",";
    public static final String SQL_CREATE_EtiquetaTarefa= "CREATE TABLE " + EtiquetaTarefaContract.EtiquetaTarefa.TABLE_NAME + " (" +
            EtiquetaTarefaContract.EtiquetaTarefa.COUMN_NAME_ETIQUETAID + INT_TYPE  + SEP +
            EtiquetaTarefaContract.EtiquetaTarefa.COLUMN_NAME_TAREFAID  + INT_TYPE  +SEP +
            "PRIMARY KEY ("+EtiquetaTarefaContract.EtiquetaTarefa.COUMN_NAME_ETIQUETAID +SEP+
            EtiquetaTarefaContract.EtiquetaTarefa.COLUMN_NAME_TAREFAID+")"+SEP+
            "FOREIGN KEY (EtiquetaID)REFERENCES "+ EtiquetasContract.Etiquetas.TABLE_NAME+"("+ EtiquetasContract.Etiquetas._ID+")"
            +SEP+"FOREIGN KEY (TarefaID)REFERENCES "+ TarefasContract.Tarefas.TABLE_NAME+"("+ TarefasContract.Tarefas._ID+")"
            +")";
    public static final String SQL_DROP_EtiquetaTarefa = "DROP TABLE IF EXISTS " + EtiquetaTarefaContract.EtiquetaTarefa.TABLE_NAME;
    public static final class EtiquetaTarefa implements BaseColumns{
        public static final String TABLE_NAME = "EtiquetaTarefa";
        public static final String COUMN_NAME_ETIQUETAID = "EtiquetaID";
        public static final String COLUMN_NAME_TAREFAID = "TarefaID";

    }
}
