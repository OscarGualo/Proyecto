package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGUI {
    
    private JFrame frame;
    private JPanel panel;

    public MenuGUI() {
        // Crear la ventana
        frame = new JFrame("Sistema de Gestión de Alimentos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Diseño vertical

        // Crear botones para las opciones del menú
        JButton btnCargarInfo = new JButton("Cargar Información");
        JButton btnConsultarCriterios = new JButton("Consultar por Criterios");
        JButton btnRangoPrecios = new JButton("Rango de Precios");
        JButton btnConsultaStock = new JButton("Consulta de Stock");
        JButton btnIngresoProductos = new JButton("Ingreso Nuevos Productos");
        JButton btnActualizarProductos = new JButton("Actualización de Productos");
        JButton btnEliminarProductos = new JButton("Eliminar Productos");
        JButton btnVerInventario = new JButton("Ver Inventario");
        JButton btnAplicarDescuentos = new JButton("Aplicar Descuentos");

        // Agregar botones al panel
        panel.add(btnCargarInfo);
        panel.add(btnConsultarCriterios);
        panel.add(btnRangoPrecios);
        panel.add(btnConsultaStock);
        panel.add(btnIngresoProductos);
        panel.add(btnActualizarProductos);
        panel.add(btnEliminarProductos);
        panel.add(btnVerInventario);
        panel.add(btnAplicarDescuentos);

        // Mostrar panel en la ventana
        frame.add(panel);
        frame.setLocationRelativeTo(null);  // Centrar la ventana
        frame.setVisible(true);

        // Acciones de los botones
        btnCargarInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí llamamos a los métodos del main o a la lógica de negocio
                JOptionPane.showMessageDialog(frame, "Cargar Información");
            }
        });

        btnConsultarCriterios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes abrir un submenú de criterios
                JOptionPane.showMessageDialog(frame, "Consultar por Criterios");
            }
        });

        btnRangoPrecios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Consultar Rango de Precios");
            }
        });

        btnConsultaStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Consulta de Stock");
            }
        });

        btnIngresoProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Ingreso de Nuevos Productos");
            }
        });

        btnActualizarProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Actualización de Productos");
            }
        });

        btnEliminarProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Eliminar Productos");
            }
        });

        btnVerInventario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Ver Inventario");
            }
        });

        btnAplicarDescuentos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Aplicar Descuentos");
            }
        });
    }

    // Método para iniciar la interfaz gráfica
    public void mostrarInterfaz() {
        frame.setVisible(true);
    }
}
