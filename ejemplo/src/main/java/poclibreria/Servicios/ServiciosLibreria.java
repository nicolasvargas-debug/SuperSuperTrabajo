/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poclibreria.Servicios;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import poclibreria.Modelo.Libreria;
import pucLiberiaUtils.StringUtils;

public class ServiciosLibreria {
    public static final String RUTA_ARCHIVO = "data//libreria.txt";

    public enum ResultadoAgregar {
        OK,
        CODIGO_REPETIDO,
        NOMBRE_REPETIDO,
        ERROR
    }

    public static ResultadoAgregar adicionarLibreria(Libreria libreria) {
        try (RandomAccessFile file = new RandomAccessFile(RUTA_ARCHIVO, "rw")) {
            file.seek(0);

            while (file.getFilePointer() < file.length()) {
                int idLibreria = file.readInt();
                String nombre = file.readUTF().trim();
                file.readDouble(); // presupuesto
                file.readChar();   // categoria
                file.readUTF();    // estado

                if (idLibreria == libreria.getIdLibreria()) {
                    return ResultadoAgregar.ID_REPETIDO;
                }
                if (nombre.equalsIgnoreCase(libreria.getNombre().trim())) {
                    return ResultadoAgregar.NOMBRE_REPETIDO;
                }
            }

            file.seek(file.length());
            file.writeInt(libreria.getIdLibreria());
            file.writeUTF(StringUtils.formatearCadena(libreria.getNombre(), 25));
            file.writeDouble(libreria.getPresupuesto());
            file.writeChar(libreria.getCategoria());
            file.writeUTF(StringUtils.formatearCadena(libreria.getEstado(), 5));

            return ResultadoAgregar.OK;
        } catch (Exception e) {
            System.out.println("Error al adicionar libreria: " + e);
            return ResultadoAgregar.ERROR;
        }
    }

    public static Libreria buscarLibreria(int idLibreriaBuscado) {
        try (RandomAccessFile file = new RandomAccessFile(RUTA_ARCHIVO, "rw")) {
            while (file.getFilePointer() < file.length()) {
                int idLibreria = file.readInt();
                String nombre = file.readUTF().trim();
                double presupuesto = file.readDouble();
                char categoria = file.readChar();
                String estado = file.readUTF().trim();

                if (idLibreria == idLibreriaBuscado && estado.equals("ACTIV")) {
                    return new Libreria(idLibreria, nombre, presupuesto, categoria, estado);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar libreria: " + e);
        }
        return null;
    }

    public static boolean actualizarLibreria(int idLibreriaBuscado, double nuevoPresupuesto, char nuevaCategoria) {
        try (RandomAccessFile file = new RandomAccessFile(RUTA_ARCHIVO, "rw")) {
            while (file.getFilePointer() < file.length()) {
                int idLibreria = file.readInt();
                file.readUTF(); // nombre
                
                long posAntesAtributos = file.getFilePointer(); // Marcamos la posición exacta antes del double y el char
                file.readDouble(); // presupuesto
                file.readChar();   // categoria
                String estado = file.readUTF().trim();

                if (idLibreria == idLibreriaBuscado && estado.equals("ACTIV")) {
                    // Nos devolvemos a la posición para sobreescribir los dos atributos[cite: 1]
                    file.seek(posAntesAtributos);
                    file.writeDouble(nuevoPresupuesto);
                    file.writeChar(nuevaCategoria);
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar libreria: " + e);
        }
        return false;
    }

    // Operación que permite eliminar un solo objeto mediante eliminado lógico (cambio de estado)[cite: 1]
    public static boolean eliminarLibreria(int idLibreriaBuscado) {
        try (RandomAccessFile file = new RandomAccessFile(RUTA_ARCHIVO, "rw")) {
            while (file.getFilePointer() < file.length()) {
                int idLibreria = file.readInt();
                file.readUTF();
                file.readDouble();
                file.readChar();
                
                long posAntesEstado = file.getFilePointer();
                String estado = file.readUTF().trim();

                if (idLibreria == idLibreriaBuscado && estado.equals("ACTIV")) {
                    file.seek(posAntesEstado);
                    file.writeUTF(StringUtils.formatearCadena("INACT", 5)); // Eliminado lógico[cite: 1]
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al eliminar libreria: " + e);
        }
        return false;
    }

    public static List<Libreria> getLibrerias() {
        List<Libreria> librerias = new ArrayList<>();
        try (RandomAccessFile file = new RandomAccessFile(RUTA_ARCHIVO, "rw")) {
            while (file.getFilePointer() < file.length()) {
                int idLibreria = file.readInt();
                String nombre = file.readUTF().trim();
                double presupuesto = file.readDouble();
                char categoria = file.readChar();
                String estado = file.readUTF().trim();

                if (estado.equals("ACTIV")) {
                    librerias.add(new Libreria(idLibreria, nombre, presupuesto, categoria, estado));
                }
            }
        } catch (Exception e) {
            System.out.println("Error al listar librerias: " + e);
        }
        return librerias;
    }

    public static double calcularSumatoriaPresupuestos() {
        double sumatoria = 0.0;
        try (RandomAccessFile file = new RandomAccessFile(RUTA_ARCHIVO, "rw")) {
            while (file.getFilePointer() < file.length()) {
                file.readInt();
                file.readUTF();
                double presupuesto = file.readDouble();
                file.readChar();
                String estado = file.readUTF().trim();

                if (estado.equals("ACTIV")) {
                    sumatoria += presupuesto;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al calcular la sumatoria: " + e);
        }
        return sumatoria;
    }
}