package poclibreria.Servicios;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import poclibreria.Modelo.Libro;
import pucLiberiaUtils.StringUtils;

public class ServicioLibro {
    public static final String RUTA_ARCHIVO = "data//libro.txt";

    public enum ResultadoAgregar {
        OK,
        CODIGO_REPETIDO,
        NOMBRE_REPETIDO,
        LLAVE_FORANEA_NO_EXISTE,
        ERROR
    }

    public static ResultadoAgregar adicionarLibro(Libro libro) {

        try {
            RandomAccessFile file = new RandomAccessFile("data//libro.txt", "rw");

            file.seek(0);
            while (file.getFilePointer() < file.length()) {
                int codigo = file.readInt();
                file.readInt();
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
            file.writeInt(libro.getCodigoLibreria()); 
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
        int codigoLibreria;
        String nombre, estado;
        double precio;
        boolean disponible;
        Libro libro;
        try {
            RandomAccessFile file = new RandomAccessFile("data//libro.txt", "rw");
            while (file.getFilePointer() < file.length()) {
                codigo = file.readInt();
                codigoLibreria = file.readInt();
                nombre = file.readUTF().trim();
                precio = file.readDouble();
                disponible = file.readBoolean();
                estado = file.readUTF();
                
                if(pCodigo == codigo){
                    libro = new Libro(codigo, codigoLibreria, nombre, precio, disponible, estado);
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

    public static boolean actualizarLibro(int codigoBuscado, double nuevoPrecio, boolean nuevaDisponibilidad) {
        try (RandomAccessFile file = new RandomAccessFile(RUTA_ARCHIVO, "rw")) {
            while (file.getFilePointer() < file.length()) {
                int codigo = file.readInt();
                file.readInt(); // codigoLibreria
                file.readUTF(); // nombre
                
                long posAntesDeAtributos = file.getFilePointer();
                file.readDouble(); // precio
                file.readBoolean(); // disponible
                String estado = file.readUTF();

                if (codigo == codigoBuscado && estado.trim().equals("ACTIV")) {

                    file.seek(posAntesDeAtributos);
                    file.writeDouble(nuevoPrecio);
                    file.writeBoolean(nuevaDisponibilidad);
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar libro: " + e);
        }
        return false;
    }

    public static boolean eliminarLibro(int codigoBuscado) {
        try (RandomAccessFile file = new RandomAccessFile(RUTA_ARCHIVO, "rw")) {
            while (file.getFilePointer() < file.length()) {
                int codigo = file.readInt();
                file.readInt();
                file.readUTF();
                file.readDouble();
                file.readBoolean();
                
                long posAntesDeEstado = file.getFilePointer();
                String estado = file.readUTF().trim();

                if (codigo == codigoBuscado && estado.equals("ACTIV")) {
                    file.seek(posAntesDeEstado);
                    file.writeUTF(StringUtils.formatearCadena("INACT", 5));
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar libro: " + e);
        }
        return false;
    }

    public static List<Libro> getLibros() {
        List <Libro> libros = new ArrayList();
        int codigo;
        int codigoLibreria;
        String nombre, estado;
        double precio;
        boolean disponible;
        Libro libro;

        try {
            RandomAccessFile file = new RandomAccessFile("data//libro.txt", "rw");
            while (file.getFilePointer() < file.length()) {
                codigo = file.readInt();
                codigoLibreria = file.readInt();
                nombre = file.readUTF().trim();
                precio = file.readDouble();
                disponible = file.readBoolean();
                estado = file.readUTF();

                libro = new Libro(codigo, codigoLibreria, nombre, precio, disponible, estado);
                libros.add(libro);
            }
            file.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return libros;
    }

    public static double calcularSumatoriaPrecios() {
        double sumatoria = 0.0;
        try (RandomAccessFile file = new RandomAccessFile(RUTA_ARCHIVO, "rw")) {
            while (file.getFilePointer() < file.length()) {
                file.readInt();
                file.readInt();
                file.readUTF();
                double precio = file.readDouble();
                file.readBoolean();
                String estado = file.readUTF().trim();

                if (estado.equals("ACTIV")) {
                    sumatoria += precio;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al calcular sumatoria: " + e);
        }
        return sumatoria;
    }
    public static int contarRegistros(){
        try{
            Integer contador = 0; 
            RandomAccessFile file = new RandomAccessFile("data//libro.txt", "rw");
            while (file.getFilePointer() < file.length()){
                file.readInt();
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