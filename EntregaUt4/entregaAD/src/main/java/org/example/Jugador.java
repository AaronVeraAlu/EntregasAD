package org.example;

public class Jugador {
    private String dni;
    private String nombre;
    private int dorsal;

    public Jugador(String dni,String nombre, int dorsal) {
        this.dni = dni;
        this.nombre = nombre;
        this.dorsal = dorsal;
    }

    public Jugador() {
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", dorsal=" + dorsal +
                '}';
    }


}
