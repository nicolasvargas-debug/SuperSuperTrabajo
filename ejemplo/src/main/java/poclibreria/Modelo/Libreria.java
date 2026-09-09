/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poclibreria.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Libreria {
    private int idLibreria;
    private String nombre;
    private String direccion;
    
    // La lista de tipo Inventario ahora se llama "libreria"
    private List<Libreria> libreria;

    public Libreria(int idLibreria, String nombre, String direccion) {
        this.idLibreria = idLibreria;
        this.nombre = nombre;
        this.direccion = direccion;
        // Inicializamos la nueva lista "libreria"
        this.libreria = new ArrayList<>();
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

    // Getter y Setter adaptados al nuevo nombre "libreria"
    public List<Libreria> getLibreria() {
        return libreria;
    }

    public void setLibreria(List<Libreria> libreria) {
        this.libreria = libreria;
    }

    // Método de negocio que agrega a la lista "libreria"
    public void agregarInventario(Libreria item) {
        this.libreria.add(item);
    }
} // <-- Esta es la llave de cierre de la clase que te faltaba