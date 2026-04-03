taller1POO
Repositorio para el taller N°1 de POO UCN

---

## Contexto
Martin, Catalina y Estefania llevan registro del tiempo que gastan procrastinando.
La app que usaban se cayó, entonces hay que hacer una de 0.
El programa lee los registros que quedaron guardados, agregar nuevos, modificarlos, eliminarlos y ver estadísticas de quién procrastina más.

---

## Integrantes
| Nombre | RUT | GitHub |
| Luis Molina | 21.564.225-9 | [@mixolydiann](https://github.com/mixolydiann) |
| Vicente Guerra Veliz | 21.855.415-6 | [@nemura0](https://github.com/nemura0) |

---

## Estructura del proyecto

```
TallerPOO/
├── src/
│   └── logica/
│       ├── Main.java          → menú principal, login, menú de usuarios, carga de archivos
│       └── MenuAnalisis.java  → menú de análisis y estadísticas ## aca de casualidad puse los ruts igual xd pero bueno ya está pusheado
├── usuarios.txt
├── Registros.txt
└── README.md
```

---

## Archivos de datos

`usuarios.txt`
```
Nombre;Contraseña
```

`Registros.txt`
```
Usuario;Fecha;Horas;Actividad
```

---

## Cómo ejecutarlo

1. Clonar el repo
2. Abrir en Eclipse como proyecto Java existente
3. Poner `usuarios.txt` y `Registros.txt` en la raíz del proyecto (donde está el `src/`)
4. Correr `Main.java`

---

## Qué se puede hacer con él

**Menú Usuarios** (requiere login)
- Registrar actividad
- Modificar actividad (fecha, duración o tipo)
- Eliminar actividad
- Cambiar contraseña
- Todo se guarda en los archivos

**Menú Análisis** (sin login)
- Actividad más realizada en general
- Actividad más realizada por cada usuario
- Usuario con más horas procrastinadas
- Ver todos los registros
