package com.example.phlo.trabalho_3;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.phlo.trabalho_3.Banco.TarefasContract;
import com.example.phlo.trabalho_3.Banco.dbHelper;

public class cadastroTarefaActivity extends AppCompatActivity {
    private EditText titulo;
    private EditText descricao;
    private EditText dificuldade;
    private Button btnCadastrar;
    dbHelper dbhelper;
    private Spinner estado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titulo = (EditText) findViewById(R.id.Titulo);
        descricao = (EditText) findViewById(R.id.descricao);
        dificuldade = (EditText) findViewById(R.id.dificuldade);
        btnCadastrar=(Button)findViewById(R.id.btnCadastro);
        estado=(Spinner)findViewById(R.id.estado);
        dbhelper = new dbHelper(getApplicationContext());

        ArrayAdapter adapter= ArrayAdapter.createFromResource(this,R.array.estados,android.R.layout.simple_spinner_item);

        estado.setAdapter(adapter);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                try{
                int controleDificuldade=Integer.parseInt(dificuldade.getText().toString());
                if(controleDificuldade>0 && controleDificuldade<= 5) {   //controle de 1 a 5 a dificuldade
                    SQLiteDatabase db = dbhelper.getWritableDatabase();

                    ContentValues values = new ContentValues();
                    values.put(TarefasContract.Tarefas.COLUMN_NAME_TITULO, titulo.getText().toString());
                    values.put(TarefasContract.Tarefas.COLUMN_NAME_Descricao, descricao.getText().toString());
                    values.put(TarefasContract.Tarefas.COLUMN_NAME_Dificuldade, Integer.parseInt(dificuldade.getText().toString()));
                    values.put(TarefasContract.Tarefas.COLUMN_NAME_ESTADO, estado.getSelectedItem().toString());

                    db.insert(TarefasContract.Tarefas.TABLE_NAME, null, values);
                    Toast.makeText(getApplicationContext(),"Cadastrado com sucesso",Toast.LENGTH_LONG).show();

                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Colocar dificuldade Valida",Toast.LENGTH_LONG).show();

                }

            }
            catch (Exception e){
                Log.e("erro",e.getLocalizedMessage());
            }
            }
        });
    }
}
