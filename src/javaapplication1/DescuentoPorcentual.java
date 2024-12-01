/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author oscar
 */
public class DescuentoPorcentual implements Descuento {
    private double porcentajeDescuento;

    // Constructor que recibe el porcentaje de descuento
    public DescuentoPorcentual(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    // Implementación del cálculo del precio con descuento
    @Override
    public double calcularPrecioConDescuento(double precio) {
        return precio - (precio * (porcentajeDescuento / 100));
    }

    // Implementación de mostrarDetalles para mostrar el porcentaje de descuento
    @Override
    public void mostrarDetalles() {
        System.out.println("Descuento aplicado: " + porcentajeDescuento + "%");
    }
}
