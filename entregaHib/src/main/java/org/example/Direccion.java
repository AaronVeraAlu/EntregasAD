package org.example;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "direcciones")
public class Direccion implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "direccion_id")
    Long id;
    private String tipoVia;
    @Column(name = "nombre")
    private String nombreCalle;

    public Direccion() {
    }

    public Direccion(String tipoVia, String nombreCalle) {
        this.tipoVia = tipoVia;
        this.nombreCalle = nombreCalle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoVia() {
        return tipoVia;
    }

    public void setTipoVia(String tipoVia) {
        this.tipoVia = tipoVia;
    }

    public String getNombreCalle() {
        return nombreCalle;
    }

    public void setNombreCalle(String nombreCalle) {
        this.nombreCalle = nombreCalle;
    }

    @Override
    public String toString() {
        return "Direccion{" +
                ", id='" + id + '\'' +
                ", tipoVia='" + tipoVia + '\'' +
                ", nombreCalle='" + nombreCalle + '\'' +
                '}';
    }
}
