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

import com.example.phlo.trabalho_3.Banco.TarefasContract;
import com.example.phlo.trabalho_3.Banco.dbHelper;
import com.example.phlo.trabalho_3.R;

import org.w3c.dom.Text;

public class TarefaAdapter extends CursorAdapter {
    private dbHelper tarefasHelper;
    private String estado;
    public TarefaAdapter(Context context, Cursor c) {
        super(context, c, 0);
        tarefasHelper= new dbHelper(context);
    }


    @Override
    public View newView(Context context, Cursor cursor,ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.listar_tarefa,viewGroup,false);
    }

    @Override
    public long getItemId(int position){
        return position;
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtTitulo=(TextView)view.findViewById(R.id.txtTitulo);
        TextView txtEstado=(TextView)view.findViewById(R.id.txtEstado);


        String titulo =cursor.getString(cursor.getColumnIndexOrThrow(TarefasContract.Tarefas.COLUMN_NAME_TITULO));
        String estado =cursor.getString(cursor.getColumnIndexOrThrow(TarefasContract.Tarefas.COLUMN_NAME_ESTADO));

        txtTitulo.setText(titulo);
        txtEstado.setText(estado);
    }
    public void atualizar(){
        try {
            SQLiteDatabase db = tarefasHelper.getReadableDatabase();
            String[] visao = {
                    TarefasContract.Tarefas._ID,
                    TarefasContract.Tarefas.COLUMN_NAME_TITULO,
                    TarefasContract.Tarefas.COLUMN_NAME_Descricao,
                    TarefasContract.Tarefas.COLUMN_NAME_Dificuldade,
                    TarefasContract.Tarefas.COLUMN_NAME_ESTADO,
            };

            Cursor c = db.query(TarefasContract.Tarefas.TABLE_NAME, visao, null, null, null, null, TarefasContract.Tarefas.COLUMN_NAME_ESTADO);
            this.changeCursor(c);

        } catch (Exception e) {
            Log.e("BIBLIO", e.getLocalizedMessage());
            Log.e("BIBLIO", e.getStackTrace().toString());
        }
    }

}
