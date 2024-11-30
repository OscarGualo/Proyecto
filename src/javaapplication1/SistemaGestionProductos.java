package javaapplication1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SistemaGestionProductos {
    private List<ProductoBase> productos;

    public SistemaGestionProductos() {
        this.productos = new ArrayList<>();
    }

    // Cargar datos desde un archivo CSV
 public void cargarDesdeCSV(String rutaArchivo) {
    try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
        String linea;
        boolean primeraLinea = true; // Para ignorar la primera línea (encabezados)

        while ((linea = br.readLine()) != null) {
            if (primeraLinea) {
                primeraLinea = false; // Saltar la primera línea
                continue;
            }

            // Dividir la línea por comas
            String[] datos = linea.split(",");
            if (datos.length < 10) continue; // Verificar que haya suficientes columnas

            // Limpiar datos y asignar variables
            String codigo = datos[0].trim();
            String marca = datos[1].trim();
            String presentacion = datos[2].trim();
            
            // Validar valores numéricos
            double costo = datos[3].trim().matches("\\d+(\\.\\d+)?") ? Double.parseDouble(datos[3].trim()) : 0.0;
            double precioVenta = datos[4].trim().matches("\\d+(\\.\\d+)?") ? Double.parseDouble(datos[4].trim()) : 0.0;
            int stock = datos[5].trim().matches("\\d+") ? Integer.parseInt(datos[5].trim()) : 0;

            String grupo = datos[6].trim();
            String categoria = datos[7].trim();
            String productoEspecifico = datos[8].trim();

            // Leer el descuento (última columna en el CSV)
            double descuento = datos[9].trim().matches("\\d+(\\.\\d+)?") ? Double.parseDouble(datos[9].trim()) : 0.0;

            // Crear y añadir el producto
            ProductoBase producto = new ProductoEspecifico(codigo, marca, presentacion, costo, precioVenta, stock, grupo, categoria, productoEspecifico, descuento);
            productos.add(producto);
        }

        // Mensaje de depuración: Verifica que los productos se han cargado correctamente
        System.out.println("Productos cargados: " + productos.size());
    } catch (Exception e) {
        e.printStackTrace();
    }
}

/**
 Va a consultar por grupo
 * recibe de paremtro el grupo ingreasdo por el usuarip
 * Programacion Funcionala
  
  */
    public List<ProductoBase> consultarPorGrupo(String grupo) {
    return productos.stream()
            .filter(p -> p.getGrupo().equalsIgnoreCase(grupo))
            .collect(Collectors.toList());
}




public List<ProductoBase> consultarPorCategoria(String categoria) {
    List<ProductoBase> productosFiltrados = new ArrayList<>();
    for (ProductoBase producto : productos) {
        if (producto.getCategoria().equalsIgnoreCase(categoria)) {
            productosFiltrados.add(producto);
        }
    }
    return productosFiltrados;
}
public List<ProductoBase> consultarPorProductoEspecifico(String productoEspecifico) {
    List<ProductoBase> productosFiltrados = new ArrayList<>();
    for (ProductoBase producto : productos) {
        if (producto.getProductoEspecifico().equalsIgnoreCase(productoEspecifico.trim())) {
            productosFiltrados.add(producto);
        }
    }
    return productosFiltrados;
}

public List<ProductoBase> consultarPorMarca(String marca) {
    List<ProductoBase> productosFiltrados = new ArrayList<>();
    for (ProductoBase producto : productos) {
        if (producto.getMarca().equalsIgnoreCase(marca)) {
            productosFiltrados.add(producto);
        }
    }
    return productosFiltrados;
}

public void mostrarResultados(List<ProductoBase> productosFiltrados) {
    if (productosFiltrados.isEmpty()) {
        System.out.println("No se encontraron productos que coincidan con los criterios.");
    } else {
        for (ProductoBase producto : productosFiltrados) {
            producto.mostrarDetalles();
        }
    }
}
public List<ProductoBase> consultarPorCodigo(String codigo) {
    List<ProductoBase> productosFiltrados = new ArrayList<>();
    for (ProductoBase producto : productos) {
        if (producto.getCodigo().equalsIgnoreCase(codigo.trim())) {
            productosFiltrados.add(producto);
        }
    }
    return productosFiltrados;
}
public List<ProductoBase> consultarPorRangoDePrecio(double precioMinimo, double precioMaximo) {
    List<ProductoBase> productosFiltrados = new ArrayList<>();
    for (ProductoBase producto : productos) {
        double precio = producto.getPrecioVenta();
        if (precio >= precioMinimo && precio <= precioMaximo) {
            productosFiltrados.add(producto);
        }
    }
    return productosFiltrados;
}
public void consultarStock() {
    if (productos.isEmpty()) {
        System.out.println("No hay productos disponibles en el sistema.");
    } else {
        System.out.println("Consulta de stock de los productos:");
        for (ProductoBase producto : productos) {
            System.out.println("Producto: " + producto.getCodigo() + " - " + producto.getMarca() + " - Stock: " + producto.getStock());
        }
    }
}


public void ingresarNuevoProducto() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Ingrese los detalles del nuevo producto:");

    System.out.print("Código: ");
    String codigo = scanner.nextLine();

    System.out.print("Marca: ");
    String marca = scanner.nextLine();

    System.out.print("Presentación: ");
    String presentacion = scanner.nextLine();

    System.out.print("Costo: ");
    double costo = Double.parseDouble(scanner.nextLine());

    System.out.print("Precio de venta: ");
    double precioVenta = Double.parseDouble(scanner.nextLine());

    System.out.print("Stock: ");
    int stock = Integer.parseInt(scanner.nextLine());

    System.out.print("Grupo: ");
    String grupo = scanner.nextLine();

    System.out.print("Categoría: ");
    String categoria = scanner.nextLine();

    System.out.print("Producto específico: ");
    String productoEspecifico = scanner.nextLine();
    System.out.println("Descuento");
    double descuento = Double.parseDouble(scanner.nextLine());
    // Crear el nuevo producto
    ProductoBase nuevoProducto = new ProductoEspecifico(codigo, marca, presentacion, costo, precioVenta, stock, grupo, categoria, productoEspecifico, descuento);

    // Agregar el producto a la lista
    productos.add(nuevoProducto);
    System.out.println("Producto agregado correctamente.");
}



public void actualizarProducto() {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Ingrese el código del producto que desea actualizar: ");
    String codigo = scanner.nextLine();

    // Buscar el producto por código
    ProductoBase producto = null;
    for (ProductoBase p : productos) {
        if (p.getCodigo().equalsIgnoreCase(codigo)) {
            producto = p;
            break;
        }
    }

    if (producto == null) {
        System.out.println("Producto no encontrado.");
        return;
    }

    // Si el producto se encuentra, pedimos los nuevos valores
    System.out.println("Producto encontrado: " + producto.getCodigo() + " - " + producto.getMarca());

    // Actualizar los atributos
    System.out.println("¿Qué desea actualizar?");
    System.out.println("1. Costo");
    System.out.println("2. Precio de venta");
    System.out.println("3. Stock");
    System.out.println("4. Marca");
    System.out.println("5. Presentación");
    System.out.println("6. Grupo");
    System.out.println("7. Categoría");
    

    int opcion = Integer.parseInt(scanner.nextLine());

    switch (opcion) {
        case 1:
            System.out.print("Ingrese el nuevo costo: ");
            double nuevoCosto = Double.parseDouble(scanner.nextLine());
            producto.setCosto(nuevoCosto);
            System.out.println("Costo actualizado.");
            break;
        case 2:
            System.out.print("Ingrese el nuevo precio de venta: ");
            double nuevoPrecioVenta = Double.parseDouble(scanner.nextLine());
            producto.setPrecioVenta(nuevoPrecioVenta);
            System.out.println("Precio de venta actualizado.");
            break;
        case 3:
            System.out.print("Ingrese el nuevo stock: ");
            int nuevoStock = Integer.parseInt(scanner.nextLine());
            producto.setStock(nuevoStock);
            System.out.println("Stock actualizado.");
            break;
        case 4:
            System.out.print("Ingrese la nueva marca: ");
            String nuevaMarca = scanner.nextLine();
            producto.setMarca(nuevaMarca);
            System.out.println("Marca actualizada.");
            break;
        case 5:
            System.out.print("Ingrese la nueva presentación: ");
            String nuevaPresentacion = scanner.nextLine();
            producto.setPresentacion(nuevaPresentacion);
            System.out.println("Presentación actualizada.");
            break;
        case 6:
            System.out.print("Ingrese el nuevo grupo: ");
            String nuevoGrupo = scanner.nextLine();
            producto.setGrupo(nuevoGrupo);
            System.out.println("Grupo actualizado.");
            break;
        case 7:
            System.out.print("Ingrese la nueva categoría: ");
            String nuevaCategoria = scanner.nextLine();
            producto.setCategoria(nuevaCategoria);
            System.out.println("Categoría actualizada.");
            break;
       
        default:
            System.out.println("Opción no válida.");
    }
}

public void eliminarProducto(String codigo) {
    ProductoBase productoAEliminar = null;
    
    // Buscar el producto por código
    for (ProductoBase producto : productos) {
        if (producto.getCodigo().equalsIgnoreCase(codigo)) {
            productoAEliminar = producto;
            break;
        }
    }
    
    // Si el producto no se encuentra
    if (productoAEliminar == null) {
        System.out.println("Producto no encontrado.");
    } else {
        // Eliminar el producto
        productos.remove(productoAEliminar);
        System.out.println("El producto "+"[ " + productoAEliminar + "]"+" ha sido eliminado");
    }
}

    public void aplicarDescuentoAGrupo(String grupo, Descuento descuento) {
       for (ProductoBase producto : productos) {
        if (producto.getCodigo().equalsIgnoreCase(grupo)) {
            producto.aplicarDescuento();  // Aplica el descuento

            // Mostrar el mensaje con el nuevo precio de venta
            System.out.println("Descuento aplicado al producto con código: " + grupo);
            System.out.println("Nuevo precio de venta: " + producto.getPrecioVenta());
            
        }
    }
    System.out.println("Producto no encontrado con el código: " + grupo);
    }
 
    public void aplicarDescuentoACategoria(String categoria, Descuento descuento) {
         for (ProductoBase producto : productos) {
             if (producto.getCodigo().equalsIgnoreCase(categoria)) {
            producto.aplicarDescuento();  // Aplica el descuento

            // Mostrar el mensaje con el nuevo precio de venta
            System.out.println("Descuento aplicado al producto con código: " + categoria);
            System.out.println("Nuevo precio de venta: " + producto.getPrecioVenta());
            
        }
    }
    System.out.println("Producto no encontrado con el código: " + categoria);
    }

    public void aplicarDescuentoAProducto(String codigo) {
    for (ProductoBase producto : productos) {
        if (producto.getCodigo().equalsIgnoreCase(codigo)) {
            producto.aplicarDescuento();  
            System.out.println("Descuento aplicado al producto con código: " + codigo);
            System.out.println("Nuevo precio de venta: " + producto.getPrecioVenta());
            break;
        }
    }
}

/*
    
    */
public void aplicarDescuentoFijoPorCodigo(String codigoProducto) {
    Scanner scanner = new Scanner(System.in);
    boolean productoEncontrado = false;
    double descuentoFijo;

    // Pedimos el monto del descuento fijo por consola
    System.out.print("Ingresa el monto de descuento fijo (en unidades monetarias): ");
    descuentoFijo = scanner.nextDouble();

    // Creamos una instancia de DescuentoFijo
    DescuentoFijo descuento = new DescuentoFijo(descuentoFijo);

    // Recorremos los productos
    for (ProductoBase producto : productos) {
        // Buscamos el producto por su código
        if (producto.getCodigo().equalsIgnoreCase(codigoProducto)) {
            productoEncontrado = true;

            // Aplicamos el descuento fijo usando el método de DescuentoFijo
            double nuevoPrecio = descuento.calcularPrecioConDescuento(producto.getPrecioVenta());

            // Aseguramos que el precio no quede negativo
            if (nuevoPrecio < 0) {
                System.out.println("El descuento es mayor que el precio del producto. El precio se ajustará a 0.");
                producto.setPrecioVenta(0);
            } else {
                producto.setPrecioVenta(nuevoPrecio);
            }

            // Mostramos el resultado
            System.out.println("Descuento de " + descuentoFijo + " aplicado al producto con código: " + producto.getCodigo());
            System.out.println("Nuevo precio de venta: " + producto.getPrecioVenta());
        }
    }

    // Si no encontramos el producto
    if (!productoEncontrado) {
        System.out.println("No se encontró ningún producto con el código: " + codigoProducto);
    }
}


    // Mostrar todos los productos
        public void mostrarProductos() {
        for (ProductoBase producto : productos) {
            System.out.println(producto);
        }
    }
}
