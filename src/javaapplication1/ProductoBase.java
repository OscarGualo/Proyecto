package javaapplication1;

public abstract class ProductoBase implements Descuento {
    private String codigo;
    private String marca;
    private String presentacion;
    private double costo;
    private double precioVenta;
    private int stock;
    private String grupo;
    private String categoria;
    private double descuento;

    public ProductoBase(String codigo, String marca, String presentacion, double costo, double precioVenta, int stock,
            String grupo, String categoria, double descuento) {
        this.codigo = codigo;
        this.marca = marca;
        this.presentacion = presentacion;
        this.costo = costo;
        this.precioVenta = precioVenta;
        this.stock = stock;
        this.grupo = grupo;
        this.categoria = categoria;
        this.descuento = descuento;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
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

    public void aplicarDescuento() {
        double precioConDescuento = this.precioVenta - (this.precioVenta * (this.descuento / 100));
        this.precioVenta = precioConDescuento; // Actualizamos el precio
    }

    @Override
    public String toString() {
        // Ajustar los anchos de las columnas para no exceder el ancho del terminal
        int anchoCodigo = 10;
        int anchoMarca = 18;
        int anchoPresentacion = 24;
        int anchoCosto = 10;
        int anchoPrecioVenta = 9;
        int anchoStock = 8;
        int anchoGrupo = 13;
        int anchoCategoria = 23;
        int anchoProductoEspecifico = 28;
        int anchoDescuento = 10;

        // Crear el formato de la tabla
        String formato = "%-" + anchoCodigo + "s%-" + anchoMarca + "s%-" + anchoPresentacion + "s%-" +
                anchoCosto + "s%-" + anchoPrecioVenta + "s%-" + anchoStock + "s%-" +
                anchoGrupo + "s%-" + anchoCategoria + "s%-" + anchoProductoEspecifico + "s%-" +
                anchoDescuento + "s";

        // Crear la cadena formateada para los encabezados
        String result = String.format(formato,
                "Código", "Marca", "Presentación", "Costo", "Precio", "Stock", "Grupo", "Categoría",
                "Producto Específico", "Descuento");

        // Crear la cadena formateada para los valores
        if (grupo.equals("Artículos de aseo")) {
            grupo = "Aseo";
        }

        if (grupo.equals("Productos para mascotas")) {
            grupo = "Mascotas";
        }

        result = String.format(formato,
                codigo, marca, presentacion, costo + "$", precioVenta, stock, grupo, categoria, getProductoEspecifico(),
                descuento + " %");

        return result;
    }

    @Override
    public double calcularPrecioConDescuento(double descuento) {
        return precioVenta - (precioVenta * descuento);
    }

}
