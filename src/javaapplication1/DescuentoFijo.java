/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author oscar
 */
public class DescuentoFijo implements Descuento {
    private double montoDescuento;

    // Constructor que recibe el monto del descuento fijo
    public DescuentoFijo(double montoDescuento) {
        this.montoDescuento = montoDescuento;
    }

    // Implementación del cálculo del precio con descuento
    @Override
    public double calcularPrecioConDescuento(double precio) {
        return precio - montoDescuento;
    }

    // Implementación de mostrarDetalles para mostrar el monto del descuento
    @Override
    public void mostrarDetalles() {
        System.out.println("Descuento aplicado: " + montoDescuento + " unidades monetarias");
    }
}
