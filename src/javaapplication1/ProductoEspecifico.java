package javaapplication1;

public class ProductoEspecifico extends  ProductoBase  {
    private String productoEspecifico;

   public ProductoEspecifico(String codigo, String marca, String presentacion, double costo, double precioVenta, int stock, String grupo, String categoria, String productoEspecifico, double descuento) {
    // Llamar al constructor de la clase base (ProductoBase) para inicializar los valores
    super(codigo, marca, presentacion, costo, precioVenta, stock, grupo, categoria, descuento);
    this.productoEspecifico = productoEspecifico;
}


    @Override
    public String getProductoEspecifico() {
        return productoEspecifico;
    }

   public void setProductoEspecifico(String productoEspecifico) {
    if (productoEspecifico == null || productoEspecifico.trim().isEmpty()) {
        throw new IllegalArgumentException("El nombre del producto específico no puede estar vacío.");
    }
    this.productoEspecifico = productoEspecifico;
}


    
  @Override
   public void mostrarDetalles() {
    System.out.println("Detalles del Producto Específico:");
    System.out.println("Nombre: " + getProductoEspecifico());
    System.out.println(super.toString());
}
    @Override
    public void aplicarDescuento() {
        
        super.aplicarDescuento();  // Aplica el descuento definido en la clase base
    }

@Override
public String toString() {
    return super.toString();
}


}
