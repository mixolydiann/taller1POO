package logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	//listas en paralelo xq no objetos allowed rip :<
	
	// Datos de Usuarios
	static String[] listaNombres = new String[10];
	static String[] listaPasswords = new String[10];
	static int cantUsuarios = 0;

	// Datos de Registros (Maximo 300 segun el enunciado)
	static String[] regUsuarios = new String[300];
	static String[] regFechas = new String[300];
	static int[] regHoras = new int[300];
	static String[] regActividades = new String[300];
	static int cantRegistros = 0;
	
	public static void main(String[] args) throws FileNotFoundException {
	    
		cargarUsuarios("usuarios.txt");
		cargarRegistros("Registros.txt");
		
		
	    Scanner sc = new Scanner(System.in); 
	    
	    int choice = 0;
	    String uid;
	    String pw;
	    String usuarioon;
	    
	    do {
	        System.out.println("1) Menu de Usuarios");
	        System.out.println("2) Menu de Analisis");
	        System.out.println("3) Salir");
	        
	        
	        while (!sc.hasNextInt()) {
	            System.out.println("Ingrese un valor numérico válido");
	            sc.next(); // Limpiar el error
	        }
	        
	        choice = sc.nextInt();
	        sc.nextLine(); 

	        if (choice < 1 || choice > 3) {
	            System.out.println("Ingrese un valor válido");
	        } else if (choice == 3) {
	            break;
	        }

	        switch (choice) {
	            case 1 -> {
	                System.out.print("Usuario: ");
	                uid = sc.nextLine(); 
	                
	                System.out.print("Contraseña: ");
	                pw = sc.nextLine();
	                
	                usuarioon = validarLogin(listaNombres,listaPasswords,uid,pw);
	                
	                if (usuarioon != null) {
	                	menuUsuarios(usuarioon);
	                }
	            }
	            case 2 -> {
	                // menu analisis
	            	MenuAnalisis.mostrarMenu(regUsuarios, regFechas, regHoras, regActividades, cantRegistros, listaNombres, cantUsuarios, sc);
	            }
	        }

	    } while (choice != 3);

	    System.out.println("Saliendo del programa...");
	    //sc.close(); 
	}
	
	
	private static void menuUsuarios(String currentuser) {
		Scanner menuU = new Scanner(System.in);
		int useropt = 0;
		
		System.out.println("Acceso correcto !");
		System.out.println(". . . . . . . . . . . .");
		System.out.println("Bienvenido " + currentuser + " !");
		System.out.println();
		
		do {
			System.out.println("Que deseas realizar ?");
			System.out.println();
			System.out.println("1) Registrar actividad.");
			System.out.println("2) Modificar actividad.");
			System.out.println("3) Eliminar actividad.");
			System.out.println("4) Cambiar contraseña.");
			System.out.println("5) Salir.");
			
			useropt = menuU.nextInt();
			
			if (useropt < 1 || useropt > 5) {
				System.out.println("Ingrese un valor valido. Reintentando . . .");
				System.out.println();
			}
			
		}while(useropt != 5);
		
		
		
		
		
	}

	public static void cargarUsuarios(String ruta) throws FileNotFoundException {
	    File archivo = new File(ruta);
	    Scanner sc = new Scanner(archivo);
	    
	    while (sc.hasNextLine() && cantUsuarios < listaNombres.length) {
	        String linea = sc.nextLine();
	        if (linea.trim().isEmpty()) {
	        	continue;
	        }
	        
	        String[] partes = linea.split(";");
	        listaNombres[cantUsuarios] = partes[0];   
	        listaPasswords[cantUsuarios] = partes[1]; 
	        cantUsuarios++;
	    }
	    sc.close();
	}
	
	public static void cargarRegistros(String ruta) throws FileNotFoundException {
	    File archivo = new File(ruta);
	    Scanner sc = new Scanner(archivo);
	    
	    while (sc.hasNextLine() && cantRegistros < 300) {
	        String linea = sc.nextLine();
	        if (linea.trim().isEmpty()) continue;
	        
	        String[] partes = linea.split(";");
	        // en paralelo todo
	        regUsuarios[cantRegistros] = partes[0];
	        regFechas[cantRegistros] = partes[1];
	        regHoras[cantRegistros] = Integer.parseInt(partes[2]);
	        regActividades[cantRegistros] = partes[3];
	        cantRegistros++;
	    }
	    sc.close();
	}
	
	public static String validarLogin(String[] nombres, String[] passwords, String userTyped, String passTyped) {
	    
	    // Recorremos los arreglos
	    for (int i = 0; i < 3; i++) {
	        
	        
	        if (nombres[i].equals(userTyped)) {
	            
	            
	            if (passwords[i].equals(passTyped)) {
	                
	                // para saber quién entro
	                return nombres[i];
	            }
	        }
	    }
	    
	    // credenciales incorrectas
	    return null;
	}
	
}