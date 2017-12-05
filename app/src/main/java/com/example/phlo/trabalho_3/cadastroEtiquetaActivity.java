package com.example.phlo.trabalho_3;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.phlo.trabalho_3.Banco.EtiquetasContract;
import com.example.phlo.trabalho_3.Banco.TarefasContract;
import com.example.phlo.trabalho_3.Banco.dbHelper;

public class cadastroEtiquetaActivity extends AppCompatActivity {

    private EditText nameEtiqueta;
    private Button btnEtiqueta;
    dbHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_etiqueta);

        nameEtiqueta =(EditText)findViewById(R.id.nameEtiqueta);
        btnEtiqueta = (Button) findViewById( R.id.btnEtiqueta);
        dbhelper = new dbHelper(getApplicationContext());
        btnEtiqueta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbhelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(EtiquetasContract.Etiquetas.COLUMN_NAME_NOME,nameEtiqueta.getText().toString());
                db.insert(EtiquetasContract.Etiquetas.TABLE_NAME, null, values);
                Toast.makeText(getApplicationContext(),"Cadastrado com sucesso",Toast.LENGTH_LONG).show();

                finish();
            }
        });
    }
}
