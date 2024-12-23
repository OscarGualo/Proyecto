package javaapplication1;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int opc = 0, contEntradas = 7;
        int salida = 1;
        boolean bandera = true;
        boolean informacionCargada = false;
        Scanner datos = new Scanner(System.in);
        SistemaGestionProductos s1 = new SistemaGestionProductos();
        Empresa empresa = new Empresa("MULTICOMERCIO", s1);

        do {
            /*
             * do-while permite que se vuelva repetir el ingreso de datos, solo si el número
             * ingresado
             * no se encuentra en las opciones del menú
             */
            do {
                System.out.println("*------ EMPRESA " + empresa.getNombreEmpresa() + " -----*");
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
                /*
                 * try-catch atrapa excepcionestipo NumberFormatException (sale si se ingresa
                 * una
                 * letra en vez de un número)
                 */
                try {
                    opc = Integer.parseInt(datos.nextLine());
                    if ((opc < 0) || (opc > 9)) {
                        System.out.print("\nError, el numero: " + opc + " no es una opcion\n");
                        System.out.println("Intentelo de nuevo....");
                    }
                } catch (NumberFormatException e) {
                    System.out.print("\nNo se permiten letras. Intentelo de nuevo ....\n");
                    opc = -1;
                }
                if ((opc < 0) || (opc > 9)) {
                    bandera = false;
                } else {
                    bandera = true;
                }

            } while (!bandera);

            // Verificación de carga de información antes de las opciones 2-9
            if (!informacionCargada && opc != 1 && opc != 0) {
                System.out.println(
                        "\nDebe cargar la información antes de realizar esta acción. Por favor, elija la opción 1.\n");
                continue;
            }
            switch (opc) {
                case 1:
                    // Pedir la ruta del archivo para que el programa funcione en multiples máquinas
                    System.out.print("\nIngrese la ruta del archivo excel\n>> ");
                    String rutaArchivo = "src\\javaapplication1\\producto.csv";
                    s1.cargarDesdeCSV(rutaArchivo);
                    s1.mostrarProductos();
                    informacionCargada = true;
                    break;
                case 2:
                    int opcCasoDos;
                    /*
                     * do-while permite que se vuelva repetir el ingreso de datos, solo si el número
                     * ingresado
                     * no se encuentra en las opciones del menú
                     */
                    do {
                        System.out.println("\nConsulta de productos por criterios");
                        System.out.println("1. Por grupo ");
                        System.out.println("2. Por categoria");
                        System.out.println("3. Por su nombre");
                        System.out.println("4. Por Marca");
                        System.out.println("5. Por codigo");
                        System.out.print("\n>> ");
                        /*
                         * try-catch atrapa excepcionestipo NumberFormatException (sale si se ingresa
                         * una
                         * letra en vez de un número)
                         */

                        try {
                            opcCasoDos = Integer.parseInt(datos.nextLine());
                            if (opcCasoDos == 0) {
                                break;
                            }
                            if ((opcCasoDos < 1) || (opcCasoDos > 5)) {
                                System.out.print("\nError, el numero: " + opcCasoDos + " no es una opcion\n");
                                System.out.println("Intentelo de nuevo....");
                            }
                        } catch (NumberFormatException e) {
                            System.out.print("\nNo se permiten letras. Intentelo de nuevo ....\n");
                            opcCasoDos = -1;
                        }
                        if ((opcCasoDos < 1) || (opcCasoDos > 9)) {
                            bandera = false;
                        } else {
                            bandera = true;
                        }

                    } while (!bandera);
                    // Menú del caso 2
                    try {
                        switch (opcCasoDos) {
                            case 1:
                                while (true) {
                                    s1.mostrarGrupos();
                                    System.out.print("\nIngrese un grupo a buscar o 0 para salir:\n>> ");
                                    String grupo = datos.nextLine();
                                    if (grupo.equals("0")) {
                                        break;
                                    }
                                    List<ProductoBase> productosPorGrupo = s1.consultarPorGrupo(grupo);
                                    if (productosPorGrupo.isEmpty()) {
                                        // Excepcion que se lanza si no se encuentran productos en el grupo
                                        throw new ProductoNoEncontradoException(
                                                "No se encontraron productos en el grupo: " + grupo);
                                    }
                                    s1.mostrarResultados(productosPorGrupo);
                                }
                                break;

                            case 2:
                                while (true) {
                                    s1.mostrarCategorias();
                                    System.out.print("\nIngrese la categoria a buscar o 0 para salir:\n>> ");
                                    String categoria = datos.nextLine();
                                    if (categoria.equals("0")) {
                                        break;
                                    }
                                    List<ProductoBase> productosPorCategoria = s1.consultarPorCategoria(categoria);
                                    if (productosPorCategoria.isEmpty()) {
                                        // Excepcion que se lanza si no se encuentran productos en la categoria
                                        throw new ProductoNoEncontradoException(
                                                "No se encontraron productos en la categoria: " + categoria);
                                    }
                                    s1.mostrarResultados(productosPorCategoria);
                                }
                                break;

                            case 3:
                                while (true) {
                                    s1.mostrarNombreProductos();
                                    System.out.print("\nIngrese el nombre del producto o 0 para salir:\n>> ");
                                    String nombre = datos.nextLine();
                                    if (nombre.equals("0")) {
                                        break;
                                    }
                                    List<ProductoBase> productosPorNombre = s1.consultarPorProductoEspecifico(nombre);
                                    if (productosPorNombre.isEmpty()) {
                                        // Excepcion que se lanza si no se encuentran productos en el nombre
                                        throw new ProductoNoEncontradoException(
                                                "No se encontraron productos en la nombre: " + nombre);
                                    }
                                    s1.mostrarResultados(productosPorNombre);
                                }
                                break;

                            case 4:
                                while (true) {
                                    s1.mostrarMarcaProductos();
                                    System.out.print("\nIngrese la marca del producto o 0 para salir:\n>> ");
                                    String marca = datos.nextLine();
                                    if (marca.equals("0")) {
                                        break;
                                    }
                                    List<ProductoBase> productosPorMarca = s1.consultarPorMarca(marca);
                                    if (productosPorMarca.isEmpty()) {
                                        // Excepcion que se lanza si no se encuentran productos en la marca
                                        throw new ProductoNoEncontradoException(
                                                "No se encontraron productos en la marca: " + marca);
                                    }
                                    s1.mostrarResultados(productosPorMarca);
                                }
                                break;
                            case 5:
                                while (true) {
                                    s1.mostrarCodigoProductos();
                                    System.out.print("\nIngrese el codigo del producto o 0 para salir:\n>> ");
                                    String codigo = datos.nextLine();
                                    if (codigo.equals("0")) {
                                        break;
                                    }
                                    List<ProductoBase> productosPorCodigo = s1.consultarPorCodigo(codigo);
                                    if (productosPorCodigo.isEmpty()) {
                                        // Excepcion que se lanza si no se encuentran productos en e codigo
                                        throw new ProductoNoEncontradoException(
                                                "No se encontraron productos en el codigo: " + codigo);
                                    }
                                    s1.mostrarResultados(productosPorCodigo);
                                }
                                break;
                            default:
                                throw new OperacionInvalidaException("Argumentos no validos D:");
                        }
                    } catch (ProductoNoEncontradoException | OperacionInvalidaException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    while (true) {
                        System.out.print("\nIngrese el precio minimo o 000 para salir:\n>> ");
                        double precioMinimo = Double.parseDouble(datos.nextLine());
                        if (precioMinimo == 000) {
                            break;
                        }
                        System.out.print("Ingrese el precio maximo o 000 para salir:\n>> ");
                        double precioMaximo = Double.parseDouble(datos.nextLine());
                        List<ProductoBase> productosPorRango = s1.consultarPorRangoDePrecio(precioMinimo, precioMaximo);
                        s1.mostrarResultados(productosPorRango);
                        // contEntradas controla que solo pueda digitar 1 ó 0 para repetir el proceso o
                        // terminarlo
                        contEntradas = validarEntradas(0, 1, " volver a ingresar el precio minimo y maximo");

                        if (contEntradas == 0) {
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
                        System.out.print("\nIngrese el codigo del producto a eliminar  o 0 para salir: ");
                        String codigoEliminar = datos.nextLine();
                        // Permite salir sin ingresar ningun código
                        if (codigoEliminar.equals("0")) {
                            contEntradas = 0;
                            break;
                        }
                        s1.eliminarProducto(codigoEliminar);
                        contEntradas = validarEntradas(0, 1, " ingresar otro codigo");
                        // if rompe el bloque para ir directo al menú principal.
                        if (contEntradas == 0) {
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
                        /*
                         * do-while permite que se vuelva repetir el ingreso de datos, solo si el número
                         * ingresado
                         * no se encuentra en las opciones del menú
                         */
                        do {
                            System.out.println("Escoja el tipo de descuento o ingrese 0 para salir: ");
                            System.out.println("\n1. Descuento porcentual");
                            System.out.print("2. Descuento fijo \n>> ");
                            try {
                                opcCase9 = Integer.parseInt(datos.nextLine());
                                if(opcCase9 == 0){
                                    break;
                                }
                                if ((opcCase9 < 0) || (opcCase9 > 2)) {
                                    System.out.print("\nError, el numero: " + opcCase9 + " no es una opcion\n");
                                    System.out.println("Intentelo de nuevo....");
                                }
                            } catch (NumberFormatException e) {
                                System.out.print("\nNo se permiten letras. Intentelo de nuevo ....\n");
                                opcCase9 = -1;
                            }
                            if ((opcCase9 < 0) || (opcCase9 > 2)) {
                                bandera = false;
                            } else {
                                bandera = true;
                            }
                        } while (!bandera);
                        // Menú del caso 9
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

                        if (opcCase9 == 0){
                            contEntradas =0;
                            break;
                        }
                        System.out.print("\nIngrese cualquier numero para aplicar otro descuento o 0 para salir1\n>> ");
                        String opcion = datos.nextLine();

                        if (opcion.equals("0")) {
                            break;
                        }
                    }
                    break;
                default:
                    salida = 0;
                    System.out.print("\nSaliendo del programa...\n\n");
                    return;
            }
            // Salida valida que el valor ingresado sea solo 1 ó 0 y si no cumple vuelve a
            // solicitar el ingreso
            if (contEntradas == 0) {
                salida = 1;
            } else {
                salida = validarEntradas(0, 1, "regresar al menu principal");
            }
            // if-else depende del resultado de salida
            if (salida != 0) {
                bandera = true;
            } else {
                bandera = false;
                System.out.print("\nSaliendo del programa...\n\n");
            }
        } while (bandera);

    }

    /**
     * Description: Método para validar que la entrada solo sea las que se prensenta
     * en las opciones.
     * 
     * @param num1 parámetro tipo int.
     * @param num2 parámetro tipo int.
     * @return un int.
     */
    public static int validarEntradas(int num1, int num2, String mensaje) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        do {
            /*
             * try-catch atrapa excepcionestipo NumberFormatException (sale si se ingresa
             * una
             * letra en vez de un número)
             */
            try {
                System.out.print("\nIngrese 0 para regresar al menu principal o 1 para " + mensaje + " \n>> ");
                opcion = Integer.parseInt(scanner.nextLine());
                if ((opcion < num1) || (opcion > num2)) {
                    System.out.print("\nError, el numero: " + opcion + " no es una opcion\n");
                    System.out.println("Intentelo de nuevo....");
                }
            } catch (NumberFormatException e) {
                System.out.print("\nSolo se permiten numeros enteros. Intentelo de nuevo ....\n");
                opcion = num1 - 1;
            }
        } while ((opcion < num1) || (opcion > num2));
        return opcion;
    }

}

class OperacionInvalidaException extends Exception {
    public OperacionInvalidaException(String mensaje) {
        super(mensaje);
    }
}

class RangoPrecioInvalidoException extends Exception {
    public RangoPrecioInvalidoException(String mensaje) {
        super(mensaje);
    }
}

class ProductoNoEncontradoException extends Exception {
    public ProductoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
