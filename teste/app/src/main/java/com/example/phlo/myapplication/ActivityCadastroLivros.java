package com.example.phlo.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.phlo.myapplication.Banco.BibliotecaContract;
import com.example.phlo.myapplication.Banco.BibliotecaDbHelper;
import com.example.phlo.myapplication.Model.LivroModel;

public class ActivityCadastroLivros extends AppCompatActivity {
    public static final int dado=2;
    private Button btnSalvar;
    private Button btnVoltar;
    private EditText titulo;
    private EditText editora;
    private EditText ano;
    private BibliotecaDbHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_livros);
        btnSalvar=(Button)findViewById(R.id.btnSalvar);
        btnVoltar=(Button)findViewById(R.id.btnVoltar);
        titulo=(EditText)findViewById(R.id.titulo);
        editora=(EditText)findViewById(R.id.editora);
        ano=(EditText)findViewById(R.id.ano);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Titulo=titulo.getText().toString();
                String Editora=editora.getText().toString();
                String Ano=ano.getText().toString();
                if(Titulo.isEmpty()){
                    titulo.requestFocus();
                }
                else if(Editora.isEmpty()){
                    editora.requestFocus();
                }
                else if(Ano.isEmpty()){
                    ano.requestFocus();
                }
                else{
                    LivroModel livro = new LivroModel(Titulo,Editora,Integer.parseInt(Ano));
                    dbhelper=new BibliotecaDbHelper(getApplicationContext());
                    if( dbhelper.salvar(livro)) {
                        LivroAdapter adapter = new LivroAdapter(getBaseContext(), null);
                        adapter.atualiza();
                        Toast toast = Toast.makeText(getApplicationContext(), "Adicionado com sucesso", Toast.LENGTH_SHORT);
                        toast.show();
                        finish();
                    }
                    else {
                        titulo.requestFocus();
                        Toast toast = Toast.makeText(getApplicationContext(), "Ja existe um livro com esse nome", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }

            }
        });
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

    }
}
