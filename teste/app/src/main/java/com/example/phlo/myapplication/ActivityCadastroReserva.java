package com.example.phlo.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.phlo.myapplication.Banco.BibliotecaDbHelper;
import com.example.phlo.myapplication.Model.LivroModel;
import com.example.phlo.myapplication.Model.ParticipanteModel;
import com.example.phlo.myapplication.Model.ReservaModel;

import java.util.ArrayList;

public class ActivityCadastroReserva extends AppCompatActivity {

    private ArrayList<LivroModel> livros=new ArrayList<>();
    private Button btnSalvar;
    private Button btnVoltar;
    private ArrayList<ParticipanteModel> participa= new ArrayList<>();
    private LivroModel reservar;
    private ReservaModel reservarLivro;
    private ArrayList<ReservaModel> reservardelivros = new ArrayList<>();
    private BibliotecaDbHelper dbhelper;
    private Spinner lista_participante;
    private Spinner llistaLivro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_reserva);
        //alimenta spinner
        ArrayList<String> list_partic = new ArrayList<String>();
        ArrayList<String> list_livro = new ArrayList<String>();

        lista_participante=(Spinner)findViewById(R.id.lista_participante);
        dbhelper= new BibliotecaDbHelper(getApplicationContext());
        list_partic= dbhelper.getTodosParticipanteNome();
        ArrayAdapter adapter =  new ArrayAdapter(this,android.R.layout.simple_spinner_item,list_partic);
        lista_participante.setAdapter(adapter);
        llistaLivro=(Spinner)findViewById(R.id.llistaLivro);
        list_livro=dbhelper.getLivroTitulo();
        ArrayAdapter adapter2 =  new ArrayAdapter(this,android.R.layout.simple_spinner_item,list_livro);
        llistaLivro.setAdapter(adapter2);






        btnSalvar=(Button)findViewById(R.id.btnSalvar);
        btnVoltar=(Button)findViewById(R.id.btnVoltar);


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReservaModel reserva = new ReservaModel(llistaLivro.getSelectedItem().toString(),
                        lista_participante.getSelectedItem().toString());
                dbhelper=new BibliotecaDbHelper(getApplicationContext());
                if(dbhelper.salvar(reserva)) {
                    Toast.makeText(getApplicationContext(), "Reservado  com sucesso", Toast.LENGTH_LONG).show();
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Livro nao foi reservado", Toast.LENGTH_LONG).show();

                }
            }
        });
    }


}
