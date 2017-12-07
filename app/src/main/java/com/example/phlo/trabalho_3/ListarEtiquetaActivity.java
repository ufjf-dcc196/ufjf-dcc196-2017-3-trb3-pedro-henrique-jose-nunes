package com.example.phlo.trabalho_3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.phlo.trabalho_3.Adapter.EtiquetaAdapter;
import com.example.phlo.trabalho_3.Adapter.TarefaAdapter;
import com.example.phlo.trabalho_3.Banco.EtiquetaTarefaContract;
import com.example.phlo.trabalho_3.Banco.dbHelper;

public class ListarEtiquetaActivity extends AppCompatActivity {
    private ListView listarEtiqueta;
    private Button CadastrarEtiquetaTarefa;
    private EtiquetaAdapter adapter;
    private dbHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_etiqueta);

        listarEtiqueta=(ListView)findViewById(R.id.listarEtiqueta);
        CadastrarEtiquetaTarefa=(Button)findViewById(R.id.CadastrarEtiquetaTarefa);
        adapter=new EtiquetaAdapter(getBaseContext(),null);
        listarEtiqueta.setAdapter(adapter);
        adapter.atualizar();
        dbhelper = new dbHelper(getApplication());

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
                String id =sqlCursor.getString(sqlCursor.getColumnIndex("nome"));
                //String sql="SELECT * FROM "+
                //EtiquetaTarefaContract.EtiquetaTarefa.TABLE_NAME+" WHERE " +
               // EtiquetaTarefaContract.EtiquetaTarefa.COUMN_NAME_ETIQUETAID +" =?";
                //System.out.println("o que e isso "+adapter.getItem(i).toString());
               // Cursor c = db.rawQuery(sql,new String[]{});

                Intent EtiqueTarefa = new Intent(ListarEtiquetaActivity.this, MostrarEtiquetaeTarefaActivity.class);
                EtiqueTarefa.putExtra("id", id);
                startActivity(EtiqueTarefa);


            }
        });
    }
    }

