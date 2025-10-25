package com.example.mis_notitas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VerNotaActivity extends AppCompatActivity {

    protected String notaSeleccionada;
    protected GestorBaseDatos gbd;
    protected Intent pasarPantalla;

    protected Button botonVolver;

    protected Button botonBorrar;

    protected TextView texto1;

    protected TextView texto2;

    protected int selectedId;

    protected Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver_nota);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        gbd = new GestorBaseDatos(this);

        texto1 = (TextView) findViewById(R.id.textView_verNota);
        texto2 = (TextView) findViewById(R.id.textView2);
        botonVolver = (Button) findViewById(R.id.boton1_volver_verNota);
        botonBorrar = (Button) findViewById(R.id.boton2_borrar_verNota);


        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasarPantalla = new Intent(VerNotaActivity.this, ListadoActivity.class);
                startActivity(pasarPantalla);
            }
        });

        botonBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                   if(!gbd.borrarNota(selectedId)) {
                       Toast.makeText(VerNotaActivity.this, getString(R.string.toast_texto1_verNotaActivity), Toast.LENGTH_SHORT).show();

                       pasarPantalla = new Intent(VerNotaActivity.this, ListadoActivity.class);
                       startActivity(pasarPantalla);
                   }
                   else {
                       Toast.makeText(VerNotaActivity.this, getString(R.string.toast_texto2_verNotaActivity), Toast.LENGTH_SHORT).show();
                   }

            }
        });


        extras = getIntent().getExtras();

        if (extras != null) {
            selectedId = extras.getInt("ID");
            notaSeleccionada = extras.getString("NOTA");

            texto2.setText(notaSeleccionada);
        } else {
            Toast.makeText(this, getString(R.string.toast_texto3_verNotaActivity), Toast.LENGTH_SHORT).show();
        }


    }
}