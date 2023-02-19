package org.example;

import jakarta.persistence.*;

import java.io.Serializable;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@Table(name="personas")
public abstract class Persona implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_id")
    Long id;
    @Column(name = "nombre")
    private String nombre;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccion_id",columnDefinition = "nombre")
    private Direccion direccion;

    public Persona() {
    }

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Persona{" +
                ", nombre='" + nombre + '\'' +
                ", direccion=" + direccion.getNombreCalle() +
                '}';
    }
}
