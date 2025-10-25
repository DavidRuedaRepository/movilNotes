package com.example.mis_notitas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CrearNotaActivity extends AppCompatActivity {

    protected Intent pasarPantalla;

    protected GestorBaseDatos gbd;

    protected EditText cajaNota;

    protected String contenidoNota;


    protected Button crearNota;

    protected Button volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear_nota);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        gbd = new GestorBaseDatos(this);

        crearNota = (Button) findViewById(R.id.button2_crear_crearNota);
        volver = (Button) findViewById(R.id.button1_volver_crearNota);
        cajaNota = (EditText) findViewById(R.id.editText_crearNota);


        crearNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                contenidoNota = cajaNota.getText().toString();
                if (contenidoNota.equalsIgnoreCase("")) {
                    Toast.makeText(CrearNotaActivity.this, getString(R.string.toast_texto1_crearNotaActivity), Toast.LENGTH_SHORT).show();
                } else {
                    //guardar nota

                    if (gbd.insertarNota(contenidoNota)) {
                        Toast.makeText(CrearNotaActivity.this, getString(R.string.toast_texto2_crearNotaActivity), Toast.LENGTH_SHORT).show();
                        pasarPantalla = new Intent(CrearNotaActivity.this, ListadoActivity.class);
                        startActivity(pasarPantalla);
                    } else {
                        Toast.makeText(CrearNotaActivity.this, getString(R.string.toast_texto3_crearNotaActivity), Toast.LENGTH_SHORT).show();
                    }

                }

            }


        });

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasarPantalla = new Intent(CrearNotaActivity.this, ListadoActivity.class);
                startActivity(pasarPantalla);
            }
        });


    }
}