// alumno 1: vicente guerra
// rut 1: 21.855.415-6

// alumno 2: luis molina
// rut 2: 21.564.225-9

package logica;

import java.util.Scanner;

public class MenuAnalisis {

    // menu principal de analisis
    public static void mostrarMenu(
            String[] regUsuario,
            String[] regFecha,
            int[] regHoras,
            String[] regActividad,
            int totalReg,
            String[] usuarios,
            int totalUsuarios,
            Scanner scanner) {

        int opcion;

        do {
            System.out.println("====================================");
            System.out.println("   Bienvenido al menu de analisis!");
            System.out.println("====================================");
            System.out.println("Que deseas realizar?");
            System.out.println("");
            System.out.println("1) Actividad más realizada");
            System.out.println("2) Actividad más realizada por cada usuario");
            System.out.println("3) Usuario con mayor procrastinacion");
            System.out.println("4) Ver todas las actividades");
            System.out.println("5) Salir");
            System.out.print("> ");

            opcion = leerOpcionEntera(scanner);

            if (opcion == 1) {
                actividadMasRealizada(regActividad, regHoras, totalReg);
            } else if (opcion == 2) {
                actividadMasRealizadaPorUsuario(regUsuario, regActividad, regHoras, totalReg, usuarios, totalUsuarios);
            } else if (opcion == 3) {
                usuarioConMayorProcrastinacion(regUsuario, regHoras, totalReg, usuarios, totalUsuarios);
            } else if (opcion == 4) {
                verTodasLasActividades(regUsuario, regFecha, regHoras, regActividad, totalReg);
            } else if (opcion == 5) {
                System.out.println("Volviendo al menu principal...");
            } else {
                System.out.println("[!] Opcion invalida. Ingrese un numero entre 1 y 5.");
            }

            System.out.println("");

        } while (opcion != 5);
    }

    // opcion 1: muestra la actividad con mas horas en total (todos los usuarios)
    private static void actividadMasRealizada(String[] regActividad, int[] regHoras, int totalReg) {
        if (totalReg == 0) {
            System.out.println("[!] No hay registros disponibles.");
            return;
        }

        String actividadTop = "";
        int horasMax = 0;

        for (int i = 0; i < totalReg; i++) {
            // sumar todas las horas de esta actividad
            int horasActividad = 0;
            for (int j = 0; j < totalReg; j++) {
                // aca va el tolowercase y equals para comparar los textos de forma mas segura
                if (regActividad[j].toLowerCase().equals(regActividad[i].toLowerCase())) {
                    horasActividad += regHoras[j];
                }
            }

            if (horasActividad > horasMax) {
                horasMax = horasActividad;
                actividadTop = regActividad[i];
            }
        }

        System.out.println("Actividad mas realizada (global):");
        System.out.println("----------------------------------");
        System.out.println("* " + actividadTop + " -> con " + horasMax + " horas registradas en total");
    }

    // opcion 2: actividad con mas horas para cada usuario
    private static void actividadMasRealizadaPorUsuario(
            String[] regUsuario,
            String[] regActividad,
            int[] regHoras,
            int totalReg,
            String[] usuarios,
            int totalUsuarios) {

        System.out.println("Actividades mas realizadas por cada usuario:");
        System.out.println("--------------------------------------------");

        for (int u = 0; u < totalUsuarios; u++) {
            String nombreUsuario = usuarios[u];
            String actividadTop = "";
            int horasMax = 0;

            for (int i = 0; i < totalReg; i++) {
                if (!regUsuario[i].toLowerCase().equals(nombreUsuario.toLowerCase())) {
                    continue;
                }

                // sumar horas de esta actividad para este usuario especifico
                int horasActividad = 0;
                for (int j = 0; j < totalReg; j++) {
                    if (regUsuario[j].toLowerCase().equals(nombreUsuario.toLowerCase())
                            && regActividad[j].toLowerCase().equals(regActividad[i].toLowerCase())) {
                        horasActividad += regHoras[j];
                    }
                }

                if (horasActividad > horasMax) {
                    horasMax = horasActividad;
                    actividadTop = regActividad[i];
                }
            }

            if (horasMax == 0) {
                System.out.println("* " + nombreUsuario + " -> Sin actividades registradas");
            } else {
                System.out.println("* " + nombreUsuario + " -> " + actividadTop + " -> con " + horasMax + " horas registradas");
            }
        }
    }

    // opcion 3: mostrar el usuario con mas horas acumuladas en total
    private static void usuarioConMayorProcrastinacion(
            String[] regUsuario,
            int[] regHoras,
            int totalReg,
            String[] usuarios,
            int totalUsuarios) {

        if (totalUsuarios == 0) {
            System.out.println("[!] No hay usuarios registrados.");
            return;
        }

        String usuarioTop = "";
        int horasMax = 0;

        for (int u = 0; u < totalUsuarios; u++) {
            int horasUsuario = 0;

            for (int i = 0; i < totalReg; i++) {
                if (regUsuario[i].toLowerCase().equals(usuarios[u].toLowerCase())) {
                    horasUsuario += regHoras[i];
                }
            }

            if (horasUsuario > horasMax) {
                horasMax = horasUsuario;
                usuarioTop = usuarios[u];
            }
        }

        System.out.println("Usuario con mayor procrastinacion:");
        System.out.println("----------------------------------");
        if (horasMax == 0) {
            System.out.println("* Ningun usuario tiene actividades registradas.");
        } else {
            System.out.println("* " + usuarioTop + " -> con " + horasMax + " horas registradas en total");
        }
    }

    // opcion 4: par mostrar todos los registros disponibles
    private static void verTodasLasActividades(
            String[] regUsuario,
            String[] regFecha,
            int[] regHoras,
            String[] regActividad,
            int totalReg) {

        if (totalReg == 0) {
            System.out.println("[!] No hay actividades registradas.");
            return;
        }

        System.out.println("Todas las actividades registradas:");
        System.out.println("-----------------------------------");

        for (int i = 0; i < totalReg; i++) {
            System.out.println((i + 1) + ") " + regUsuario[i]
                    + " | " + regFecha[i]
                    + " | " + regHoras[i] + " hora(s)"
                    + " | " + regActividad[i]);
        }

        System.out.println("-----------------------------------");
        System.out.println("Total: " + totalReg + " actividad(es)");
    }

    // intenta leer un entero
    // retorna -1 si la entrada se rompe o no es numero
    private static int leerOpcionEntera(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}