package com.example.proyecto_capstone.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.proyecto_capstone.R;

public class HOME extends AppCompatActivity {

    private ImageButton btnSalir;
    private Button btnGeolocalizacion, btnListarClientes, btnReservas;
    private EditText txtAdministrador;
    private Bundle parameters;
    private String nombreUsuario = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        parameters = getIntent().getExtras();
        loadParameters();

        findViews();
        onClicks();

        txtAdministrador.setText(nombreUsuario);
    }

    private void loadParameters(){
        if (parameters != null){
            if (parameters.containsKey("nombreUsuario")){
                this.nombreUsuario = parameters.getString("nombreUsuario");
            }
        }
    }
    private void findViews(){
        btnSalir = findViewById(R.id.btnSalir);
        btnGeolocalizacion = findViewById(R.id.btnGeolocalizacion);
        btnListarClientes = findViewById(R.id.btnListarClientes);
        btnReservas = findViewById(R.id.btnReservas);
        txtAdministrador = findViewById(R.id.txtAdministrador);
    }
    private void onClicks(){
        btnGeolocalizacion.setOnClickListener(v -> {
            Intent intent = new Intent(HOME.this, MapsActivity.class);
            startActivity(intent);
        });

        btnSalir.setOnClickListener(v -> {
            finish();
        });
    }
}