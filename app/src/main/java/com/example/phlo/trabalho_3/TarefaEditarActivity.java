package com.example.phlo.trabalho_3;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
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

import com.example.phlo.trabalho_3.Adapter.TarefaAdapter;
import com.example.phlo.trabalho_3.Banco.TarefasContract;
import com.example.phlo.trabalho_3.Banco.dbHelper;

public class TarefaEditarActivity extends AppCompatActivity {
    private dbHelper tarefasHelper;
    private EditText txtId;
    private EditText txtTitulo;
    private EditText txtdescricao;
    private EditText txtDificuldade;
    private Spinner estado;
    private Button btnEditar;
    private Button btnRemover;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa_editar);
        txtId= (EditText) findViewById(R.id.txtId);

        txtTitulo= (EditText) findViewById(R.id.Titulo);
        txtdescricao= (EditText) findViewById(R.id.descricao);
        txtDificuldade= (EditText) findViewById(R.id.dificuldade);
        estado=(Spinner)findViewById(R.id.estado);
        btnEditar=(Button)findViewById(R.id.btnEditar);
        btnRemover=(Button)findViewById(R.id.btnRemover);

        ArrayAdapter adapter= ArrayAdapter.createFromResource(this,R.array.estados,android.R.layout.simple_spinner_item);
        estado.setAdapter(adapter);

        Intent i = getIntent();
        if(i!=null) {
            Bundle params=i.getExtras();
            if(params!= null){
                String id =params.getString("id");
                BuscarTarefa(id);
            }

        }
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int controleDificuldade=Integer.parseInt(txtDificuldade.getText().toString());
                    if(controleDificuldade>0 && controleDificuldade<= 5) {
                        SQLiteDatabase db = openOrCreateDatabase("Tabelas.db", Context.MODE_PRIVATE, null);
                        String id = txtId.getText().toString();
                        ContentValues values = new ContentValues();
                        values.put(TarefasContract.Tarefas.COLUMN_NAME_TITULO, txtTitulo.getText().toString());
                        values.put(TarefasContract.Tarefas.COLUMN_NAME_Descricao, txtdescricao.getText().toString());
                        values.put(TarefasContract.Tarefas.COLUMN_NAME_Dificuldade, Integer.parseInt(txtDificuldade.getText().toString()));
                        values.put(TarefasContract.Tarefas.COLUMN_NAME_ESTADO, estado.getSelectedItem().toString());
                        db.update(TarefasContract.Tarefas.TABLE_NAME, values, "_id=?", new String[]{id});
                        db.close();
                        TarefaAdapter adapter = new TarefaAdapter(getBaseContext(), null);
                        adapter.atualizar();
                        Toast.makeText(getApplicationContext(), "Alterado com sucesso", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), ListarTarefaActivity.class));
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Colocar dificuldade Valida",Toast.LENGTH_LONG).show();
                }
                catch (Exception e) {
                    Log.e("erro", e.getLocalizedMessage());

                } }
        });
        btnRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id =txtId.getText().toString();
                try{
                    SQLiteDatabase db=openOrCreateDatabase(dbHelper.DATABASE_NAME,Context.MODE_PRIVATE,null);
                    db.delete(TarefasContract.Tarefas.TABLE_NAME,"_id=?",new String[]{id});
                    db.close();
                    Toast.makeText(getApplicationContext(),"Excluido com sucesso",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), ListarTarefaActivity.class));
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),"erro ao excuir",Toast.LENGTH_LONG).show();

                }
            }
        });
        }

    private void BuscarTarefa(String id) {
        SQLiteDatabase db =openOrCreateDatabase("Tabelas.db", Context.MODE_PRIVATE,null);
        String sql ="SELECT * FROM tarefas WHERE _id=?";
        Cursor c=(SQLiteCursor)db.rawQuery(sql,new String[]{id});
        if(c.moveToFirst()){
            String ids = c.getString(c.getColumnIndex("_id"));
            String titulo=c.getString(c.getColumnIndex(TarefasContract.Tarefas.COLUMN_NAME_TITULO));
            String descricao=c.getString(c.getColumnIndex(TarefasContract.Tarefas.COLUMN_NAME_Descricao));
            String dificudade=c.getString(c.getColumnIndex(TarefasContract.Tarefas.COLUMN_NAME_Dificuldade));
            txtId.setText(ids.toString());
            txtTitulo.setText(titulo.toString());
            txtdescricao.setText(descricao.toString());
            txtDificuldade.setText(dificudade.toString());
            c.close();
            db.close();
        }
    }
}
