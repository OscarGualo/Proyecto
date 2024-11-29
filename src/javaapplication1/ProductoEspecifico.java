package javaapplication1;

public class ProductoEspecifico extends  ProductoBase  {
    private String productoEspecifico;

    public ProductoEspecifico(String codigo, String marca, String presentacion, double costo, double precioVenta, int stock, String grupo, String categoria, String productoEspecifico) {
        super(codigo, marca, presentacion, costo, precioVenta, stock, grupo, categoria);
        this.productoEspecifico = productoEspecifico;
    }

    @Override
    public String getProductoEspecifico() {
        return productoEspecifico;
    }

    public void setProductoEspecifico(String productoEspecifico) {
        this.productoEspecifico = productoEspecifico;
    }

    
   @Override
public void mostrarDetalles() {
    System.out.println("Detalles del producto específico: " + getProductoEspecifico());
    System.out.println(this);  // Muestra el detalle del producto base
}

}
