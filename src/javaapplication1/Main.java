package javaapplication1;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int opc = 0;
        int salida = 1;
        boolean bandera = true;
        Scanner datos = new Scanner(System.in);
        SistemaGestionProductos s1 = new SistemaGestionProductos();

        do {
            do {
                System.out.println("\nSistema de Gestion de Alimentos");
                System.out.println("1. Cargar Informacion");
                System.out.println("2. Consultar por criterios");
                System.out.println("3. Rango de precios");
                System.out.println("4. Consulta de stock");
                System.out.println("5. Ingreso  nuevos productos");
                System.out.println("6. Actualizacion de productos ");
                System.out.println("7. Elimar productos");
                System.out.println("8. Ver inventario");
                System.out.println("9. Aplicar descuentos a productos");
                System.out.println("0. Salir del programa");
                System.out.print("\n>> ");
                try {
                    opc = Integer.parseInt(datos.nextLine());
                } catch (NumberFormatException e) {
                    opc = -1;
                }
                if ((opc< 0)||(opc > 9)) { 
                    System.out.println("Opcion Invalida. Intentelo de nuevo");
                    bandera = false;
                }else{
                    bandera = true;
                }
                
            } while (!bandera);

            switch (opc) {
                case 1:
                    // Pedir la ruta del archivo para que el programa funcione en multiples máquinas
                    System.out.print("\nIngrese la ruta del archivo excel\n>> ");
                    String rutaArchivo = "src\\javaapplication1\\producto.csv";
                    s1.cargarDesdeCSV(rutaArchivo);
                    s1.mostrarProductos();
                    break;
                case 2:
                    int opcCasoDos;
                    System.out.println("\nConsulta de productos por criterios");
                    System.out.println("1. Por grupo ");
                    System.out.println("2. Por categoria");
                    System.out.println("3. Por su nombre");
                    System.out.println("4. Por Marca");
                    System.out.println("5. Por codigo");
                    System.out.print("\n>> ");
                    opcCasoDos = Integer.parseInt(datos.nextLine());
                    switch (opcCasoDos) {
                        case 1:
                            while (true) {
                                s1.mostrarGrupos();
                                System.out.print("\nIngresa el grupo a buscar (o 0 para salir):\n>> ");
                                String grupo = datos.nextLine();

                                if (grupo.equals("0")) {
                                    break;
                                }
                                List<ProductoBase> productosPorGrupo = s1.consultarPorGrupo(grupo);
                                s1.mostrarResultados(productosPorGrupo);
                            }
                            break;
                        case 2:
                            while (true) {
                                s1.mostrarCategorias();
                                System.out.print("\nIngrese la categoria a buscar o (0 para salir):\n>> ");
                                String categoria = datos.nextLine();
                                if (categoria.equals("0")) {
                                    break;
                                }
                                List<ProductoBase> productosPorCategoria = s1.consultarPorCategoria(categoria);
                                s1.mostrarResultados(productosPorCategoria);
                            }
                            break;
                        case 3:
                            while (true) {
                                s1.mostrarNombreProductos();
                                System.out.print("\nIngrese el nombre del producto o (0 para salir):\n>> ");
                                String nombre = datos.nextLine();
                                if (nombre.equals("0")) {
                                    break;
                                }
                                List<ProductoBase> productosPorNombre = s1.consultarPorProductoEspecifico(nombre);
                                s1.mostrarResultados(productosPorNombre);
                            }
                            break;
                        case 4:
                            while (true) {
                                s1.mostrarMarcaProductos();
                                System.out.print("\nIngrese la marca del producto o (0 para salir):\n>> ");
                                String marca = datos.nextLine();
                                if (marca.equals("0")) {
                                    break;
                                }
                                List<ProductoBase> productosPorMarca = s1.consultarPorMarca(marca);
                                s1.mostrarResultados(productosPorMarca);
                            }
                            break;
                        case 5:
                            while (true) {
                                s1.mostrarCodigoProductos();
                                System.out.print("\nIngrese el codigo del producto o (0 para salir):\n>> ");
                                String codigo = datos.nextLine();
                                if (codigo.equals("0")) {
                                    break;
                                }
                                List<ProductoBase> productosPorCodigo = s1.consultarPorCodigo(codigo);
                                s1.mostrarResultados(productosPorCodigo);
                            }
                            break;
                        default:
                            throw new AssertionError("La lista esta vacia");
                    }
                    break;
                case 3:
                    while (true) {

                        System.out.print("\nIngrese el precio mínimo:\n>> ");
                        double precioMinimo = Double.parseDouble(datos.nextLine());
                        System.out.print("Ingrese el precio máximo:\n>> ");
                        double precioMaximo = Double.parseDouble(datos.nextLine());
                        List<ProductoBase> productosPorRango = s1.consultarPorRangoDePrecio(precioMinimo, precioMaximo);
                        s1.mostrarResultados(productosPorRango);
                        System.out.print("\nIngrese 0 para salir: \n>> ");
                        String opcion = datos.nextLine();
                        if (opcion.equals("0")) {
                            break;
                        }
                    }
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
                    while (true) {
                        System.out.print("\nIngrese el código del producto a eliminar: ");
                        String codigoEliminar = datos.nextLine();
                        s1.eliminarProducto(codigoEliminar);

                        System.out.print("\nIngrese 0 para salir o cualquier numero para eliminar otro producto\n>> ");
                        String opcion = datos.nextLine();

                        if (opcion.equals("0")) {
                            break;
                        }
                    }
                    break;
                case 8:
                    s1.mostrarProductos();
                    break;
                case 9:
                    int opcCase9;
                    while (true) {

                        System.out.println("\n1. Descuento porcentual");
                        System.out.print("2. Descuento fijo \n>> ");
                        opcCase9 = Integer.parseInt(datos.nextLine());
                        switch (opcCase9) {
                            case 1:
                                String codigo;
                                System.out.print("\nIngrese el codigo del producto\n>> ");
                                codigo = datos.nextLine();
                                s1.aplicarDescuentoAProducto(codigo);

                                break;
                            case 2:
                                String codigoDos;
                                System.out.print("\nIngrese el codigo del producto\n>> ");
                                codigoDos = datos.nextLine();
                                s1.aplicarDescuentoFijoPorCodigo(codigoDos);
                                break;

                        }

                        System.out.print("\nIngrese 0 para salir o cualquier numero para aplicar otro descuento\n>> ");
                        String opcion = datos.nextLine();

                        if (opcion.equals("0")) {
                            break;
                        }
                    }
                    break;

                case 0:
                    salida = 0;
                    System.out.print("\nSaliendo del programa...\n\n");
                    return;
                default:
                    throw new AssertionError("Salida");
            }

            System.out
                    .print("\nPresione 0 para salir del programa o cualquier numero para regresar al menu principal\n>> ");
            salida = Integer.parseInt(datos.nextLine());

            if (salida == 0) {
                bandera = false;
                System.out.print("\nSaliendo del programa...\n\n");
            } else if (salida == 1) {
                bandera = true;
            }
        } while (bandera);

    }
}
