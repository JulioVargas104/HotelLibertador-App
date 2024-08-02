package com.example.proyecto_capstone.bd;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseConnection extends SQLiteOpenHelper {
    Resources mResources;
    Context context;
    SQLiteDatabase db;
    private static final String DATABASE_NAME = "bd_hotelLibertador.db";
    private static final int DATABASE_VERSION = 1;
    public static final String tablaUsuario = "usuarios";
    public static final String tablaReserva = "reservas";

    public DatabaseConnection(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        mResources = context.getResources();
        db = this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + tablaUsuario + " (codigo TEXT PRIMARY KEY" +
                ", nombres TEXT NOT NULL " +
                ", usuario TEXT NOT NULL " +
                ", contrasena TEXT NOT NULL)");
        db.execSQL("create table " + tablaReserva + " (codigo TEXT PRIMARY KEY" +
                ", clienteNombre TEXT NOT NULL " +
                ", estado INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + tablaUsuario);
        db.execSQL("DROP TABLE " + tablaReserva);
        context.deleteDatabase(DATABASE_NAME);
        onCreate(db);
    }
}
