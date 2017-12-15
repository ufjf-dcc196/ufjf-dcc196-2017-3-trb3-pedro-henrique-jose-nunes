package com.example.phlo.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.phlo.myapplication.Banco.BibliotecaContract;
import com.example.phlo.myapplication.Banco.BibliotecaDbHelper;
import com.example.phlo.myapplication.Model.LivroModel;
import com.example.phlo.myapplication.Model.ParticipanteModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private Button btnCadastroParticipante;
    private Button btnReservar;
    private Button btnCadastroLivro;
    private Button btnListaReserva;
    private ArrayList<ParticipanteModel> participa= new ArrayList<>();
    private ArrayList<LivroModel> livro= new ArrayList<>();
    private ListView participantes;
    public static   ParticipanteAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastroLivro=(Button)findViewById(R.id.btnCadastroLivro);
        btnCadastroParticipante=(Button)findViewById(R.id.btnCadastroParti);
        btnReservar=(Button)findViewById(R.id.btnReserva);
        btnListaReserva=(Button)findViewById(R.id.btnLIstaReserva);
        participantes=(ListView)findViewById(R.id.participantes);

        //alimenta o listview participantes

        adapter=new ParticipanteAdapter(getBaseContext(),null);
        adapter.atualiza();


        participantes.setAdapter(adapter);

        participantes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Date data = new Date();
                Calendar cal = Calendar.getInstance();
                data = cal.getTime();
                SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");
                int i=(int)id;
                if(adapter.Entrada(i).equals("")) {
                    adapter.SetEntrada(dateFormat.format(data),i);
                    Toast.makeText(MainActivity.this,"hora entrada atualizada",Toast.LENGTH_SHORT).show();
                    return true;
                }
                else if(adapter.saida(i).equals("")) {
                    adapter.SetSaida(dateFormat.format(data),i);
                    Toast.makeText(MainActivity.this,"hora saida atualizada",Toast.LENGTH_SHORT).show();

                    return true;
                }
                else  {
                    adapter.SetSaida("",i);

                    adapter.SetEntrada("",i);
                    Toast.makeText(MainActivity.this,"hora apagada",Toast.LENGTH_SHORT).show();

                    return true;

                }

            }
        });
        participantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent dadosSobre= new Intent(MainActivity.this,ActivityPessoaDados.class);
                int i =(int )id;
                dadosSobre.putExtra("id" , Integer.toString(i));

                startActivity(dadosSobre);
            }
        });
        btnCadastroParticipante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent cadastrarParti = new Intent(MainActivity.this,ActivityCadastroParticipante.class);
                startActivity(cadastrarParti);

            }
        });
        btnCadastroLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cadastrarLivro = new Intent(MainActivity.this,ActivityCadastroLivros.class);

                startActivity(cadastrarLivro);
            }
        });
        btnReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Reservar = new Intent(MainActivity.this,ActivityCadastroReserva.class);


                startActivity(Reservar);
            }
        });
        btnListaReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Reservar = new Intent(MainActivity.this,ListarLivros.class);
                startActivity(Reservar);
            }
        });
    }



}
