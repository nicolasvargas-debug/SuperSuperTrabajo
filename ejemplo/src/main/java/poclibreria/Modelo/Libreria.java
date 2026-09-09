package poclibreria.Modelo;

import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import pocLibro.Gui.VentanaAgregar;
import pocLibro.Gui.VentanaBuscar;
import pocLibro.Gui.VentanaListado;

/**
 * Ventana Principal de la Librería
 * @author delva
 */
public class Libreria extends JFrame {

    public Libreria() {
        // Configuraciones básicas de la ventana principal
        setTitle("Sistema de Gestión - Librería");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null); // Layout nulo para posicionamiento absoluto

        // Botón Agregar
        JButton btnAgregar = new JButton("Agregar Libro");
        btnAgregar.setBounds(100, 50, 200, 40);
        btnAgregar.addActionListener(e -> {
            VentanaAgregar vAgregar = new VentanaAgregar();
            vAgregar.setVisible(true);
        });
        getContentPane().add(btnAgregar);

        // Botón Buscar
        JButton btnBuscar = new JButton("Buscar Libro");
        btnBuscar.setBounds(100, 110, 200, 40);
        btnBuscar.addActionListener(e -> {
            VentanaBuscar vBuscar = new VentanaBuscar();
            vBuscar.setVisible(true);
        });
        getContentPane().add(btnBuscar);

        // Botón Listar
        JButton btnListar = new JButton("Listar Libros");
        btnListar.setBounds(100, 170, 200, 40);
        btnListar.addActionListener(e -> {
            VentanaListado vListado = new VentanaListado();
            vListado.setVisible(true);
        });
        getContentPane().add(btnListar);
    }

    public static void main(String[] args) {
        // Garantizar que la carpeta "data" existe para evitar errores con archivos
        File directorio = new File("data");
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        // Iniciar la interfaz gráfica
        SwingUtilities.invokeLater(() -> {
            new Libreria().setVisible(true);
        });
    }
}