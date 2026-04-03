package logica;
/*
Luis Molina / 21.564.225-9 / mixolydiann
Vicente Guerra / 21.855.415-6 / nemura0
*/
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	//listas en paralelo xq no objetos allowed
	
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
	
	public static void main(String[] args) throws IOException {
	    
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
	                
	            }
	        }

	    } while (choice != 3);

	    System.out.println("Saliendo del programa...");
	    sc.close();
	}
	
	// Funcion para el menu de usuariosxsx
	private static void menuUsuarios(String currentuser) throws IOException {
		Scanner menuU = new Scanner(System.in);
		int useropt = 0;
		int choice2 = 0;
		
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
			
			// Por si nuestro brillante usuario pone una letra
		    while (!menuU.hasNextInt()) { 
		        System.out.println("Error Debes ingresar un valor valido. Reintentando . . .");
		        System.out.print("Intenta de nuevo: ");
		        menuU.next(); // limpiar el scanner
		    }
			
			useropt = menuU.nextInt();
			menuU.nextLine();
			
			
			if (useropt < 1 || useropt > 5) {
				System.out.println("Ingrese un valor valido. Reintentando . . .");
				System.out.println();
			}
			
			switch(useropt) {
			
			case 1 -> {
				
				if (cantRegistros >= 300) {
					System.out.println("Error la cantidad de registros supera el limite (300)");
				}
				else { System.out.println("--- Registrar actividad ---");
				
				regUsuarios[cantRegistros] = currentuser;
				
				System.out.println("Ingrese la fecha del registro: ");
				System.out.println("formato (DD/MM/AAAA)");
				regFechas[cantRegistros] = menuU.next();
				
				System.out.println("Ingrese horas procrastinadas: ");
				while(!menuU.hasNextInt()) {
					System.out.println("Error: ingrese un numero entero");
					menuU.next();
				}
				
				regHoras[cantRegistros] = menuU.nextInt();
				menuU.nextLine();
				
				System.out.println("Ingrese el tipo de actividad: ");
				regActividades[cantRegistros] = menuU.nextLine();
				
				cantRegistros++;
				
				fileEdit("Registros.txt");
				
				
				} 
				
				
			}
			case 2 -> {
			    System.out.println("¿Qué actividad deseas modificar?");
			    System.out.println();
			    int[] mapeoIndices = new int[301];
			    int contadorOpciones = 1;
			    
			    for (int i = 0; i < cantRegistros; i++) {
			        if (regUsuarios[i].equals(currentuser)) {
			            System.out.println(contadorOpciones + ") " + regFechas[i] + " ; " + regHoras[i] + " hrs ; " + regActividades[i]);
			            mapeoIndices[contadorOpciones] = i;
			            contadorOpciones++;
			        }
			    }
			    
			    if (contadorOpciones == 1) {
			        System.out.println("No tienes actividades registradas.");
			    } else {
			        System.out.print("Seleccione el numero de actividad a modificar (0 para cancelar): ");
			        
			        while (!menuU.hasNextInt()) {
			            System.out.println("Error: ingrese un valor valido");
			            menuU.next();
			        }
			        
			        int seleccion = menuU.nextInt();
			        menuU.nextLine();
			        
			        if (seleccion > 0 && seleccion < contadorOpciones) {
			            int indiceReal = mapeoIndices[seleccion];
			            
			            do {
			                System.out.println("Modificando: " + regFechas[indiceReal] + " - " + regActividades[indiceReal]);
			                System.out.println("¿Qué deseas modificar?");
			                System.out.println("0) Regresar");
			                System.out.println("1) Fecha");
			                System.out.println("2) Duración");
			                System.out.println("3) Tipo de actividad");
			                
			                while (!menuU.hasNextInt()) {
			                    System.out.println("Error: ingrese un valor valido");
			                    menuU.next();
			                }
			                choice2 = menuU.nextInt();
			                menuU.nextLine(); 
			                
			                if (choice2 == 1) {
			                    System.out.print("Ingrese nueva fecha (DD/MM/AAAA): ");
			                    regFechas[indiceReal] = menuU.next();
			                    menuU.nextLine();
			                } else if (choice2 == 2) {
			                    System.out.print("Ingrese nueva duración (horas): ");
			                    while (!menuU.hasNextInt()) {
			                        System.out.println("Error: ingrese un numero entero");
			                        menuU.next();
			                    }
			                    regHoras[indiceReal] = menuU.nextInt();
			                    menuU.nextLine();
			                } else if (choice2 == 3) {
			                    System.out.print("Ingrese nuevo tipo de actividad: ");
			                    regActividades[indiceReal] = menuU.nextLine();
			                } else if (choice2 < 0 || choice2 > 3) {
			                    System.out.println("Error: opción no válida");
			                }
			                
			                if (choice2 > 0 && choice2 <= 3) {
			                    System.out.println("¡Dato actualizado en memoria!");
			                }

			            } while (choice2 != 0);
			            
			            
			            fileEdit("Registros.txt");
			            System.out.println("Cambios guardados permanentemente.");
			            
			        }
			    }
			}
			case 3 -> {
			    System.out.println("--- Eliminar Actividad ---");
			    
			    // Arreglo temporal para encontrar el index "real" del q elija el user
			    int[] mapeoIndices = new int[301]; 
			    int contadorOpciones = 1;

			    // Mostrar las actividades del usuario logueado (reemplazo de la otra funcion)
			    for (int i = 0; i < cantRegistros; i++) {
			        if (regUsuarios[i].equals(currentuser)) {
			            System.out.println(contadorOpciones + ") " + regFechas[i] + " ; " + regHoras[i] + " hrs ; " + regActividades[i]);
			            mapeoIndices[contadorOpciones] = i; // Guardamos la ubicación real en el arreglo de 300
			            contadorOpciones++;
			        }
			    }

			    if (contadorOpciones == 1) {
			        System.out.println("No tienes actividades registradas.");
			    } else {
			        System.out.print("Seleccione el numero de actividad a eliminar (0 para cancelar): ");
			        int seleccion = menuU.nextInt();
			        menuU.nextLine();

			        if (seleccion > 0 && seleccion < contadorOpciones) {
			            int indiceReal = mapeoIndices[seleccion];

			            // Logica para eliminar del arreglo
			            for (int j = indiceReal; j < cantRegistros - 1; j++) {
			                regUsuarios[j] = regUsuarios[j + 1];
			                regFechas[j] = regFechas[j + 1];
			                regHoras[j] = regHoras[j + 1];
			                regActividades[j] = regActividades[j + 1];
			            }
			            cantRegistros--; // Reducimos el contador	

			            // Actualizar el archivus
			            fileEdit("Registros.txt");
			            System.out.println("¡Actividad eliminada y archivo actualizado!");
			        }
			    }
			}
			case 4 -> {
			    System.out.println("--- Cambiar Contraseña ---");
			    System.out.print("Ingrese su nueva contraseña: ");
			    String nuevaPass = menuU.next(); // Leemos la nueva pass
			    
			    // Buscamos el index del usuario
			    for (int i = 0; i < cantUsuarios; i++) {
			        if (listaNombres[i].equals(currentuser)) {
			            // Actualizamos la contraseña en el indice q corresponde al usuario
			            listaPasswords[i] = nuevaPass;
			            break; 
			        }
			    }
			    
			    // Actualizar archivo
			    guardarUsuarios("usuarios.txt");
			}
			
			}
			
			
		}while(useropt != 5);

		
	}

	private static void guardarUsuarios(String ruta) {
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))){
			for (int i = 0; i<cantUsuarios;i++) {
				bw.write(listaNombres[i] + ";" + listaPasswords[i]);
				bw.newLine();
			}
			System.out.println("Archivo Usuarios.txt Actualizado");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al actualizar Usuarios.txt");;
		}
		
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
	
	public static void fileEdit(String ruta) throws IOException {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))){
			for (int i = 0; i < cantRegistros;i++) {
				bw.write(regUsuarios[i]+ ";" + regFechas[i]+ ";" + regHoras[i]+ ";" + regActividades[i]);
				bw.newLine();
				}
			} catch (java.io.IOException e) {
				System.out.println("Error al escribir en el archivo");
			}
			
		}
	}

