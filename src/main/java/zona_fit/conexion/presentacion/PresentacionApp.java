package zona_fit.conexion.presentacion;

import zona_fit.conexion.datos.ClienteDAO;
import zona_fit.conexion.datos.IClienteDAO;
import zona_fit.conexion.dominio.Cliente;

import java.security.spec.ECField;
import java.util.Scanner;

public class PresentacionApp {
    public static void main(String[] args) {
        zonaFitAPP();
    }
    public static void zonaFitAPP(){
        boolean salir = false;
        IClienteDAO clienteDAO = new ClienteDAO();
        Scanner teclado = new Scanner(System.in);
        while(!salir){
            try {
                var opcion = mostrarMenu(teclado);
                salir = ejecutarOpciones(teclado,opcion,clienteDAO);
            }catch (Exception e){
                System.out.println("Error al ejecutar opciones " + e.getMessage());
                e.printStackTrace();
            }
            System.out.println();
        }
    }
    public static int mostrarMenu(Scanner consola){
        while(true){
            System.out.print("""
                ****** Menu zona_fit ******
                1. Mostar lista de clientes
                2. Buscar cliente por ID
                3. Actualizar cliente por ID
                4. Agregar cliente por ID
                5. Eliminar cliente por ID
                6. Salir
                Seleccione una opcion:\s""");
            try {
                return Integer.parseInt(consola.nextLine());
            }catch (Exception e){
                System.out.println("Error al leer la opción: " + e.getMessage());
            }
        }
    }
    public static boolean ejecutarOpciones(Scanner consola,int opcion, IClienteDAO clienteDAO){
        boolean salir = false;
        switch (opcion){
            case 1 ->{
                System.out.println("Listado de clientes: ");
                var clientes = clienteDAO.listarClientes();
                clientes.forEach(System.out::println);
            }
            case 2 -> {
                Integer id = 0;
                boolean entradaValida = false;
                System.out.println("--- Buscar Cliente ---");
                do {
                    try {
                        System.out.print("Introduce el ID a consultar: ");
                        id = Integer.parseInt(consola.nextLine());
                        if(id > 0){
                            entradaValida = true;
                        }else {
                            System.out.println("Error: Debe ingresar un valor mayor a 0");
                        }
                    }catch(NumberFormatException e){
                        System.out.println("Error: Debe ingresar un valor numérico válido: " + e.getMessage());
                    }
                }while (!entradaValida);

                var cliente = new Cliente(id,null,null,0);
                var clienteEncontrado = clienteDAO.buscarClientePorId(cliente);
                if(clienteEncontrado){
                    System.out.println("Cliente encontrado correctamente: " + cliente);
                }else {
                    System.out.println("Cliente no encontrado con el ID: " + id);
                }
            }
            case 3 ->{
                Integer id = 0;
                String nombre;
                String apellido;
                Integer membresia = 0;
                boolean entradaValida = false;
                System.out.println("--- Actualizar Cliente ---");
                do {
                    try {
                        System.out.print("Ingrese el Id del cliente a actualizar: ");
                        id = Integer.parseInt(consola.nextLine());
                        if(id > 0){
                            entradaValida = true;
                        }else {
                            System.out.println("Error: Debe ingresar un valor numérico válido");
                        }

                    }catch (NumberFormatException e) {
                        System.out.println("Error: Debe ingresar un valor numérico válido: " + e.getMessage());
                    }
                }while(!entradaValida);

                do{
                    System.out.print("Ingrese nombre a actualizar: ");
                    nombre = consola.nextLine();
                    if(nombre.isBlank() || !nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")){
                        System.out.println("Error: El nombre no puede estar vacío ni contener números o símbolos.");
                    }
                }while(nombre.isBlank() || !nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$"));

                do {
                    System.out.print("Ingrese el apellido a actualizar: ");
                    apellido = consola.nextLine();
                    if(apellido.isBlank() || !apellido.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")){
                        System.out.println("Error: El apellido no puede estar vacío ni contener números o símbolos.");
                    }
                }while(apellido.isBlank() || !apellido.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$"));

                do {
                    try {
                        System.out.print("Ingrese el valor de membresia a actualizar: ");
                        membresia = Integer.parseInt(consola.nextLine());
                        if(membresia > 0){
                            entradaValida = true;
                        }else {
                            System.out.println("Error: El número debe ser mayor a 0.");
                        }
                    }catch(NumberFormatException e){
                        System.out.println("Error: Debe ingresar un valor numérico válido: " + e.getMessage());
                    }
                }while(!entradaValida);
                var cliente = new Cliente(id,nombre,apellido,membresia);
                var clienteActualizado = clienteDAO.actualizarCliente(cliente);
                if(clienteActualizado){
                    System.out.println("Cliente actualizado correctamente...");
                }else{
                    System.out.println("Erro, Cliente no encontrado con el ID: " + id);
                }
            }
            case 4 ->{
                System.out.println("--- Agregar Nuevo Cliente ---");
                String nombre;
                String apellido;
                Integer membresia = 0;
                boolean entradaValida = false;

                do{
                    System.out.print("Ingrese el nuevo nombre: ");
                    nombre = consola.nextLine();
                    if(nombre.isBlank() || !nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")){
                        System.out.println("Error: El nombre no puede estar vacío ni contener números o símbolos.");
                    }
                }while(nombre.isBlank() || !nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$"));


                do {
                    System.out.print("Ingrese el nuevo apellido: ");
                    apellido = consola.nextLine();
                    if(apellido.isBlank() || !apellido.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")){
                        System.out.println("Error: El apellido no puede estar vacío ni contener números o símbolos.");
                    }
                }while(apellido.isBlank() || !apellido.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$"));

                do {
                    try {
                        System.out.print("Ingrese el nuevo valor de membresia: ");
                        membresia = Integer.parseInt(consola.nextLine());
                        if(membresia > 0){
                            entradaValida = true;
                        }else {
                            System.out.println("Error: El número debe ser mayor a 0.");
                        }
                    }catch(NumberFormatException e){
                        System.out.println("Error: Debe ingresar un valor numérico válido: " + e.getMessage());
                    }
                }while(!entradaValida);

                var cliente = new Cliente(null,nombre,apellido,membresia);
                var clienteNuevo = clienteDAO.agregarCliente(cliente);
                if(clienteNuevo){
                    System.out.println("Cliente agregado correctamente...");
                }else {
                    System.out.println("Error al agregar cliente");
                }
            }
            case 5 ->{
                Integer id = 0;
                boolean entradaValida = false;
                System.out.println("--- Eliminar Cliente ---");
                do {
                    try {
                        System.out.print("Ingrese el ID al cliente a eliminar");
                        id = Integer.parseInt(consola.nextLine());
                        if(id > 0){
                            entradaValida = true;
                        }else {
                            System.out.println("Error: El número debe ser mayor a 0.");
                        }
                    }catch (NumberFormatException e){
                        System.out.println("Error: Debe ingresar un valor numérico válido: " + e.getMessage());
                    }
                }while(!entradaValida);

                var cliente = new Cliente(id,null,null,0);
                var clienteEliminado = clienteDAO.eliminarCliente(cliente);
                if(clienteEliminado){
                    System.out.println("Cliente elimiando correctamente...");
                }else {
                    System.out.println("Cliente no encontrado con el ID: "+id);
                }
            }
            case 6 ->{
                System.out.println("¡Hasta pronto!");
                salir = true;
                break;
            }
            default -> {
                System.out.println("Opción no reconocida: " + opcion);
            }
        }
        return salir;
    }
}
