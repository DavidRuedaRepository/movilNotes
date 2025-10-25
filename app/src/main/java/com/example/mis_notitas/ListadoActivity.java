package com.example.mis_notitas;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ListAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {


    protected ListView lista;

    protected Intent pasarPantalla;

    protected GestorBaseDatos gbd;

    protected ArrayList<String> listNotas = new ArrayList<String>();

    protected ArrayAdapter<String> adaptador;

    protected String notaSeleccionada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.gbd = new GestorBaseDatos(this);

        lista = (ListView) findViewById(R.id.lista1_listado);


        listNotas = gbd.obtenerNotas();
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listNotas);
        lista.setAdapter(adaptador);
        adaptador.clear();
        listNotas = gbd.obtenerNotas();
        adaptador.addAll(listNotas);
        adaptador.notifyDataSetChanged();

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                notaSeleccionada = adaptador.getItem(position);
                int selectedId = Integer.parseInt(notaSeleccionada.split(".-")[0]);


                Intent pasarPantalla = new Intent(ListadoActivity.this, VerNotaActivity.class);
                pasarPantalla.putExtra("ID", selectedId);
                pasarPantalla.putExtra("NOTA", notaSeleccionada);
                startActivity(pasarPantalla);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        // Handle item selection.
        if (id == R.id.crear_menu) {
            Toast.makeText(this, getString(R.string.toast_texto1_listadoActivity), Toast.LENGTH_SHORT).show();
            pasarPantalla = new Intent(ListadoActivity.this, CrearNotaActivity.class);
            startActivity(pasarPantalla);
        } else {
            pasarPantalla = new Intent(ListadoActivity.this,BorrarNotasActivity.class);
            startActivity(pasarPantalla);
        }
        return true;
    }


}





