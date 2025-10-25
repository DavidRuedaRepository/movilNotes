package com.example.mis_notitas;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GestorBaseDatos extends SQLiteOpenHelper {


    public GestorBaseDatos(@Nullable Context context) {
        super(context, "notitas", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE notas (id INTEGER PRIMARY KEY AUTOINCREMENT, nota TEXT)");
        db.execSQL("INSERT INTO notas VALUES(null, 'Ejemplo primera nota')");
        db.execSQL("INSERT INTO notas VALUES(null, 'Ejemplo segunda nota')");
        db.execSQL("INSERT INTO notas VALUES(null, 'Ejemplo tercera nota')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public ArrayList<String> obtenerNotas() {
        ArrayList<String> notas = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM notas", null);

        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                notas.add(cursor.getInt(0) + ".-" + cursor.getString(1));
                cursor.moveToNext();
            }
        }
        return notas;
    }

    public boolean insertarNota(String id) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO notas VALUES(null, '" + id + "')");

        return true;
    }


    public boolean borrarNota(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM notas WHERE id = " + id);


        return false;
    }

    public void borrarTodo(){

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM notas");
    }


}

