package org.example;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
@Table(name="libros")
public class Libro implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "libro_id")
    Long id;
    @Column(name="titulo")
    private String titulo;
    @Column(name = "precio")
    private int precio;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "autor", referencedColumnName = "_id")
    private Autor autor;

    public Libro() {
    }

    public Libro(String titulo, int precio, Autor autor) {
        this.titulo = titulo;
        this.precio = precio;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Libro{" +
                ", titulo='" + titulo + '\'' +
                ", precio=" + precio +
                ", autor=" + autor.getNombre() +
                '}';
    }
}
