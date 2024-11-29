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

    public DescuentoFijo(double montoDescuento) {
        this.montoDescuento = montoDescuento;
    }

    @Override
    public double calcularPrecioConDescuento(double precio) {
        return precio - montoDescuento;
    }

    @Override
    public void mostrarDetalles() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
