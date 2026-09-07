/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pocLibreria.Servicios;


import java.util.List;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import poclibreria.Modelo.Libro;
import pucLiberiaUtils.StringUtils;
import poclibreria.gui.VentanaEliminar;

/**
 *
 * @author nikol
 */
public class ServiciosLibreria {
    public enum ResultadoAgregar {
        OK, CODIGO_REPETIDO, NOMBRE_REPETIDO, ERROR
    }

    public static ResultadoAgregar adicionarLibro(Libro libro) {
        try {
            RandomAccessFile file = new RandomAccessFile("data//libro.txt", "rw");

            file.seek(0);
            while (file.getFilePointer() < file.length()) {
                int codigo = file.readInt();
                String nombre = file.readUTF().trim();
                file.readDouble();
                file.readBoolean();
                file.readUTF();

                if (codigo == libro.getCodigo()) {
                    file.close();
                    return ResultadoAgregar.CODIGO_REPETIDO;
                }
                if (nombre.equalsIgnoreCase(libro.getNombre().trim())) {
                    file.close();
                    return ResultadoAgregar.NOMBRE_REPETIDO;
                }
            }
            file.seek(file.length());
            file.writeInt(libro.getCodigo());
            file.writeUTF(StringUtils.formatearCadena(libro.getNombre(), 25));
            file.writeDouble(libro.getPrecio());
            file.writeBoolean(libro.isDisponible());
            file.writeUTF(StringUtils.formatearCadena(libro.getEstado(), 5));
            file.close();
            return ResultadoAgregar.OK;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return ResultadoAgregar.ERROR;
    }
    
    public static Libro buscarLibro(int pCodigo){
        int codigo;
        String nombre, estado;
        double precio;
        boolean disponible;
        Libro libro;
        try {
            RandomAccessFile file = new RandomAccessFile("data//libro.txt", "rw");
            while (file.getFilePointer() < file.length()) {
                codigo = file.readInt();
                nombre = file.readUTF().trim();
                precio = file.readDouble();
                disponible = file.readBoolean();
                estado = file.readUTF();
                
                if(pCodigo == codigo){
                    libro = new Libro(codigo, nombre, precio, disponible, estado);
                    file.close();
                    
                    return libro;
                }
            }
            file.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        return null;
        
    }
    
    public static void aumentarValor(){
        try{
            RandomAccessFile file = new RandomAccessFile("data//libro.txt", "rw");
            while(file.getFilePointer() < file.length()){
                file.readInt();
                file.readUTF();
                double valorNuevo = file.readDouble() * 1.1 ;
                file.seek(file.getFilePointer() -8);
                file.writeDouble(valorNuevo);
                file.readBoolean();
                file.readUTF();
                
            }
            
        }
        catch (Exception e){
            System.out.println("Error: " + e);
        }
        
    }

    
    public static List<Libro> getLibros(){
        List <Libro> libros = new ArrayList();
        int codigo;
        String nombre, estado;
        double precio;
        boolean disponible;
        Libro libro;

        try {
            RandomAccessFile file = new RandomAccessFile("data//libro.txt", "rw");
            while (file.getFilePointer() < file.length()) {
                codigo = file.readInt();
                nombre = file.readUTF().trim();
                precio = file.readDouble();
                disponible = file.readBoolean();
                estado = file.readUTF();

                libro = new Libro(codigo, nombre, precio, disponible, estado);
                libros.add(libro);
            }
            file.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return libros;
    }

        public static int contarRegistros(){
        try{
            Integer contador = 0; 
            RandomAccessFile file = new RandomAccessFile("data//libro.txt", "rw");
            while (file.getFilePointer() < file.length()){
                file.readInt();
                file.readUTF();
                file.readDouble();
                file.readBoolean();
                file.readUTF();
                contador ++;
                
            }
            return contador;
        }
        catch (Exception e){
            return -1;
        }
    } 
}
