package com.example.phlo.myapplication.Banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.example.phlo.myapplication.Model.LivroModel;
import com.example.phlo.myapplication.Model.ParticipanteModel;
import com.example.phlo.myapplication.Model.ReservaModel;

import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by phlo on 08/12/17.
 */

public class BibliotecaDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "Biblioteca.db";

    public BibliotecaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(BibliotecaContract.SQL_CREATE_LIVRO);
        sqLiteDatabase.execSQL(BibliotecaContract.SQL_CREATE_PARTICIPANTE);
        sqLiteDatabase.execSQL(BibliotecaContract.SQL_CREATE_Reserva);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(BibliotecaContract.SQL_DROP_LIVRO);
        sqLiteDatabase.execSQL(BibliotecaContract.SQL_DROP_PARTICIPANTE);
        sqLiteDatabase.execSQL(BibliotecaContract.SQL_DROP_Reserva);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    private ContentValues getContentValues(Object objeto) {
        ContentValues values = new ContentValues();
        if (objeto instanceof LivroModel) {
            LivroModel livro = (LivroModel) objeto;
            if (!TextUtils.isEmpty(livro.getTitulo())) {
                values.put(BibliotecaContract.Livro.COLUMN_NAME_TITULO, livro.getTitulo());
            }
            if (!TextUtils.isEmpty(livro.getEditora())) {
                values.put(BibliotecaContract.Livro.COLUMN_NAME_EDITORA, livro.getEditora());
            }
            if (!TextUtils.isEmpty(String.valueOf(livro.getAno()))) {
                values.put(BibliotecaContract.Livro.COLUMN_NAME_ANO, livro.getAno());

            }
        } else if (objeto instanceof ParticipanteModel) {
            ParticipanteModel participante = (ParticipanteModel) objeto;
            if (!TextUtils.isEmpty(participante.getNome())) {
                values.put(BibliotecaContract.Participante.COLUMN_NAME_NOME, participante.getNome());
            }
            if (!TextUtils.isEmpty(participante.getEmail())) {
                values.put(BibliotecaContract.Participante.COLUMN_NAME_EMAIL, participante.getEmail());

            }
            values.put(BibliotecaContract.Participante.COLUMN_NAME_ENTRADA,"");
            values.put(BibliotecaContract.Participante.COLUMN_NAME_SAIDA,"");
        } else if (objeto instanceof ReservaModel) {
                ReservaModel reserva = (ReservaModel) objeto;
                if (!TextUtils.isEmpty(reserva.getLivronome())) {
                    values.put(BibliotecaContract.Reserva.COLUMN_NAME_TITULOLIVRO, reserva.getLivronome());
                }
                if (!TextUtils.isEmpty(reserva.getParticipantesnome())) {
                    values.put(BibliotecaContract.Reserva.COLUMN_NAME_NomeParticipante, reserva.getParticipantesnome());
                }
            }
        return values;
        }
        /*else if(objeto instanceof ReservaModel){
            ReservaModel reserva=(ReservaModel)objeto;
            (!TextUtils.isEmpty(reserva.getParticipantes().get)){
                values.put(BibliotecaContract.Participante.COLUMN_NAME_NOME,participante.getNome());
            }
            if(!TextUtils.isEmpty(reserva.getEmail())){
                values.put(BibliotecaContract.Participante.COLUMN_NAME_EMAIL,participante.getEmail());
            }
        }*/


    public boolean VerificaLivro(LivroModel livro){
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor c = db.rawQuery("SELECT * FROM "+ BibliotecaContract.Livro.TABLE_NAME+" WHERE  "+ BibliotecaContract.Livro.TABLE_NAME+"."+
                    BibliotecaContract.Livro.COLUMN_NAME_TITULO +" = " +"'"+livro.getTitulo()+"'",null);
            if(c!=null && c.moveToFirst()){
                return false;
            }
            else
                return true;

        } catch (Exception e) {
            Log.e("BIBLIO", e.getLocalizedMessage());
            Log.e("BIBLIO", e.getStackTrace().toString());
            return false;
        }
    }
    public Boolean salvar(Object objeto) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = getContentValues(objeto);
            if (objeto instanceof LivroModel) {

                if(VerificaLivro((LivroModel)objeto)) {
                    db.insert(BibliotecaContract.Livro.TABLE_NAME, null, values);
                    return true;
                }
                else
                    return false;
            } else if (objeto instanceof ParticipanteModel) {

                db.insert(BibliotecaContract.Participante.TABLE_NAME, null, values);
                return true;
            } else if (objeto instanceof ReservaModel) {
                db.insert(BibliotecaContract.Reserva.TABLE_NAME, null, values);
                return true;

            }
        }catch (SQLiteConstraintException e){
            return  false;
        }
        return false;
    }

    public void entrada(String entrada, int id) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(BibliotecaContract.Participante.COLUMN_NAME_ENTRADA, entrada);
            String sel = BibliotecaContract.Participante._ID + "=?";
            db.update(BibliotecaContract.Participante.TABLE_NAME, values, sel,
                    new String[]{Integer.toString(id)});
        } catch (Exception e) {
            Log.e("erro", e.getLocalizedMessage());
        }
    }

    public void saida(String entrada, int id) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(BibliotecaContract.Participante.COLUMN_NAME_SAIDA, entrada);
            String sel = BibliotecaContract.Participante._ID + "=?";
            db.update(BibliotecaContract.Participante.TABLE_NAME, values, sel,
                    new String[]{Integer.toString(id)});
        } catch (Exception e) {
            Log.e("erro", e.getLocalizedMessage());

        }


    }

    public String consultaEntrada(int id) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String[] visao = {
                    BibliotecaContract.Participante._ID,
                    BibliotecaContract.Participante.COLUMN_NAME_NOME,
                    BibliotecaContract.Participante.COLUMN_NAME_EMAIL,
                    BibliotecaContract.Participante.COLUMN_NAME_ENTRADA,

                    BibliotecaContract.Participante.COLUMN_NAME_SAIDA,


            };
            String selecao = BibliotecaContract.Participante._ID + "=?";
            String []args = {Integer.toString(id)};
            Cursor c = db.query(BibliotecaContract.Participante.TABLE_NAME, visao, selecao, args, null, null, null);
            c.moveToFirst();
            String entrada = c.getString(c.getColumnIndex(BibliotecaContract.Participante.COLUMN_NAME_ENTRADA));
            return entrada;

        } catch (Exception e) {
            Log.e("BIBLIO", e.getLocalizedMessage());
            Log.e("BIBLIO", e.getStackTrace().toString());
            return null;
        }

    }

    public String consultaSaida(int id) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String[] visao = {
                    BibliotecaContract.Participante._ID,
                    BibliotecaContract.Participante.COLUMN_NAME_NOME,
                    BibliotecaContract.Participante.COLUMN_NAME_EMAIL,
                    BibliotecaContract.Participante.COLUMN_NAME_ENTRADA,

                    BibliotecaContract.Participante.COLUMN_NAME_SAIDA,


            };
            String selecao = BibliotecaContract.Participante._ID + "=?";
            String []args = {Integer.toString(id)};
            Cursor c = db.query(BibliotecaContract.Participante.TABLE_NAME, visao, selecao, args, null, null, null);
            c.moveToFirst();
            String saida = c.getString(c.getColumnIndex(BibliotecaContract.Participante.COLUMN_NAME_SAIDA));
            return saida;

        } catch (Exception e) {
            Log.e("BIBLIO", e.getLocalizedMessage());
            Log.e("BIBLIO", e.getStackTrace().toString());
            return null;
        }

    }

    public ParticipanteModel Participantes(int i) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String[] visao = {
                    BibliotecaContract.Participante._ID,
                    BibliotecaContract.Participante.COLUMN_NAME_NOME,
                    BibliotecaContract.Participante.COLUMN_NAME_EMAIL,
                    BibliotecaContract.Participante.COLUMN_NAME_ENTRADA,

                    BibliotecaContract.Participante.COLUMN_NAME_SAIDA,


            };
            String sel = BibliotecaContract.Participante._ID + "=?";
            String arg[] = {Integer.toString(i)};
            Cursor c = db.query(BibliotecaContract.Participante.TABLE_NAME, visao, sel, arg, null, null, null);
            c.moveToFirst();
            ParticipanteModel participante = new ParticipanteModel(c.getString(c.getColumnIndex(BibliotecaContract.Participante.COLUMN_NAME_NOME)),
                    c.getString(c.getColumnIndex(BibliotecaContract.Participante.COLUMN_NAME_EMAIL)
                    ), c.getString(c.getColumnIndex(BibliotecaContract.Participante.COLUMN_NAME_ENTRADA)
            ), c.getString(c.getColumnIndex(BibliotecaContract.Participante.COLUMN_NAME_SAIDA)
            ));
            return participante;
        } catch (Exception e) {
            Log.e("BIBLIO", e.getLocalizedMessage());
            Log.e("BIBLIO", e.getStackTrace().toString());
            return null;
        }
    }
    public LivroModel livros(int id ){
        try {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] visao = {
                BibliotecaContract.Livro._ID,
                BibliotecaContract.Livro.COLUMN_NAME_TITULO,
                BibliotecaContract.Livro.COLUMN_NAME_EDITORA,
                BibliotecaContract.Livro.COLUMN_NAME_ANO
        };
        String selecao = BibliotecaContract.Livro._ID + "=?";
        String arg[] = {Integer.toString(id)};
        Cursor c = db.query(BibliotecaContract.Livro.TABLE_NAME, visao, selecao, arg, null, null, null);
        c.moveToFirst();
        LivroModel l = new LivroModel(c.getString(c.getColumnIndex(BibliotecaContract.Livro.COLUMN_NAME_TITULO)),
                    c.getString(c.getColumnIndex(BibliotecaContract.Livro.COLUMN_NAME_EDITORA)),
                    Integer.parseInt(c.getString(c.getColumnIndex(BibliotecaContract.Livro.COLUMN_NAME_ANO)))
        );
        return l;
        } catch (Exception e) {
            Log.e("BIBLIO", e.getLocalizedMessage());
            Log.e("BIBLIO", e.getStackTrace().toString());
            return null;
        }
    }


    public Cursor atualizaParticipantes(){
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String[] visao = {
                    BibliotecaContract.Participante._ID,
                    BibliotecaContract.Participante.COLUMN_NAME_NOME,
                    BibliotecaContract.Participante.COLUMN_NAME_EMAIL,
                    BibliotecaContract.Participante.COLUMN_NAME_ENTRADA,
                    BibliotecaContract.Participante.COLUMN_NAME_SAIDA,


            };

            Cursor c = db.query(BibliotecaContract.Participante.TABLE_NAME, visao, null, null, null, null, null);
            return c;

        } catch (Exception e) {
            Log.e("BIBLIO", e.getLocalizedMessage());
            Log.e("BIBLIO", e.getStackTrace().toString());
            return  null;
        }
    }
    public Cursor atualizaLivro(){

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String[] visao = {
                    BibliotecaContract.Livro._ID,
                    BibliotecaContract.Livro.COLUMN_NAME_TITULO,
                    BibliotecaContract.Livro.COLUMN_NAME_EDITORA,
                    BibliotecaContract.Livro.COLUMN_NAME_ANO

            };

            Cursor c = db.query(BibliotecaContract.Livro.TABLE_NAME, visao, null, null, null, null, null);
            return c;

        } catch (Exception e) {
            Log.e("BIBLIO", e.getLocalizedMessage());
            Log.e("BIBLIO", e.getStackTrace().toString());
            return null;
        }
    }
    public ArrayList<String> getTodosParticipanteNome(){
        try{
        ArrayList<String> list_partic = new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c =db.rawQuery("SELECT * FROM "+ BibliotecaContract.Participante.TABLE_NAME,null);
        if(c!=null && c.moveToFirst()){
            do{
                list_partic.add(c.getString(c.getColumnIndex(BibliotecaContract.Participante.COLUMN_NAME_NOME)));
            }while(c.moveToNext());
        }
        return list_partic;
        } catch (Exception e) {
            Log.e("BIBLIO", e.getLocalizedMessage());
            Log.e("BIBLIO", e.getStackTrace().toString());
            return null;
        }
    }
    public ArrayList<String>getLivroTitulo(){
        try{
        SQLiteDatabase db=this.getReadableDatabase();
        ArrayList<String>list_livro = new ArrayList<>();
        Cursor c2 =db.rawQuery("SELECT * FROM "+ BibliotecaContract.Livro.TABLE_NAME,null);
        if(c2!=null && c2.moveToFirst()){
            do{
                list_livro.add(c2.getString(c2.getColumnIndex(BibliotecaContract.Livro.COLUMN_NAME_TITULO)));
            }while(c2.moveToNext());
        }
        return list_livro;
    } catch (Exception e) {
        Log.e("BIBLIO", e.getLocalizedMessage());
        Log.e("BIBLIO", e.getStackTrace().toString());
        return null;
    }
    }
    public ArrayList<String>getParticipantesReserva(String titulo){
        try {
        SQLiteDatabase db=this.getReadableDatabase();
        ArrayList<String> list_partic = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM "+ BibliotecaContract.Reserva.TABLE_NAME+" WHERE "+ BibliotecaContract.Reserva.TABLE_NAME+"."+
                BibliotecaContract.Reserva.COLUMN_NAME_TITULOLIVRO +" = " +"'"+titulo+"'",null);
        if(c!=null && c.moveToFirst()){
            do{
                list_partic.add(c.getString(c.getColumnIndex(BibliotecaContract.Reserva.COLUMN_NAME_NomeParticipante)));
            }while(c.moveToNext());
        }
        return list_partic;
        } catch (Exception e) {
            Log.e("BIBLIO", e.getLocalizedMessage());
            Log.e("BIBLIO", e.getStackTrace().toString());
            return null;
        }
    }
    public ArrayList<String> getRev(){
        try{
            ArrayList<String> list_partic = new ArrayList<>();
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor c =db.rawQuery("SELECT * FROM "+ BibliotecaContract.Reserva.TABLE_NAME,null);
            if(c!=null && c.moveToFirst()){
                do{
                    list_partic.add(c.getString(c.getColumnIndex(BibliotecaContract.Reserva.COLUMN_NAME_TITULOLIVRO)));
                }while(c.moveToNext());
            }
            return list_partic;
        } catch (Exception e) {
            Log.e("BIBLIO", e.getLocalizedMessage());
            Log.e("BIBLIO", e.getStackTrace().toString());
            return null;
        }
    }

}