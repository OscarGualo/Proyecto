package javaapplication1;

/**
 * Representa una empresa con un nombre y un sistema de gestión de productos
 * asociado.
 * 
 * @author Cristofer Diaz
 * @version 1.0.0
 */
public class Empresa {

    // Atributos
    private String nombreEmpresa; // Nombre de la empresa
    private SistemaGestionProductos sistemaGestionProductos; // Sistema de gestión de productos

    // Constructor
    /**
     * Constructor para inicializar el nombre de la empresa y establecer el sistema de gestión de productos.
     * @param nombreEmpresa           El nombre de la empresa.
     * @param sistemaGestionProductos El sistema de gestión de productos asociado a la empresa.
     */
    public Empresa(String nombreEmpresa, SistemaGestionProductos sistemaGestionProductos) {
        this.nombreEmpresa = nombreEmpresa;
        this.sistemaGestionProductos = sistemaGestionProductos; 
    }

    // Getters y Setters
    /**
     * Obtiene el nombre de la empresa.
     * 
     * @return El nombre de la empresa.
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * Establece el nombre de la empresa.
     * 
     * @param nombreEmpresa El nuevo nombre de la empresa.
     */
    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    /**
     * Obtiene el sistema de gestión de productos asociado a la empresa.
     * 
     * @return El sistema de gestión de productos.
     */
    public SistemaGestionProductos getSistemaGestionProductos() {
        return sistemaGestionProductos;
    }

    /**
     * Establece el sistema de gestión de productos para la empresa.
     * 
     * @param sistemaGestionProductos El nuevo sistema de gestión de productos.
     */
    public void setSistemaGestionProductos(SistemaGestionProductos sistemaGestionProductos) {
        this.sistemaGestionProductos = sistemaGestionProductos;
    }
}
