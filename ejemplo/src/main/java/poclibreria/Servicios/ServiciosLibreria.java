package poclibreria.Servicios;

import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import poclibreria.Modelo.Libreria;
import pucLiberiaUtils.StringUtils;

public class ServiciosLibreria {

    public enum ResultadoAgregar {
        OK, CODIGO_REPETIDO, NOMBRE_REPETIDO, ERROR
    }

    public static ResultadoAgregar adicionarLibreria(Libreria libreria) {
        try {
            RandomAccessFile file = new RandomAccessFile("data//libreria.txt", "rw");

            file.seek(0);
            while (file.getFilePointer() < file.length()) {
                int codigoLibreria = file.readInt();
                String nombre = file.readUTF().trim();
                file.readDouble();
                file.readUTF();
                file.readBoolean();

                if (codigoLibreria == libreria.getCodigoLibreria()) {
                    file.close();
                    return ResultadoAgregar.CODIGO_REPETIDO;
                }
                if (nombre.equalsIgnoreCase(libreria.getNombre().trim())) {
                    file.close();
                    return ResultadoAgregar.NOMBRE_REPETIDO;
                }
            }
            
            file.seek(file.length());
            file.writeInt(libreria.getCodigoLibreria());
            file.writeUTF(StringUtils.formatearCadena(libreria.getNombre(), 25));
            file.writeDouble(libreria.getPresupuesto());
            file.writeUTF(StringUtils.formatearCadena(libreria.getCategoria(), 5)); 
            file.writeBoolean(libreria.isEstado());
            file.close();
            
            return ResultadoAgregar.OK;
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return ResultadoAgregar.ERROR;
    }

    public static Libreria buscarLibreria(int pcodigoLibreria) {
        int codigoLibreria;
        String nombre, categoria;
        double presupuesto;
        boolean estado;
        Libreria libreria;
        
        try {
            RandomAccessFile file = new RandomAccessFile("data//libreria.txt", "rw");
            while (file.getFilePointer() < file.length()) {
                codigoLibreria = file.readInt();
                nombre = file.readUTF().trim();
                presupuesto = file.readDouble();
                categoria = file.readUTF().trim();
                estado = file.readBoolean();

                if (pcodigoLibreria == codigoLibreria) {
                    libreria = new Libreria(codigoLibreria, nombre, presupuesto, categoria, estado);
                    file.close();
                    return libreria;
                }
            }
            file.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
        return null;
    }

    public static boolean actualizarLibreria(Libreria libreriaModificada) {
        try {
            RandomAccessFile file = new RandomAccessFile("data//libreria.txt", "rw");
            while (file.getFilePointer() < file.length()) {
                int codigoLibreria = file.readInt();
                
                if (codigoLibreria == libreriaModificada.getCodigoLibreria()) {
                    file.writeUTF(StringUtils.formatearCadena(libreriaModificada.getNombre(), 25));
                    file.writeDouble(libreriaModificada.getPresupuesto());
                    file.writeUTF(StringUtils.formatearCadena(libreriaModificada.getCategoria(), 5));
                    file.writeBoolean(libreriaModificada.isEstado());
                    
                    file.close();
                    return true;
                } else {
                    file.readUTF();
                    file.readDouble();
                    file.readUTF();
                    file.readBoolean();
                }
            }
            file.close();
        } catch (Exception e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
        return false;
    }

    public static List<Libreria> getLibrerias() {
        List<Libreria> librerias = new ArrayList<>();
        int codigoLibreria;
        String nombre, categoria;
        double presupuesto;
        boolean estado;
        Libreria libreria;

        try {
            RandomAccessFile file = new RandomAccessFile("data//libreria.txt", "rw");
            while (file.getFilePointer() < file.length()) {
                codigoLibreria = file.readInt();
                nombre = file.readUTF().trim();
                presupuesto = file.readDouble();
                categoria = file.readUTF().trim();
                estado = file.readBoolean();

                libreria = new Libreria(codigoLibreria, nombre, presupuesto, categoria, estado);
                librerias.add(libreria);
            }
            file.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        return librerias;
    }

    public static int contarRegistros() {
        try {
            int contador = 0;
            RandomAccessFile file = new RandomAccessFile("data//libreria.txt", "rw");
            while (file.getFilePointer() < file.length()) {
                file.readInt();
                file.readUTF();
                file.readDouble();
                file.readUTF();
                file.readBoolean();
                contador++;
            }
            file.close();
            return contador;
        } catch (Exception e) {
            return -1;
        }
    }
}