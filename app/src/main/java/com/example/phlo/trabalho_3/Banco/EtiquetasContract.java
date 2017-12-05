package com.example.phlo.trabalho_3.Banco;

import android.provider.BaseColumns;

/**
 * Created by phlo on 03/12/17.
 */

public class EtiquetasContract {

    public static final String TEXT_TYPE = " VARCHAR(45)";
    public static final String INT_TYPE = " INTEGER";
    public static final String SEP = ",";
    public static final String SQL_CREATE_ETIQUETAS= "CREATE TABLE " + EtiquetasContract.Etiquetas.TABLE_NAME + " (" +
            EtiquetasContract.Etiquetas._ID + INT_TYPE +" PRIMARY KEY AUTOINCREMENT" + SEP +
            Etiquetas.COLUMN_NAME_NOME + TEXT_TYPE +")";
    public static final String SQL_DROP_ETIQUETAS = "DROP TABLE IF EXISTS " + EtiquetasContract.Etiquetas.TABLE_NAME;
    public EtiquetasContract() {
    }

    public static final class  Etiquetas implements BaseColumns {
        public static final String TABLE_NAME = "etiqueta";
        public static final String COLUMN_NAME_NOME = "nome";

    }
}
