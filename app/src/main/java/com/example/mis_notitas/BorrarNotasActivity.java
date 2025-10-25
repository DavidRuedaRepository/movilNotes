package com.example.mis_notitas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BorrarNotasActivity extends AppCompatActivity {

    protected GestorBaseDatos gbd;
    protected Button botonBorrarTodo;

    protected Button volver;

    protected Intent pasarPantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_borrar_notas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.gbd = new GestorBaseDatos(this);

        botonBorrarTodo = (Button) findViewById(R.id.boton1_borrarTodo);
        volver = (Button) findViewById(R.id.boton2_borrarTodo);


        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasarPantalla = new Intent(BorrarNotasActivity.this, ListadoActivity.class);
                startActivity(pasarPantalla);
            }
        });

        botonBorrarTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                gbd.borrarTodo();
                Toast.makeText(BorrarNotasActivity.this, getString(R.string.toast_texto1_borrarNotasActivity), Toast.LENGTH_SHORT).show();
                pasarPantalla = new Intent(BorrarNotasActivity.this, ListadoActivity.class);
                startActivity(pasarPantalla);

            }
        });







    }
}