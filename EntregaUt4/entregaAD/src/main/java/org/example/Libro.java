package org.example;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class Libro {
    @BsonId
    private ObjectId id;
    @BsonProperty(value = "nombre")
    private String nombre;
    private String autor;
    private float precio;
    private ArrayList<String> generos = new ArrayList<String>();

    public Libro() {
    }

    public Libro(String nombre, String autor, float precio, ArrayList<String> generos) {
        this.nombre = nombre;
        this.autor = autor;
        this.precio = precio;
        this.generos = generos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public ArrayList<String> getGeneros() {
        return generos;
    }

    public void setGeneros(ArrayList<String > generos) {
        this.generos = generos;
    }

    public void addGenero(String genero){
        generos.add(genero);
    }

    @Override
    public String toString() {
        return "Libro{" +
                ", nombre='" + nombre + '\'' +
                ", autor='" + autor + '\'' +
                ", precio=" + precio +
                ", generos=" + generos +
                '}';
    }
}
