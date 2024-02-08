package com.example.pmdm_415_raul;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Opcion> datos;
    private Button btnAceptar;
    private RecyclerView recview;
    private AdaptadorOpciones adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas y datos
        btnAceptar = findViewById(R.id.btnAceptar);
        recview = findViewById(R.id.recview);
        datos = new ArrayList<>();

        // Agregar opciones al listado
        datos.add(new Opcion("Television", R.drawable.tv));
        datos.add(new Opcion("Telefono Movil", R.drawable.smartphone));
        datos.add(new Opcion("Tableta", R.drawable.tablet));
        datos.add(new Opcion("Ordenador Fijo", R.drawable.pc_fijo));
        datos.add(new Opcion("Ordenador Portatil", R.drawable.pc_portatil));
        datos.add(new Opcion("Reloj", R.drawable.reloj));

        // Configurar el RecyclerView y el adaptador
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recview.setLayoutManager(layoutManager);
        adaptador = new AdaptadorOpciones(datos);
        recview.setAdapter(adaptador);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Actualizar el estado de selección antes de obtener las opciones seleccionadas
                adaptador.notifyDataSetChanged(); // Asegurarse de que se actualice la vista
                ArrayList<Opcion> opcionesSeleccionadas = adaptador.obtenerOpcionesSeleccionadas();

                // Verificar si hay al menos una opción seleccionada
                if (!opcionesSeleccionadas.isEmpty()) {
                    // Construir el mensaje utilizando las opciones seleccionadas
                    StringBuilder mensaje = new StringBuilder("Has seleccionado los siguientes dispositivos:\n");
                    for (Opcion opcion : opcionesSeleccionadas) {
                        mensaje.append("- ").append(opcion.getNombre()).append("\n");
                    }

                    // Mostrar el mensaje
                    Toast.makeText(MainActivity.this, mensaje.toString(), Toast.LENGTH_SHORT).show();
                } else {
                    // Mostrar un mensaje si no se selecciona ninguna opción
                    //Toast.makeText(MainActivity.this, "Por favor, seleccione al menos una opción", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Manejar clics en el RecyclerView
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adaptador.actualizarEstadoSeleccion(recview.getChildAdapterPosition(v));
            }
        });
    }
}
