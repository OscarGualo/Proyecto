package javaapplication1;

import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
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
                if (datos.length < 10)
                    continue; // Verificar que haya suficientes columnas

                // Limpiar datos y asignar variables
                String codigo = datos[0].trim();
                String marca = datos[1].trim();
                String presentacion = datos[2].trim();

                // Validar valores numéricos
                double costo = datos[3].trim().matches("\\d+(\\.\\d+)?") ? Double.parseDouble(datos[3].trim()) : 0.0;
                double precioVenta = datos[4].trim().matches("\\d+(\\.\\d+)?") ? Double.parseDouble(datos[4].trim())
                        : 0.0;
                int stock = datos[5].trim().matches("\\d+") ? Integer.parseInt(datos[5].trim()) : 0;

                String grupo = datos[6].trim();
                String categoria = datos[7].trim();
                String productoEspecifico = datos[8].trim();

                // Leer el descuento (última columna en el CSV)
                double descuento = datos[9].trim().matches("\\d+(\\.\\d+)?") ? Double.parseDouble(datos[9].trim())
                        : 0.0;

                // Crear y añadir el producto
                ProductoBase producto = new ProductoEspecifico(codigo, marca, presentacion, costo, precioVenta, stock,
                        grupo, categoria, productoEspecifico, descuento);
                productos.add(producto);
            }

            // Mensaje de depuración: Verifica que los productos se han cargado
            // correctamente
            System.out.println("Productos cargados: " + productos.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Va a consultar por grupo
     * recibe de paremtro el grupo ingreasdo por el usuarip
     * Programacion Funcionala
     * 
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
        System.out
                .println("\nCódigo:   " + "Marca:            " + "Presentación:                     " + "Costo:    " + "Precio:  "
                        + "Stock:  " + "Grupo:                       " + "Categoría:                          " + 
                        "Producto Específico:          " + "Descuento: ");

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
            if (producto.getCodigo().equals(codigo)) {
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
            // Imprimir el encabezado con el formato adecuado
            System.out
                    .println(String.format("%-30s %-31s %-20s %-10s", "\nProducto:", " Presentacion:", "Marca:", "Stock:\n"));

            // Recorrer la lista de productos y mostrar cada uno con el formato adecuado
            for (ProductoBase producto : productos) {
                System.out.println(String.format("%-30s %-30s %-20s %-10d",
                        producto.getProductoEspecifico(),
                        producto.getPresentacion(),
                        producto.getMarca(),
                        producto.getStock()));
            }
        }
    }

    public void ingresarNuevoProducto() {
        Scanner scanner = new Scanner(System.in);
        int opcion =0;

        while (true) {
            System.out.println("\nIngrese los detalles del nuevo producto:");

            System.out.print("Código: ");
            String codigo = scanner.nextLine();

            System.out.print("Marca: ");
            String marca = scanner.nextLine();

            System.out.print("Presentación: ");
            String presentacion = scanner.nextLine();

            System.out.print("Costo: ");
            double costo = revisarNumero();
            
            System.out.print("Precio de venta: ");
            double precioVenta = revisarNumero();

            int stock =0;    
            while (true) {
                opcion = 0;
                try {
                    System.out.print("Stock: ");
                    stock = Integer.parseInt(scanner.nextLine());
                    if(stock < 0){
                        System.out.println("No se premite numeros menores a 0");
                        opcion = -1;
                    }
                } catch (NumberFormatException e) {
                     System.out.print("\nSolo se permiten numeros enteros\n");
                     System.out.println("Ingrese de nuevo");
                     opcion = -1;
                }
                    if(opcion == 0){
                        break;
                    }
                }

            System.out.print("Grupo: ");
            String grupo = scanner.nextLine();

            System.out.print("Categoría: ");
            String categoria = scanner.nextLine();

            System.out.print("Producto específico: ");
            String productoEspecifico = scanner.nextLine();

            System.out.print("Descuento: ");
            double descuento = revisarNumero();

            // Crear el nuevo producto
            ProductoBase nuevoProducto = new ProductoEspecifico(codigo, marca, presentacion, costo, precioVenta, stock,
                    grupo, categoria, productoEspecifico, descuento);

            // Agregar el producto a la lista
            productos.add(nuevoProducto);
            System.out.println("\nProducto agregado correctamente.\n");

            do {
               /*
               * try-catch atrapa excepcionestipo  NumberFormatException (sale si se ingresa una
               * letra en vez de un número)
               */
              try {
                    System.out.println("\nIngrese 0 para salir o 1 para ingresar otro producto\n>> ");
                    opcion = Integer.parseInt(scanner.nextLine());
                    if((opcion < 0)||(opcion > 1)){
                        System.out.print("\nError, el numero: " + opcion + " no es una opcion\n");
                        System.out.println("Intentelo de nuevo....");
                    }
                } catch (NumberFormatException e) {
                    System.out.print("\nNo se permiten letras. Intentelo de nuevo ....\n");
                    opcion = -1;
                }
            }while ((opcion < 0)||(opcion > 1));

            if (opcion == 0){
                break;
            }
        }
    }
    /**
     * Descripción: Revisa que el valor ingresado no sea un String y menor a cero.
     *              En el caso que sea menor a 0 o una letra solicita que se repita
     *              el ingreso de datos
     * @return un parámetro tipo double.
     */
    private double revisarNumero (){
        Scanner datos = new Scanner(System.in);
        double numero = 0;
        do {
            try {
                numero = Double.parseDouble(datos.nextLine());
                if(numero < 0){
                    System.out.println("Error, no permiten números menores 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("No se permite letras. Ingrese de nuevo");
                numero = -1;
            }
        } while (numero < 0);
        return numero;
    }

    public void actualizarProducto() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Ingrese el código del producto que desea actualizar: \n>> ");
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
                System.out.println("\nProducto no encontrado.");
                return;
            }

            // Si el producto se encuentra, pedimos los nuevos valores
            System.out
                .println("\nCódigo:   " + "Marca:            " + "Presentación:                     " + "Costo:    " + "Precio:  "
                        + "Stock:  " + "Grupo:                       " + "Categoría:                          " + 
                        "Producto Específico:          " + "Descuento: ");

            producto.mostrarDetalles();

            // Actualizar los atributos
            System.out.println("\n¿Qué desea actualizar?");
            System.out.println("1. Costo");
            System.out.println("2. Precio de venta");
            System.out.println("3. Stock");
            System.out.println("4. Marca");
            System.out.println("5. Presentación");
            System.out.println("6. Grupo");
            System.out.print("7. Categoría\n>> ");

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
                    System.out.println("\nOpción no válida.");
            }

            System.out.print("\nIngrese 0 para salir o cualquier numero para ingresar otro producto\n>> ");
            String opc = scanner.nextLine();
            if (opc.equals("0")) {
                break;
            }
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
            System.out.println("\nEl siguiente producto: ");
            System.out
                .println("\nCódigo:   " + "Marca:            " + "Presentación:                     " + "Costo:    " + "Precio:  "
                        + "Stock:  " + "Grupo:                       " + "Categoría:                          " + 
                        "Producto Específico:          " + "Descuento: ");

            productoAEliminar.mostrarDetalles();
            System.out.println("\nHa sido eliminado");
        }
    }

    public void aplicarDescuentoAGrupo(String grupo, Descuento descuento) {
        for (ProductoBase producto : productos) {
            if (producto.getCodigo().equalsIgnoreCase(grupo)) {
                producto.aplicarDescuento(); // Aplica el descuento

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
                producto.aplicarDescuento(); // Aplica el descuento

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
                System.out.println("\nDescuento del " + producto.getDescuento() + " % aplicado al producto con código: " + codigo);
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

        while (true) {
            try {
                System.out.print("\nIngresa el monto de descuento fijo (en unidades monetarias): ");
                descuentoFijo = Double.parseDouble(scanner.nextLine());
                if(descuentoFijo < 0){
                    System.out.println("No se perimten descuentos menores que cero. Intentelo de nuevo..");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: No se permiten letras. Intentelo de nuevo..");
                descuentoFijo = -1;
            }
            if (descuentoFijo >=0){
                break;
            }
        }

        // Creamos una instancia de DescuentoFijo
        DescuentoFijo descuento = new DescuentoFijo(descuentoFijo);
        int i = 1;
        // Recorremos los productos
        for (ProductoBase producto : productos) {
            double PrecioAntiguo = producto.getPrecioVenta();
            if (producto.getCodigo().equalsIgnoreCase(codigoProducto)) {
                productoEncontrado = true;

                // Aplicamos el descuento fijo usando el método de DescuentoFijo
                double nuevoPrecio = descuento.calcularPrecioConDescuento(producto.getPrecioVenta());
                System.out.print("\n" + i + ". ");
                // Aseguramos que el precio no quede negativo
                if (nuevoPrecio < 0) {
                    System.out
                            .println("El descuento es mayor que el precio del producto. El precio se ajustará a 0.");
                    producto.setPrecioVenta(0);
                } else {
                    producto.setPrecioVenta(nuevoPrecio);
                }

                
                // Mostramos el resultado
                System.out.print(
                        "Descuento de " + descuentoFijo + " aplicado al producto: " + producto.getProductoEspecifico() + "(" + producto.getPresentacion() +") con codigo "
                                + producto.getCodigo());
                System.out.println("\nPrecio sin descuento: " + PrecioAntiguo + "$\nNuevo precio de venta: " + producto.getPrecioVenta());

                i++;
            }
        }

        // Si no encontramos el producto
        if (!productoEncontrado) {
            System.out.println("No se encontró ningún producto con el código: " + codigoProducto);
        }
    }

    // Mostrar todos los productos
    public void mostrarProductos() {
        System.out
                .println("\nCódigo:   " + "Marca:            " + "Presentación:                     " + "Costo:    " + "Precio:  "
                        + "Stock:  " + "Grupo:                       " + "Categoría:                          " + 
                        "Producto Específico:          " + "Descuento: ");

        for (ProductoBase producto : productos) {
            System.out.println(producto);
        }
    }

    // Mostrar grupos sin repetir
    public void mostrarGrupos() {
        Set<String> grupos = new HashSet<>();
        System.out.println("\nGrupos: ");
        for (ProductoBase productoBase : productos) {
            grupos.add(productoBase.getGrupo());
        }

        imprimirEnFormaDeTabla(grupos, 2);
    }

    // mostrar categoria sin repetir
    public void mostrarCategorias() {
        Set<String> grupos = new HashSet<>();
        System.out.println("\nCategoria:");
        for (ProductoBase productoBase : productos) {
            grupos.add(productoBase.getCategoria());
        }

        imprimirEnFormaDeTabla(grupos, 4);

    }

    // mostrar nombres sin repetir
    public void mostrarNombreProductos() {
        Set<String> grupos = new HashSet<>();
        System.out.println("\nNombre de los productos:");
        for (ProductoBase productoBase : productos) {
            grupos.add(productoBase.getProductoEspecifico());
        }

        imprimirEnFormaDeTabla(grupos, 4);
    }

    // mostrar marca sin repetir
    public void mostrarMarcaProductos() {
        Set<String> grupos = new HashSet<>();
        System.out.println("\nMarcas:");
        for (ProductoBase productoBase : productos) {
            grupos.add(productoBase.getMarca());
        }

        imprimirEnFormaDeTabla(grupos, 4);
    }

    public void mostrarCodigoProductos() {
        Set<String> grupos = new HashSet<>();
        System.out.println("\nCodigos:");
        for (ProductoBase productoBase : productos) {
            grupos.add(productoBase.getCodigo());
        }

        imprimirEnFormaDeTabla(grupos, 4);

    }

    public void imprimirEnFormaDeTabla(Set<String> set, int columnas) {
        List<String> lista = new ArrayList<>(set);
        int filas = (int) Math.ceil((double) lista.size() / columnas);
        String[][] table = new String[filas][columnas];

        // Rellenar la tabla con los elementos de la lista
        for (int i = 0; i < lista.size(); i++) {
            table[i / columnas][i % columnas] = lista.get(i);
        }

        // Calcular el ancho máximo de cada columna
        int[] maxWidths = new int[columnas];
        for (int col = 0; col < columnas; col++) {
            for (int row = 0; row < filas; row++) {
                if (table[row][col] != null && table[row][col].length() > maxWidths[col]) {
                    maxWidths[col] = table[row][col].length();
                }
            }
        }

        // Imprimir la tabla
        
        for (int row = 0; row < filas; row++) {
            for (int col = 0; col < columnas; col++) {
                if (table[row][col] != null) {
                    System.out.print(String.format("%-" + (maxWidths[col] + 4) + "s", "  - " + table[row][col]));
                }
            }
            System.out.println();
        }
    }


}
