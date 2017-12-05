package com.example.phlo.trabalho_3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {
    private Button cadastrarTarefa;
    private Button listarTarefa;
    private Button cadastrarEtiqueta;
    private Button listarEtiqueta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        cadastrarTarefa=(Button)findViewById(R.id.CadastrarTarefa);
        listarTarefa=(Button)findViewById(R.id.listarTarefa);
        listarEtiqueta=(Button)findViewById(R.id.listarEtiqueta);
        cadastrarEtiqueta=(Button)findViewById(R.id.CadastrarEtiqueta);
        listarEtiqueta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listarEtiqueta = new Intent(Menu.this,ListarEtiquetaActivity.class);
                startActivity(listarEtiqueta);
            }
        });
        cadastrarEtiqueta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadastrarEtiqueta = new Intent(Menu.this,cadastroEtiquetaActivity.class);
                startActivity(cadastrarEtiqueta);
            }
        });
        cadastrarTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadastrarTarefa = new Intent(Menu.this,cadastroTarefaActivity.class);
                startActivity(cadastrarTarefa);
            }
        });


        listarTarefa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listarTarefa = new Intent(Menu.this,ListarTarefaActivity.class);
                startActivity(listarTarefa);
            }
        });
    }
}
