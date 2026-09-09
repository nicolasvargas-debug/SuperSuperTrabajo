/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
        LLAVE_FORANEA_NO_EXISTE, // Nuevo estado para validar Archivo A
        ERROR
    }

    public static ResultadoAgregar adicionarLibro(Libro libro) {
        
        if (ServiciosLibreria.buscarLibreria(libro.getIdLibreria()) == null) {
            return ResultadoAgregar.LLAVE_FORANEA_NO_EXISTE;
        }

        try (RandomAccessFile file = new RandomAccessFile(RUTA_ARCHIVO, "rw")) {
            file.seek(0);

            while (file.getFilePointer() < file.length()) {
                int codigo = file.readInt();
                int idLibreria = file.readInt(); // Lectura de la llave foránea
                String nombre = file.readUTF().trim();
                file.readDouble();
                file.readBoolean();
                file.readUTF();

                if (codigo == libro.getCodigo()) {
                    return ResultadoAgregar.CODIGO_REPETIDO;
                }
                if (nombre.equalsIgnoreCase(libro.getNombre().trim())) {
                    return ResultadoAgregar.NOMBRE_REPETIDO;
                }
            }

            file.seek(file.length());
            file.writeInt(libro.getCodigo());
            file.writeInt(libro.getIdLibreria()); // Escritura de la llave foránea
            file.writeUTF(StringUtils.formatearCadena(libro.getNombre(), 25));
            file.writeDouble(libro.getPrecio());
            file.writeBoolean(libro.isDisponible());
            file.writeUTF(StringUtils.formatearCadena(libro.getEstado(), 5));

            return ResultadoAgregar.OK;
        } catch (Exception e) {
            System.out.println("Error al adicionar libro: " + e);
            return ResultadoAgregar.ERROR;
        }
    }

    public static Libro buscarLibro(int codigoBuscado) {
        try (RandomAccessFile file = new RandomAccessFile(RUTA_ARCHIVO, "rw")) {
            while (file.getFilePointer() < file.length()) {
                int codigo = file.readInt();
                int idLibreria = file.readInt();
                String nombre = file.readUTF().trim();
                double precio = file.readDouble();
                boolean disponible = file.readBoolean();
                String estado = file.readUTF().trim();

                if (codigoBuscado == codigo && estado.equals("ACTIV")) { 
                    return new Libro(codigo, idLibreria, nombre, precio, disponible, estado);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar libro: " + e);
        }
        return null;
    }

    public static boolean actualizarLibro(int codigoBuscado, double nuevoPrecio, boolean nuevaDisponibilidad) {
        try (RandomAccessFile file = new RandomAccessFile(RUTA_ARCHIVO, "rw")) {
            while (file.getFilePointer() < file.length()) {
                int codigo = file.readInt();
                file.readInt(); // idLibreria
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
                    file.writeUTF(StringUtils.formatearCadena("INACT", 5)); // Eliminado lógico
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar libro: " + e);
        }
        return false;
    }

    public static List<Libro> getLibros() {
        List<Libro> libros = new ArrayList<>(); 
        try (RandomAccessFile file = new RandomAccessFile(RUTA_ARCHIVO, "rw")) {
            while (file.getFilePointer() < file.length()) {
                int codigo = file.readInt();
                int idLibreria = file.readInt();
                String nombre = file.readUTF().trim();
                double precio = file.readDouble();
                boolean disponible = file.readBoolean();
                String estado = file.readUTF().trim();

                if (estado.equals("ACTIV")) { 
                    libros.add(new Libro(codigo, idLibreria, nombre, precio, disponible, estado));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al listar libros: " + e);
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
}