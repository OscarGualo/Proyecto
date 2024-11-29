package javaapplication1;

public abstract class ProductoBase implements Descuento{
    private String codigo;
    private String marca;
    private String presentacion;
    private double costo;
    private double precioVenta;
    private int stock;
    private String grupo;
    private String categoria;
    
        

    public ProductoBase(String codigo, String marca, String presentacion, double costo, double precioVenta, int stock, String grupo, String categoria) {
        this.codigo = codigo;
        this.marca = marca;
        this.presentacion = presentacion;
        this.costo = costo;
        this.precioVenta = precioVenta;
        this.stock = stock;
        this.grupo = grupo;
        this.categoria = categoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    
    public abstract String getProductoEspecifico();
    public void aplicarDescuento(Descuento descuento){
    this.precioVenta = descuento.calcularPrecioConDescuento(this.precioVenta);
    }
    @Override
    public String toString() {
        return "Código: " + codigo + ", Marca: " + marca + ", Presentación: " + presentacion +
               ", Costo: " + costo + ", Precio Venta: " + precioVenta + ", Stock: " + stock +
               ", Grupo: " + grupo + ", Categoría: " + categoria + ", Producto Específico: " + getProductoEspecifico();
    }
     @Override
    public double calcularPrecioConDescuento(double descuento) {
        return precioVenta - (precioVenta * descuento);
    }

}
