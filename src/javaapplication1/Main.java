package javaapplication1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int opc = 0;
        int salida;
        boolean bandera = true;
        Scanner datos = new Scanner(System.in);
        SistemaGestionProductos s1 = new SistemaGestionProductos();

        do {
            System.out.println("Sistema de Gestion de Alimentos");
            System.out.println("1. Cargar Informacion");
            System.out.println("2. Consultar por criterios");
            System.out.println("3. Rango de precios");
            System.out.println("4. Consulta de stock");
            System.out.println("5. Ingreso  nuevos productos");
            System.out.println("6. Actualizacion de productos ");
            System.out.println("7. Elimar productos");
            System.out.println("8. Ver inventario");
            System.out.println("9. Aplicar descuentos a productos");
            System.out.print("\n>> ");
            opc = Integer.parseInt(datos.nextLine());
            switch (opc) {
                case 1:
                    // Pedir la ruta del archivo para que el programa funcione en multiples máquinas
                    System.out.print("\nIngrese la ruta del archivo excel\n>> ");
                    String rutaArchivo = datos.nextLine();
                    s1.cargarDesdeCSV(rutaArchivo);
                    s1.mostrarProductos();
                    System.out.print("\nIngrese una ruta de archivo correcta");
                    break;
                case 2:
                    int opcCasoDos;
                    System.out.println("\nConsulta de productos por criterios");
                    System.out.println("1. Por grupo ");
                    System.out.println("2. Por categoria");
                    System.out.println("3. Por su nombre");
                    System.out.println("5. Por Marca");
                    System.out.println("6. Por codigo");
                    System.out.print("\n>> ");
                    opcCasoDos = Integer.parseInt(datos.nextLine());

                    switch (opcCasoDos) {
                        case 1:
                            s1.mostrarGrupos();
                            System.out.print("\nIngresa el grupo a buscar:\n>> ");
                            String grupo = datos.nextLine(); // Aquí el usuario ingresa el grupo
                            List<ProductoBase> productosPorGrupo = s1.consultarPorGrupo(grupo);
                            s1.mostrarResultados(productosPorGrupo);
                            break;
                        case 2:
                            s1.mostrarCategorias();
                            System.out.print("\nIngrese la categoria a buscar:\n>> ");
                            String categoria = datos.nextLine();
                            List<ProductoBase> productosPorCategoria = s1.consultarPorCategoria(categoria);
                            s1.mostrarResultados(productosPorCategoria);
                            break;
                        case 3:
                            s1.mostrarNombreProductos();
                            System.out.print("\nIngrese el nombre del producto:\n>> ");
                            String nombre = datos.nextLine();
                            List<ProductoBase> productosPorNombre = s1.consultarPorProductoEspecifico(nombre);
                            s1.mostrarResultados(productosPorNombre);
                            break;
                        case 5:
                            s1.mostrarMarcaProductos();
                            System.out.print("\nIngrese la marca del producto:\n>> ");
                            String marca = datos.nextLine();
                            List<ProductoBase> productosPorMarca = s1.consultarPorMarca(marca);
                            s1.mostrarResultados(productosPorMarca);
                            break;
                        case 6:
                            s1.mostrarCodigoProductos();
                            System.out.print("\nIngrese el codigo del producto:\n>> ");
                            String codigo = datos.nextLine();
                            List<ProductoBase> productosPorCodigo = s1.consultarPorCodigo(codigo);
                            s1.mostrarResultados(productosPorCodigo);

                        default:
                            throw new AssertionError("La lista esta vacia");

                    }
                    break;
                case 3:
                    System.out.println("\nIngrese el precio mínimo:");
                    double precioMinimo = Double.parseDouble(datos.nextLine());
                    System.out.println("Ingrese el precio máximo:");
                    double precioMaximo = Double.parseDouble(datos.nextLine());
                    List<ProductoBase> productosPorRango = s1.consultarPorRangoDePrecio(precioMinimo, precioMaximo);
                    s1.mostrarResultados(productosPorRango);
                    break;
                case 4:
                    s1.consultarStock();
                    break;
                case 5:
                    s1.ingresarNuevoProducto();
                    break;
                case 6:
                    s1.actualizarProducto();
                    break;
                case 7:
                    System.out.print("Ingrese el código del producto a eliminar: ");
                    String codigoEliminar = datos.nextLine();
                    s1.eliminarProducto(codigoEliminar);
                    break;
                case 8:
                    s1.mostrarProductos();
                    break;
                case 9:
                    int opcCase9;
                    System.out.println("1. Descuento porcentual ");
                    System.out.println("2 .Descuento fijo ");
                    opcCase9 = Integer.parseInt(datos.nextLine());
                    switch (opcCase9) {
                        case 1:
                            String codigo;
                            System.out.println("Ingrese el codigo del producto");
                            codigo = datos.nextLine();
                            s1.aplicarDescuentoAProducto(codigo);

                            break;
                        case 2:
                            String codigoDos;
                            System.out.println("Ingrese el codigo del producto");
                            codigoDos = datos.nextLine();
                            s1.aplicarDescuentoFijoPorCodigo(codigoDos);
                            break;

                    }
                    break;
                default:
                    throw new AssertionError("Salida");
            }
            System.out.println("Presione 1 para regresar el menu principal");
            salida = Integer.parseInt(datos.nextLine());
            if (salida == 1) {
                bandera = true;
            }
        } while (bandera);

    }
}
