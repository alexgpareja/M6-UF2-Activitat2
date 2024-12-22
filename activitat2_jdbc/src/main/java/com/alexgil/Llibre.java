package com.alexgil;

public class Llibre {

    // Propiedades
    private int idLlibre;
    private long isbn;
    private String titol;
    private String autor;
    private int anyPublicacio;
    private boolean disponibilitat;
    private int idCategoria;

    // Constructor por defecto: con todos los parametros obligatoriamente
    public Llibre(int idLlibre, long isbn, String titol, String autor, int anyPublicacio, boolean disponibilitat,
            int idCategoria) {
        this.idLlibre = idLlibre;
        this.isbn = isbn;
        this.titol = titol;
        this.autor = autor;
        this.anyPublicacio = anyPublicacio;
        this.disponibilitat = disponibilitat;
        this.idCategoria = idCategoria;
    }

    // Getters y Setters
    public int getIdLlibre() {
        return idLlibre;
    }

    public void setIdLlibre(int idLlibre) {
        this.idLlibre = idLlibre;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnyPublicacio() {
        return anyPublicacio;
    }

    public void setAnyPublicacio(int anyPublicacio) {
        this.anyPublicacio = anyPublicacio;
    }

    public boolean isDisponibilitat() {
        return disponibilitat;
    }

    public void setDisponibilitat(boolean disponibilitat) {
        this.disponibilitat = disponibilitat;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public String toString() {
        return "Llibre{" +
                "idLlibre=" + idLlibre +
                ", isbn=" + isbn +
                ", titol='" + titol + '\'' +
                ", autor='" + autor + '\'' +
                ", anyPublicacio=" + anyPublicacio +
                ", disponibilitat=" + disponibilitat +
                ", idCategoria=" + idCategoria +
                '}';
    }

}
