package com.example.phlo.myapplication;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.phlo.myapplication.Banco.BibliotecaContract;
import com.example.phlo.myapplication.Banco.BibliotecaDbHelper;
import com.example.phlo.myapplication.Model.ParticipanteModel;

public class ActivityCadastroParticipante extends AppCompatActivity {

    private Button btnSalvar;
    private Button btnVoltar;
    private EditText nome;
    private EditText email;
    private BibliotecaDbHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_participante);
        btnSalvar=(Button)findViewById(R.id.btnSalvar);
        btnVoltar=(Button)findViewById(R.id.btnVoltar);
        nome=(EditText)findViewById(R.id.nome);
        email=(EditText)findViewById(R.id.email);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomes=nome.getText().toString();
                String emails=email.getText().toString();

                if(nomes.isEmpty()){
                    nome.requestFocus();
                }
                else if(emails.isEmpty()){
                    email.requestFocus();
                }
                else
                    {

                            ParticipanteModel participante = new ParticipanteModel(nomes,emails,null,null);
                            dbhelper= new BibliotecaDbHelper(getApplicationContext());
                            if(dbhelper.salvar(participante)) {
                                MainActivity.adapter.atualiza();
                                Toast toast = Toast.makeText(getApplicationContext(), "Adicionado com sucesso", Toast.LENGTH_SHORT);
                                toast.show();
                                finish();
                            }
                            else
                            {
                                Toast toast = Toast.makeText(getApplicationContext(), "Participante nao foi adicionado", Toast.LENGTH_SHORT);
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
