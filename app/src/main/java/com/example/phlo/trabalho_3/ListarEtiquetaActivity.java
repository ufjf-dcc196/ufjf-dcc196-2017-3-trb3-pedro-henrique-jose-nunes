package com.example.phlo.trabalho_3;

import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.phlo.trabalho_3.Adapter.EtiquetaAdapter;
import com.example.phlo.trabalho_3.Adapter.TarefaAdapter;
import com.example.phlo.trabalho_3.Banco.EtiquetaTarefaContract;

public class ListarEtiquetaActivity extends AppCompatActivity {
    private ListView listarEtiqueta;
    private Button CadastrarEtiquetaTarefa;
    private EtiquetaAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_etiqueta);

        listarEtiqueta=(ListView)findViewById(R.id.listarEtiqueta);
        CadastrarEtiquetaTarefa=(Button)findViewById(R.id.CadastrarEtiquetaTarefa);
        adapter=new EtiquetaAdapter(getBaseContext(),null);
        adapter.atualizar();

        listarEtiqueta.setAdapter(adapter);
        CadastrarEtiquetaTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadastroTag= new Intent(ListarEtiquetaActivity.this,CadastrarTag_TarefaActivity.class);
                startActivity(cadastroTag);
            }
        });

        listarEtiqueta.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SQLiteCursor sqlCursor= (SQLiteCursor) adapter.getItem(i);
                String id =sqlCursor.getString(sqlCursor.getColumnIndex(EtiquetaTarefaContract.EtiquetaTarefa.COUMN_NAME_ETIQUETAID));

                Intent EtiqueTarefa = new Intent(ListarEtiquetaActivity.this,MostrarEtiquetaeTarefaActivity.class);
                EtiqueTarefa.putExtra("id",id);
                startActivity(EtiqueTarefa);
            }
        });
    }
    }

