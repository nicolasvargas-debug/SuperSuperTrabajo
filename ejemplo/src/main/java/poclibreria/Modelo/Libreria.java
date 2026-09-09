/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poclibreria.Modelo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class Libreria {
     private int codigoLibreria;
    private String nombre;
    private String direccion;
    private List<Libreria> libreria;

    public Libreria(int codigoLibreria, String nombre, String direccion) {
        this.codigoLibreria = codigoLibreria;
        this.nombre = nombre;
        this.direccion = direccion;
        this.libreria = new ArrayList<>();
    }

    public int getCodigoLibreria() {
        return codigoLibreria;
    }

    public void setCodigoLibreria(int codigoLibreria) {
        this.codigoLibreria = codigoLibreria;
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

    public List<Libreria> getLibreria() {
        return libreria;
    }

    public void setLibreria(List<Libreria> libreria) {
        this.libreria = libreria;
    }

    public void agregarInventario(Libreria item) {
        this.libreria.add(item);
    }
}

