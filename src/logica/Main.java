package logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	//listas en paralelo xq no objetos allowed rip :<
	
	// Datos de Usuarios
	static String[] listaNombres = new String[3];
	static String[] listaPasswords = new String[3];
	static int cantUsuarios = 3;

	// Datos de Registros (Maximo 300 segun el enunciado)
	static String[] regUsuarios = new String[300];
	static String[] regFechas = new String[300];
	static int[] regHoras = new int[300];
	static String[] regActividades = new String[300];
	static int cantRegistros = 0;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		int choice = 0;
		String uid;
		String pw;
		
		do {
			
			System.out.println("1) Menu de Usuarios");
			System.out.println("2) Menu de Analisis");
			System.out.println("3) Salir");
			
			Scanner sc = new Scanner(System.in);
			
			choice = sc.nextInt();
			
			if (choice < 0 || choice > 3) {System.out.println("Ingrese un valor valido");System.out.println("");}
			
		}while (choice != 3);
		
		switch(choice) {
		case 1 -> {
			
			//menuUsuarios();
			
			break;
		}
		case 2 -> {
			
			//menuAnalisis();
			
		}
		}
		
		cargarUsuarios("usuarios.txt");
		cargarRegistros("Registro.txt");
		
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
	        //cantUsuarios++; wait
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
	
	public static String validarLogin(String[] nombres, String[] passwords, int cantUsuarios, String userTyped, String passTyped) {
	    
	    // Recorremos los arreglos
	    for (int i = 0; i < cantUsuarios; i++) {
	        
	        
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
