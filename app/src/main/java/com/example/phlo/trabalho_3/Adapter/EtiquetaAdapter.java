package com.example.phlo.trabalho_3.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.phlo.trabalho_3.Banco.EtiquetasContract;
import com.example.phlo.trabalho_3.Banco.TarefasContract;
import com.example.phlo.trabalho_3.Banco.dbHelper;
import com.example.phlo.trabalho_3.R;

/**
 * Created by phlo on 05/12/17.
 */

public class EtiquetaAdapter extends CursorAdapter {
    private dbHelper etiquetaHelper;
    public EtiquetaAdapter(Context context, Cursor c) {
        super(context, c, 0);
        etiquetaHelper= new dbHelper(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.listar_etiqueta,viewGroup,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtNome=(TextView)view.findViewById(R.id.txtNome);
        String nome =cursor.getString(cursor.getColumnIndexOrThrow(EtiquetasContract.Etiquetas.COLUMN_NAME_NOME));
        txtNome.setText(nome);
    }



    public void atualizar(){
        try {
            SQLiteDatabase db = etiquetaHelper.getReadableDatabase();
            String[] visao = {
                    EtiquetasContract.Etiquetas._ID,
                    EtiquetasContract.Etiquetas.COLUMN_NAME_NOME,

            };

            Cursor c = db.query(EtiquetasContract.Etiquetas.TABLE_NAME, visao, null, null, null, null, null);
            this.changeCursor(c);

        } catch (Exception e) {
            Log.e("BIBLIO", e.getLocalizedMessage());
            Log.e("BIBLIO", e.getStackTrace().toString());
        }
    }
}
