package poclibreria.Modelo;

public class Libro {
    private int codigo;
    private int codigoLibreria;
    private String nombre;
    private double precio;
    private boolean disponible;
    private String estado;

    public Libro(int codigo, String nombre, double precio, boolean disponible, String estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.disponible = disponible;
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}