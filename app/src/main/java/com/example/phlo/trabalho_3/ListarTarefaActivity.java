package com.example.phlo.trabalho_3;

import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.phlo.trabalho_3.Adapter.TarefaAdapter;
import com.example.phlo.trabalho_3.Banco.dbHelper;

public class ListarTarefaActivity extends AppCompatActivity {

    private ListView listarTarefa;
    private dbHelper dbhelper;
    private TarefaAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_tarefa);


        listarTarefa=(ListView)findViewById(R.id.listarTarefa);
        adapter=new TarefaAdapter(getBaseContext(),null);
        listarTarefa.setAdapter(adapter);
        adapter.atualizar();

        listarTarefa.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SQLiteCursor sqlCursor= (SQLiteCursor) adapter.getItem(i);
                String id =sqlCursor.getString(sqlCursor.getColumnIndex("_id"));

                Intent TarefaEditar = new Intent(ListarTarefaActivity.this,TarefaEditarActivity.class);
                TarefaEditar.putExtra("id",id);
                startActivity(TarefaEditar);
            }
        });
    }
}
