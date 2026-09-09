/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poclibreria.Modelo;
/**
 *
 * @author delva
 */
import java.util.ArrayList;
import java.util.List;

public class Libreria {
    private int idLibreria;
    private String nombre;
    private String direccion;
    private List<Libro> inventario;

    public Libreria(int idLibreria, String nombre, String direccion) {
        this.idLibreria = idLibreria;
        this.nombre = nombre;
        this.direccion = direccion;
        this.inventario = new ArrayList<>();
    }

    // Métodos Getters y Setters
    public int getIdLibreria() {
        return idLibreria;
    }

    public void setIdLibreria(int idLibreria) {
        this.idLibreria = idLibreria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Libro> getInventario() {
        return inventario;
    }

    public void setInventario(List<Libro> inventario) {
        this.inventario = inventario;
    }

    // Métodos de negocio de la librería
    public void agregarLibro(Libro libro) {
        this.inventario.add(libro);
    }
}