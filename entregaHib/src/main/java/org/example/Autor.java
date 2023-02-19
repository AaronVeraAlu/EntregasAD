package org.example;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(value = "Autor")
public class Autor extends Persona {
    @Column(name = "nacionalidad")
    private String nacionalidad;
    @OneToMany(mappedBy = "autor",fetch = FetchType.LAZY)
    List<Libro> libros = new ArrayList<>();

    public Autor() {
    }

    public Autor(String nombre, String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "nombre='" + getNombre() +'\'' +
                "direccion='" + getDireccion().getNombreCalle() +'\'' +
                "nacionalidad='" + nacionalidad + '\'' +
                '}';
    }
}
