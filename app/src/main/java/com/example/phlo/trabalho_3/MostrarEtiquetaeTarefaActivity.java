package com.example.phlo.trabalho_3;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.phlo.trabalho_3.Adapter.TarefaAdapter;
import com.example.phlo.trabalho_3.Banco.EtiquetaTarefaContract;
import com.example.phlo.trabalho_3.Banco.TarefasContract;

import java.util.ArrayList;

public class MostrarEtiquetaeTarefaActivity extends AppCompatActivity {
    private ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_etiquetae_tarefa);
        lista=(ListView)findViewById(R.id.Lista);
        ArrayList<String>NomeTarefa = new ArrayList<String>();
        Intent i = getIntent();

        if(i!=null) {
            Bundle params=i.getExtras();
            if(params!= null){
                String id =params.getString("id");
                SQLiteDatabase db = openOrCreateDatabase("Tabelas.db", Context.MODE_PRIVATE, null);
                String sql = "SELECT * FROM tarefas INNER JOIN EtiquetaTarefa ON EtiquetaTarefa.TarefaID=tarefas.titulo WHERE " +
                        "EtiquetaTarefa.EtiquetaID=?";
                Cursor c = db.rawQuery(sql, new String[]{id});
                if (c!=null && c.moveToFirst()) {
                    do {
                      NomeTarefa.add(c.getString(c.getColumnIndex(TarefasContract.Tarefas.COLUMN_NAME_TITULO)));
                    System.out.print(c);
                    }while (c.moveToNext());


                    db.close();

            }

        }
            ArrayAdapter adapter =  new ArrayAdapter(this,android.R.layout.activity_list_item,NomeTarefa);
            lista.setAdapter(adapter);



        }

    }
}
