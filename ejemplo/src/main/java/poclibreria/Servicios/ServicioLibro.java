package pocLibreria.Servicios;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import poclibreria.Modelo.Libro;
import pucLiberiaUtils.StringUtils;

public class ServicioLibro {
    
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
    
    // MÉTODO NUEVO: Conecta con el botón actualizar de la interfaz gráfica
    public static boolean actualizarLibro(Libro libroModificado) {
        try {
            RandomAccessFile file = new RandomAccessFile("data//libro.txt", "rw");
            while (file.getFilePointer() < file.length()) {
                int codigo = file.readInt(); // Lee el código (avanza 4 bytes)
                
                if (codigo == libroModificado.getCodigo()) {
                    // El puntero está justo después del código. Sobrescribimos el resto de atributos.
                    // Al usar StringUtils.formatearCadena se garantiza que los tamaños en bytes no se descuadren.
                    file.writeUTF(StringUtils.formatearCadena(libroModificado.getNombre(), 25));
                    file.writeDouble(libroModificado.getPrecio());
                    file.writeBoolean(libroModificado.isDisponible());
                    file.writeUTF(StringUtils.formatearCadena(libroModificado.getEstado(), 5));
                    
                    file.close();
                    return true; // Actualización exitosa
                } else {
                    // Si no es el libro, pasamos al siguiente registro leyendo el resto de variables
                    file.readUTF();
                    file.readDouble();
                    file.readBoolean();
                    file.readUTF();
                }
            }
            file.close();
        } catch (Exception e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
        return false; // Libro no encontrado o error
    }
    
    public static void aumentarValor(){
        try{
            RandomAccessFile file = new RandomAccessFile("data//libro.txt", "rw");
            while(file.getFilePointer() < file.length()){
                file.readInt();
                file.readUTF();
                double valorNuevo = file.readDouble() * 1.1 ;
                file.seek(file.getFilePointer() - 8); // Se retrocede los 8 bytes del double para sobrescribir
                file.writeDouble(valorNuevo);
                file.readBoolean();
                file.readUTF();
            }
            file.close(); // Siempre cerrar el archivo para liberar recursos
        }
        catch (Exception e){
            System.out.println("Error: " + e);
        }
    }

    public static List<Libro> getLibros(){
        List<Libro> libros = new ArrayList<>(); // Permitido según las restricciones del proyecto
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
            int contador = 0; 
            RandomAccessFile file = new RandomAccessFile("data//libro.txt", "rw");
            while (file.getFilePointer() < file.length()){
                file.readInt();
                file.readUTF();
                file.readDouble();
                file.readBoolean();
                file.readUTF();
                contador ++;
            }
            file.close();
            return contador;
        }
        catch (Exception e){
            return -1;
        }
    } 
}