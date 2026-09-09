/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poclibreria.Modelo;

public class Libro {
<<<<<<< HEAD
    
    private int codigoLibro;
=======
    private int codigo;
>>>>>>> 54bbe114d8fc81fc3d631e5643cfb360d0f13972
    private String nombre;
    private double precio;
    private boolean disponible;
    private String estado;
<<<<<<< HEAD
    private int codigoLibreria;
    public static final int TAM_REGISTRO = 51;
    public Libro( int codigoLibro, String nombre, double precio, boolean disponible, String estado,int codigoLibreria){
        this.codigoLibro = codigoLibro;
=======

    public Libro(int codigo, String nombre, double precio, boolean disponible, String estado) {
        this.codigo = codigo;
>>>>>>> 54bbe114d8fc81fc3d631e5643cfb360d0f13972
        this.nombre = nombre;
        this.precio = precio;
        this.disponible = disponible;
        this.estado = estado;
<<<<<<< HEAD
        this.codigoLibreria = codigoLibreria;
        

=======
>>>>>>> 54bbe114d8fc81fc3d631e5643cfb360d0f13972
    }

    public int getCodigoLibro() {
        return codigoLibro;
    }

    public void setCodigoLibro(int codigoLibro) {
        this.codigoLibro = codigoLibro;
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
<<<<<<< HEAD
    public int getCodigoLibreria(){
        return codigoLibreria;
    }
    public void setCodigoLibreria(int codigoLibreria){
        this.codigoLibreria = codigoLibreria;
    }
}
=======
}
>>>>>>> 54bbe114d8fc81fc3d631e5643cfb360d0f13972
