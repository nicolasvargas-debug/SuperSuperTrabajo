/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poclibreria.Servicios;

/**
 *
 * @author delva
 */
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
    ERROR
}

public static ResultadoAgregar adicionarLibro(Libro libro) {

    try (RandomAccessFile file =
            new RandomAccessFile(RUTA_ARCHIVO, "rw")) {

        file.seek(0);

        while (file.getFilePointer() < file.length()) {

            int codigo = file.readInt();
            String nombre = file.readUTF().trim();

            file.readDouble();
            file.readBoolean();
            file.readUTF();

            if (codigo == libro.getCodigo()) {
                return ResultadoAgregar.CODIGO_REPETIDO;
            }

            if (nombre.equalsIgnoreCase(
                    libro.getNombre().trim())) {

                return ResultadoAgregar.NOMBRE_REPETIDO;
            }
        }

        file.seek(file.length());

        file.writeInt(libro.getCodigo());

        file.writeUTF(
                StringUtils.formatearCadena(
                        libro.getNombre(), 25
                )
        );

        file.writeDouble(libro.getPrecio());

        file.writeBoolean(libro.isDisponible());

        file.writeUTF(
                StringUtils.formatearCadena(
                        libro.getEstado(), 5
                )
        );

        return ResultadoAgregar.OK;

    } catch (Exception e) {

        System.out.println("Error al adicionar libro: " + e);

        return ResultadoAgregar.ERROR;
    }
}

public static Libro buscarLibro(int codigoBuscado) {

    try (RandomAccessFile file =
            new RandomAccessFile(RUTA_ARCHIVO, "rw")) {

        while (file.getFilePointer() < file.length()) {

            int codigo = file.readInt();
            String nombre = file.readUTF().trim();
            double precio = file.readDouble();
            boolean disponible = file.readBoolean();
            String estado = file.readUTF().trim();

            if (codigoBuscado == codigo) {

                return new Libro(
                        codigo,
                        nombre,
                        precio,
                        disponible,
                        estado
                );
            }
        }

    } catch (Exception e) {

        System.out.println("Error al buscar libro: " + e);
    }

    return null;
}

public static boolean aumentarValor() {

    try (RandomAccessFile file =
            new RandomAccessFile(RUTA_ARCHIVO, "rw")) {

        while (file.getFilePointer() < file.length()) {

            file.readInt();
            file.readUTF();

            double nuevoPrecio =
                    file.readDouble() * 1.10;

            file.seek(
                    file.getFilePointer() - Double.BYTES
            );

            file.writeDouble(nuevoPrecio);

            file.readBoolean();
            file.readUTF();
        }

        return true;

    } catch (Exception e) {

        System.out.println(
                "Error al aumentar el precio: " + e
        );

        return false;
    }
}

public static List<Libro> getLibros() {

    List<Libro> libros = new ArrayList<>();

    try (RandomAccessFile file =
            new RandomAccessFile(RUTA_ARCHIVO, "rw")) {

        while (file.getFilePointer() < file.length()) {

            int codigo = file.readInt();
            String nombre = file.readUTF().trim();
            double precio = file.readDouble();
            boolean disponible = file.readBoolean();
            String estado = file.readUTF().trim();

            Libro libro = new Libro(
                    codigo,
                    nombre,
                    precio,
                    disponible,
                    estado
            );

            libros.add(libro);
        }

    } catch (Exception e) {

        System.out.println("Error al listar libros: " + e);
    }
    return libros;
}

public static int contarRegistros() {

    int contador = 0;

    try (RandomAccessFile file =
            new RandomAccessFile(RUTA_ARCHIVO, "rw")) {

        while (file.getFilePointer() < file.length()) {

            file.readInt();
            file.readUTF();
            file.readDouble();
            file.readBoolean();
            file.readUTF();

            contador++;
        }

        return contador;

    } catch (Exception e) {

        System.out.println(
                "Error al contar los libros: " + e
        );

        return -1;
    }
}
}
