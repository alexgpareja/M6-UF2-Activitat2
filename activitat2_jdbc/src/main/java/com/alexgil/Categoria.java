package com.alexgil;

public class Categoria {

    // Propiedades
    private int idCategoria;
    private String nombre;

    // Constructor
    public Categoria(int idCategoria, String nombre) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
    }

    // Getters y Setters
    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Categoria{idCategoria=" + idCategoria + ", nombre='" + nombre + "'}";
    }
}
