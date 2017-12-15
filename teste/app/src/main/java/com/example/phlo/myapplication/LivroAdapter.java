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

/**
 * Created by phlo on 08/12/17.
 */

public class LivroAdapter  extends CursorAdapter {
    private BibliotecaDbHelper bibliotecahelper;
    public LivroAdapter(Context context, Cursor c) {
        super(context, c, 0);
        bibliotecahelper= new BibliotecaDbHelper(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.listar_livro,parent,false);
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtTitulo=(TextView)view.findViewById(R.id.Livrotitulo);



        String titulo =cursor.getString(cursor.getColumnIndexOrThrow(BibliotecaContract.Livro.COLUMN_NAME_TITULO));

        txtTitulo.setText(titulo);

    }
    public void atualiza(){
        this.changeCursor(bibliotecahelper.atualizaLivro());
    }
}

