package com.example.proyecto_capstone.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.proyecto_capstone.bd.DatabaseConnection;
import com.example.proyecto_capstone.models.Usuario;

public class cUsuario extends DatabaseConnection {
    Context context;
    public cUsuario(Context context) {
        super(context);
        this.context = context;
    }
    public Usuario getUsuario(String user, String password){
        DatabaseConnection connection = new cUsuario(context);
        SQLiteDatabase database = connection.getReadableDatabase();
        Usuario usr = null;
        Cursor dato = null;
        dato = database.rawQuery("select * from " + tablaUsuario +
                        " where usuario='"+user+"' and contrasena='"+password+"'", null);
        if (dato.moveToFirst()){
            usr = new Usuario();
            usr.setCodigo(dato.getString(0));
            usr.setNombres(dato.getString(1));
            usr.setUsuario(dato.getString(2));
            usr.setContrasena(dato.getString(3));
        }
        dato.close();
        return usr;
    }
}
