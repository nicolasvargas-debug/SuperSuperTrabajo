package poclibreria.Modelo;

public class Libreria {

    private int codigoLibreria;      // atri1: integer(10) - Llave primaria
    private String nombre;       // atri2: varchar(255)
    private double presupuesto;  // atri3: double(10)
    private char categoria;      // atri4: char(1)
    private String estado;       // estado: varchar(2) - Para el eliminado lógico

    public Libreria(int idLibreria, String nombre, double presupuesto, char categoria, String estado) {
        this.codigoLibreria = idLibreria;
        this.nombre = nombre;
        this.presupuesto = presupuesto;
        this.categoria = categoria;
        this.estado = estado;
    }

    public int getIdLibreria() {
        return codigoLibreria;
    }

    public void setIdLibreria(int idLibreria) {
        this.codigoLibreria = idLibreria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(double presupuesto) {
        this.presupuesto = presupuesto;
    }

    public char getCategoria() {
        return categoria;
    }

    public void setCategoria(char categoria) {
        this.categoria = categoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}