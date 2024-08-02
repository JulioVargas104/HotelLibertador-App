package com.example.proyecto_capstone.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.proyecto_capstone.R;
import com.example.proyecto_capstone.activities.HOME;
import com.example.proyecto_capstone.controller.cUsuario;
import com.example.proyecto_capstone.models.Usuario;

public class LOGIN extends AppCompatActivity {

    Button btnLogin;
    EditText edtUser, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViews();
        onClicks();
    }

    private void findViews(){
        edtUser = findViewById(R.id.txtUser);
        edtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }

    private void onClicks(){
        btnLogin.setOnClickListener(v -> {
            Login(edtUser.getText().toString(), edtPassword.getText().toString());
        });
    }

    private void Login(String user, String psw){
        try {
            Usuario usr = null;

            Log.e("USER", user);
            Log.e("PSW", psw);

            cUsuario con = new cUsuario(this);
            usr =con.getUsuario(user, psw);

            if (usr == null){
                Toast.makeText(this, "Usuario y/o contraseña incorrectos",
                        Toast.LENGTH_SHORT).show();
            }
            else{
                Intent intent = new Intent(this, HOME.class);
                intent.putExtra("nombreUsuario", usr.getNombres());
                intent.putExtra("usuario", usr.getUsuario());
                startActivity(intent);
            }
        }
        catch (Exception ex){
            Log.e("ERROR LOGIN", ex.getMessage());
        }
    }
}