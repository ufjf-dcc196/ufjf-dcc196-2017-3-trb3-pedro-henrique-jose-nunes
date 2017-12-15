package com.example.phlo.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteCursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class ListarLivros extends AppCompatActivity {
    private ListView lista;
    private Button botaoVoltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_livros);

        lista=(ListView)findViewById(R.id.lista);
        botaoVoltar=(Button)findViewById(R.id.botaoVoltar);
        LivroAdapter adapter=new LivroAdapter(getApplicationContext(),null);
        lista.setAdapter(adapter);
        adapter.atualiza();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int i =(int )id;

                Intent TarefaEditar = new Intent(ListarLivros.this,ActivityListaReserva.class);
                TarefaEditar.putExtra("idlivro",Integer.toString(i));
                startActivity(TarefaEditar);
            }
        });
        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
