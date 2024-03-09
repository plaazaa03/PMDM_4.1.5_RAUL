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
public class AdaptadorOpciones extends RecyclerView.Adapter<OpcionViewHolder> {
    private ArrayList<Opcion> datos;
    private View.OnClickListener listener;

    public AdaptadorOpciones(ArrayList<Opcion> datos) {
        this.datos = datos;
    }

    @Override
    public OpcionViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.opcion, viewGroup, false);
        itemView.setOnClickListener(listener);
        return new OpcionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OpcionViewHolder viewHolder, int pos) {
        Opcion item = datos.get(pos);
        viewHolder.bindOpcion(item);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public void actualizarEstadoSeleccion(int position) {
        Opcion opcion = datos.get(position);
        opcion.setSeleccionado(!opcion.estaSeleccionado());
        notifyItemChanged(position);
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
