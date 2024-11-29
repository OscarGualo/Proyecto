package javaapplication1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int opc = 0 ;
        boolean bandera = true; 
        Scanner datos = new Scanner(System.in);
        SistemaGestionProductos s1 = new SistemaGestionProductos();
        do{
        System.out.println("Sistema de Gestion de Alimentos");
        System.out.println("1. Cargar Informacion");
            System.out.println("2.COnsultar por criterios");
            System.out.println("3. Rango de precios");
            System.out.println("4.Consulta de stock");
            System.out.println("5.Ingreso  nuevos productos");
            System.out.println("6.Actualizacion de productos ");
            System.out.println("7. Elimar productos");
            System.out.println("8. ver inventario");
        opc = Integer.parseInt(datos.nextLine());
        switch (opc) {
            case 1:
                s1.cargarDesdeCSV("C:\\Users\\oscar\\Documents\\NetBeansProjects\\JavaApplication1\\src\\javaapplication1\\producto.csv");
                s1.mostrarProductos();
                break;
            case 2: 
                int opcCasoDos; 
                System.out.println("Consulta de productos criterios");
                System.out.println("1.  Por grupo ");
                System.out.println("2. Por categoria");
                System.out.println("3.Por su nombre");
                System.out.println("5.Por Marca");
                System.out.println("6. Por codigo");
                opcCasoDos = Integer.parseInt(datos.nextLine());
                switch (opcCasoDos) {
                    case 1:
                            System.out.println("Ingresa el grupo a buscar: ");
                             String grupo = datos.nextLine();  // Aquí el usuario ingresa el grupo
                            List<ProductoBase> productosPorGrupo = s1.consultarPorGrupo(grupo);
                             s1.mostrarResultados(productosPorGrupo);
                    break;
                    case 2: 
                        System.out.println("ingrese la categoria a buscar");
                        String categoria = datos.nextLine();
                        List<ProductoBase> productosPorCategoria = s1.consultarPorCategoria(categoria);
                        s1.mostrarResultados(productosPorCategoria);
                          break;
                     case 3: 
                        System.out.println("ingrese el nombre del producto");
                        String nombre = datos.nextLine();
                        List<ProductoBase> productosPorNombre = s1.consultarPorProductoEspecifico(nombre);
                        s1.mostrarResultados(productosPorNombre);
                          break;
                      case 5: 
                        System.out.println("ingrese la marca del producto");
                        String marca = datos.nextLine();
                        List<ProductoBase> productosPorMarca= s1.consultarPorMarca(marca);
                        s1.mostrarResultados(productosPorMarca); 
                          break;
                      case 6: 
                          System.out.println("Ingrese el codigo");
                          String codigo = datos.nextLine();
                          List<ProductoBase> productosPorCodigo= s1.consultarPorCodigo(codigo);
                         s1.mostrarResultados(productosPorCodigo); 
                          
                    default:
                        throw new AssertionError("La lista esta vacia");
                    
                }
                break;
            case 3: 
                     System.out.println("Ingrese el precio mínimo:");
                        double precioMinimo = Double.parseDouble(datos.nextLine());
                        System.out.println("Ingrese el precio máximo:");
                        double precioMaximo = Double.parseDouble(datos.nextLine());
                        List<ProductoBase> productosPorRango = s1.consultarPorRangoDePrecio(precioMinimo, precioMaximo);
                        s1.mostrarResultados(productosPorRango);
                break;
            case 4 : 
                 s1.consultarStock();
                 break;
            case 5: 
                 s1.ingresarNuevoProducto();
                    break;
            case 6: 
                s1.actualizarProducto();
                break;
            case 7 :
                System.out.print("Ingrese el código del producto a eliminar: ");
                String codigoEliminar = datos.nextLine();
                s1.eliminarProducto(codigoEliminar);
                break;
            case 8:
                s1.mostrarProductos();
                break;
            default:
                throw new AssertionError("Salida");
        }
        }while(bandera);
        
        
        
  

}
}
