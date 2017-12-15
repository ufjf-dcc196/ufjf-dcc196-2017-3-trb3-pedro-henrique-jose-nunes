package com.example.phlo.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.phlo.myapplication.Banco.BibliotecaContract;
import com.example.phlo.myapplication.Banco.BibliotecaDbHelper;
import com.example.phlo.myapplication.Model.ParticipanteModel;

import java.util.Date;

public class ActivityPessoaDados extends AppCompatActivity {
    private TextView nome;
    private TextView email;
    private TextView dataentrada;
    private TextView datasaida;
    private Button btnVoltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa_dados);

        Intent i = getIntent();
        String id=i.getStringExtra("id");
        int j=Integer.parseInt(id);
        nome=(TextView)findViewById(R.id.Nome);
        email=(TextView)findViewById(R.id.email);
        dataentrada=(TextView)findViewById(R.id.entrada);
        datasaida=(TextView)findViewById(R.id.saida);
        btnVoltar=(Button)findViewById(R.id.btnVolta);
        ParticipanteAdapter parti = new ParticipanteAdapter(getApplicationContext(),null);
        ParticipanteModel participante =  parti.getParticipante(j);

        nome.setText(participante.getNome());
        email.setText(participante.getEmail());
        if(participante.getHoraEntrada()!=null) {
            dataentrada.setText(participante.getHoraEntrada());
        }
        if(participante.getHoraSaida()!=null) {
            datasaida.setText(participante.getHoraSaida());
        }
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
