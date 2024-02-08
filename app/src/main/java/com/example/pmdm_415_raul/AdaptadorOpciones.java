package com.example.pmdm_415_raul;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

// Definimos el Adaptador a partir de la clase RecyclerView.Adapter
// que dibuja las opciones del listado del tipo RecyclerView.
// Además, implementa el evento onClick del mismo
class AdaptadorOpciones extends RecyclerView.Adapter<OpcionViewHolder> implements View.OnClickListener {
    // Matriz dinámica para guardar los datos
    private ArrayList<Opcion> datos;

    // Variable para guardar el "puntero" (referencia) al método onClick para llamarlo cuando sea necesario
    private View.OnClickListener listener;

    // Contructor del adaptador usando una matriz dinámica del tipo Opcion
    AdaptadorOpciones(ArrayList<Opcion> datos) {
        // Guardamos estas variables de la aplicación principal para usarlas luego
        this.datos = datos;
    }

    // Evento que se lanza cuando ya es necesario dibujar una opción del listado
    @Override
    public OpcionViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Inflamos la opción con el layout correspondiente
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.opcion, viewGroup, false);

        // Establecemos el evento onClick de la opción
        itemView.setOnClickListener(this);

        // Definimos la nueva opción a partir del elemento anterior
        return new OpcionViewHolder(itemView);
    }

    // Método que se lanza cada vez que Android debe dibujar una opción en una determinada posición
    @Override
    public void onBindViewHolder(OpcionViewHolder viewHolder, int pos) {
        // Simplemente obtenemos los datos en esa posición
        Opcion item = datos.get(pos);

        // Añadimos (bind=ligamos) al ViewHolder los datos
        viewHolder.bindOpcion(item);
    }

    // Devuelve el número de elementos de la lista
    @Override
    public int getItemCount() {
        return datos.size();
    }

    // Método para establecer el evento onClick de la lista
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    // Método que se ejecuta cuando se invoca el evento onClick
    @Override
    public void onClick(View view) {
        if (listener != null) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                listener.onClick(view);
                actualizarEstadoSeleccion(position); // Llama al método para actualizar el estado de selección
            }
        }
    }




    private int getAdapterPosition() {
        // Método para obtener la posición del adaptador
        return this.getAdapterPosition();
    }


    public ArrayList<Opcion> obtenerOpcionesSeleccionadas() {
        ArrayList<Opcion> opcionesSeleccionadas = new ArrayList<>();
        for (Opcion opcion : datos) {
            if (opcion.estaSeleccionado()) {
                opcionesSeleccionadas.add(opcion);
            }
        }
        return opcionesSeleccionadas;
    }


    public void actualizarEstadoSeleccion(int position) {
        Opcion opcion = datos.get(position);
        opcion.setSeleccionado(!opcion.estaSeleccionado());
        notifyItemChanged(position);
    }

}

// Clase que se usa para almacenar las 2 etiquetas de tipo TextView y un icono de tipo ImageView de una opción
class OpcionViewHolder extends RecyclerView.ViewHolder {
    private TextView titulo;
    private ImageView icono;

    public OpcionViewHolder(View itemView) {
        super(itemView);
        titulo = itemView.findViewById(R.id.label);
        icono = itemView.findViewById(R.id.icono);
    }

    public void bindOpcion(Opcion opcion) {
        titulo.setText(opcion.getNombre());
        icono.setImageResource(opcion.getImagenResId());
    }
}
