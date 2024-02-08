package com.example.pmdm_415_raul;

public class Opcion {
    private String nombre;
    private int imagenResId;
    private boolean seleccionado;

    public Opcion(String nombre, int imagenResId) {
        this.nombre = nombre;
        this.imagenResId = imagenResId;
        this.seleccionado = false; // Por defecto, el elemento no está seleccionado
    }

    public String getNombre() {
        return nombre;
    }

    public int getImagenResId() {
        return imagenResId;
    }

    public boolean estaSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
}
