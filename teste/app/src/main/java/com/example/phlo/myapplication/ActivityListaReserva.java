package com.example.phlo.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.phlo.myapplication.Banco.BibliotecaContract;
import com.example.phlo.myapplication.Banco.BibliotecaDbHelper;
import com.example.phlo.myapplication.Model.LivroModel;
import com.example.phlo.myapplication.Model.ParticipanteModel;
import com.example.phlo.myapplication.Model.ReservaModel;

import java.util.ArrayList;

public class ActivityListaReserva extends AppCompatActivity {
    private ListView listagem;
    private TextView txttitulo;
    private BibliotecaDbHelper dbhelper;
    private TextView txtEditora;
    private TextView txtAno;
    private Button bVoltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_reserva);


        Intent i = getIntent();
        String id = i.getStringExtra("idlivro");
        int j=Integer.parseInt(id);

        listagem= (ListView)findViewById(R.id.listagem);
        bVoltar=(Button)findViewById(R.id.bVoltar);
        txttitulo=(TextView)findViewById(R.id.txttitulo);
        txtEditora=(TextView)findViewById(R.id.txtEditora);
        txtAno=(TextView)findViewById(R.id.txtAno);

        ArrayList<String>NomeParticipante = new ArrayList<String>();
        dbhelper= new BibliotecaDbHelper(getApplicationContext());

        LivroModel l =dbhelper.livros(j);
        txttitulo.setText(l.getTitulo());
        txtEditora.setText(l.getEditora());
        txtAno.setText(String.valueOf(l.getAno()));
        NomeParticipante=dbhelper.getParticipantesReserva(l.getTitulo());
        if(NomeParticipante!=null) {
            ArrayAdapter adapter = new ArrayAdapter(this, R.layout.lista_reserva, R.id.lista_parti, NomeParticipante);
            listagem.setAdapter(adapter);
        }
        else
        {

        }
        bVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
