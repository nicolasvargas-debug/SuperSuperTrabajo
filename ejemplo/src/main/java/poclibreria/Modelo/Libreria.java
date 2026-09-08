/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poclibreria.Modelo;

import poclibreria.gui.VentanaAgregar;
import poclibreria.gui.VentanaBuscar;
import poclibreria.gui.VentanaListado;
import java.io.File;

public class Libreria extends javax.swing.JFrame {

    public Libreria() {
        // Configuraciones básicas de la ventana principal
        setTitle("Sistema de Gestión - Librería");
        setSize(400, 300);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null); // Layout nulo para posicionamiento simple

        // Botón Agregar
        javax.swing.JButton btnAgregar = new javax.swing.JButton("Agregar Libro");
        btnAgregar.setBounds(100, 50, 200, 40);
        btnAgregar.addActionListener(e -> {
            VentanaAgregar vAgregar = new VentanaAgregar();
            vAgregar.setVisible(true);
        });
        getContentPane().add(btnAgregar);

        // Botón Buscar
        javax.swing.JButton btnBuscar = new javax.swing.JButton("Buscar Libro");
        btnBuscar.setBounds(100, 110, 200, 40);
        btnBuscar.addActionListener(e -> {
            VentanaBuscar vBuscar = new VentanaBuscar();
            vBuscar.setVisible(true);
        });
        getContentPane().add(btnBuscar);

        // Botón Listar
        javax.swing.JButton btnListar = new javax.swing.JButton("Listar Libros");
        btnListar.setBounds(100, 170, 200, 40);
        btnListar.addActionListener(e -> {
            VentanaListado vListado = new VentanaListado();
            vListado.setVisible(true);
        });
        getContentPane().add(btnListar);
    }

    public static void main(String[] args) {
        // Garantizar que la carpeta "data" existe para evitar errores con RandomAccessFile
        File directorio = new File("data");
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        // Iniciar la interfaz gráfica
        java.awt.EventQueue.invokeLater(() -> {
            new Libreria().setVisible(true);
        });
    }
}