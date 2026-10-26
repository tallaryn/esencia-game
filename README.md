# Requisitos

- JDK 11 
- IDE recomendado: Eclipse

# Instrucciones de instalación y ejecución
## 1 - Instalación.

### Opción A. Clonar el repositorio.

- Abre tu terminal o consola Git y ejecuta:

```bash

git clone https://github.com/tallaryn/esencia-game/

```

> *Nota: El proyecto se clonará en la carpeta actual de tu terminal. Si quieres un acceso fácil (por ejemplo, en el Escritorio), ejecuta `cd Desktop` antes de clonar.*

### Opción B. Descargar el archivo ZIP

- En la página principal de este repositorio en GitHub, haz click en Code.
- Selecciona Download ZIP.
- Extrae el archivo .zip tu carpeta de preferencia.

## 2 - Ejecución.
### Primer paso: Importar el proyecto a Eclipse

1. Abre Eclipse IDE.
2. Ve al menú superior: `File` -> `Import`.
3. En la pantalla `Select`, abre la carpeta `Gradle` y selecciona `Existing Gradle Project`. Haz click en `Next >` para proceder a la siguiente pantalla.
4. En la pantalla `Import Gradle Project`, inserte el directorio raíz de la carpeta `esencia-game-master` encontrada en el interior de la carpeta descargada. Haz click en `Next >` para proceder a la siguiente pantalla.
5. En la pantalla `Import Options`, selecciona `Confgure Workspace Settings` en la parte superior derecha. Bajo la sección `Gradle Distribution`, Selecciona `Specific Gradle version` y asigne la versión `7.3.3` Bajo la sección `Advanced Options`, en `Java home`, navega e inserte la carpeta de JDK11. Haz click en `Apply and Close` y en `Next >` para proceder a la siguiente pantalla.
6. Luego que la barra de carga termine de cargar la carpeta del juego, haz click en `Finish` para terminar de exportar.
    
### Segundo paso: Configurar la versión de Java (JDK 11)

1. En el panel izquierdo `Workplace`, se deberían divisar tres carpetas: `esencia game-core`, `esencia game-desktop` y `esencia game-master`. Haz click izquierdo en la carpeta `esencia game-core` y selecciona `Properties->Java build Path`.
2. Bajo la sección `Libraries`, en `Modulepath` haz doble click a `JRE System Library` y dentro de `Execution Environment` seleccione `JavaSE-11`. Selecciona `Finish`, luego `Apply and Close`.
3. Realiza el mismo procedimiento para `esencia game-desktop`.

## 3 - Ejecución

Haz click en la flecha apuntando hacia abajo a la derecha del botón verde de Play (Run Project) en la barra de herramientas superior, luego seleccione `Run Configurations` y seleccione la configuración como `Java Application`.
    
En caso de no contar con una configuración para ejecutar, bajo `Java application` presione el primer botón del rectángulo blanco con el signo + en la barra superior izquierda para crear una nueva configuración.
1. En la sección `Main`, en `Project`, selecciona la carpeta `esencia game-desktop`. Luego, en `Main class`, selecciona la clase `DesktopLauncher`. Haz click en el botón `Apply`.
2. Presiona el botón `Run` dentro de `Run Configurations.` Una vez realzado, se podrá ejecutar presionando el botón verde.

Se abrirá una ventana emergente ejecutando el juego.
