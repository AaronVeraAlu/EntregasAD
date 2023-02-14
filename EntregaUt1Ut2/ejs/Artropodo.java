package ejs;

public class Artropodo {

    private int codigo;
    private String nombre;
    private float peso;

    public Artropodo(int codigo,String nombre, float peso) {
        
        this.codigo = codigo;
        this.nombre = nombre;
        this.peso = peso;

    }

    public int getCodigo () { return codigo; }
    public void setCodigo (int codigo) { this.codigo = codigo; }
    public String getNombre () { return nombre; }
    public void setNombre (String nombre) { this.nombre = nombre; }
    public float getPrecio () { return peso; }
    public void setPrecio (float peso) { this.peso = peso; }

    }