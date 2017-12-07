package com.example.phlo.trabalho_3;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
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

import com.example.phlo.trabalho_3.Banco.EtiquetaTarefaContract;
import com.example.phlo.trabalho_3.Banco.EtiquetasContract;
import com.example.phlo.trabalho_3.Banco.TarefasContract;
import com.example.phlo.trabalho_3.Banco.dbHelper;

import java.util.ArrayList;

public class CadastrarTag_TarefaActivity extends AppCompatActivity {
    private dbHelper dbhelper;
    private Spinner Tarefa;
    private Spinner Tag;
    private Button associar;
    private EditText txtTarefaId;
    private EditText txtEtiquetaId;
;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_tag__tarefa);
        ArrayList<String> tarefa = new ArrayList<String>();

        ArrayList<String> etiqueta = new ArrayList<String>();

        Tarefa=(Spinner)findViewById(R.id.Tarefa);
        Tag=(Spinner)findViewById(R.id.Tag);
        associar=(Button)findViewById(R.id.associar);
        txtTarefaId=(EditText)findViewById(R.id.txtTarefaId);
        txtEtiquetaId=(EditText)findViewById(R.id.txtEtiquetaId);
        //Popula os Spinner

        SQLiteDatabase db=openOrCreateDatabase(dbHelper.DATABASE_NAME, Context.MODE_PRIVATE,null);
        Cursor c =db.rawQuery("SELECT * FROM "+ TarefasContract.Tarefas.TABLE_NAME,null);
        if(c!=null && c.moveToFirst()){
            do{
                tarefa.add(c.getString(c.getColumnIndex(TarefasContract.Tarefas.COLUMN_NAME_TITULO)));
            }while(c.moveToNext());
        }
        ArrayAdapter adapter =  new ArrayAdapter(this,android.R.layout.simple_spinner_item,tarefa);
        Tarefa.setAdapter(adapter);
        Cursor c2 =db.rawQuery("SELECT * FROM "+ EtiquetasContract.Etiquetas.TABLE_NAME,null);
        if(c2!=null && c2.moveToFirst()){
            do{
                etiqueta.add(c2.getString(c2.getColumnIndex(EtiquetasContract.Etiquetas.COLUMN_NAME_NOME)));
            }while(c2.moveToNext());
        }
        ArrayAdapter adapter2 =  new ArrayAdapter(this,android.R.layout.simple_spinner_item,etiqueta);
        Tag.setAdapter(adapter2);
        db.close();

        associar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            try {
                    SQLiteDatabase db=openOrCreateDatabase(dbHelper.DATABASE_NAME, Context.MODE_PRIVATE,null);
                    ContentValues values = new ContentValues();

                    values.put(EtiquetaTarefaContract.EtiquetaTarefa.COLUMN_NAME_TAREFAID, Tarefa.getSelectedItem().toString());
                    values.put(EtiquetaTarefaContract.EtiquetaTarefa.COUMN_NAME_ETIQUETAID, Tag.getSelectedItem().toString());
                    db.insert(EtiquetaTarefaContract.EtiquetaTarefa.TABLE_NAME, null, values);
                    Toast.makeText(getApplicationContext(), "Associado  com sucesso", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(CadastrarTag_TarefaActivity.this,ListarEtiquetaActivity.class));
                    db.close();

                }catch (SQLException e){
                    Log.e("erro",e.getLocalizedMessage());
                     Toast.makeText(getApplicationContext(), "Ja existe Associacao", Toast.LENGTH_LONG).show();
                }catch (Exception e){
                Log.e("erro2",e.getLocalizedMessage());
            }
            }
        });
    }
}
