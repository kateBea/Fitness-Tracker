# Fitness Tracker

Trabajo de fin de grado de aplicación para gestionar el entrenamiento físico.

## Capturas cliente web

![img1](capturas/web-img1.png)

## Capturas cliente Android

A continuación se muestran algunas capturas del cliente android.

<p align="center">
    <img src="/capturas/android-img1.png" alt="Pantalla 1" width="250"/>
    <img src="/capturas/android-img2.png" alt="Pantalla 2" width="250"/>
</p>

## Breve introducción

La gente hoy en día está muy interesada en mantener un estado de salud bueno, realizando ejercicios físicos ya sea en el gimnasio o en casa. Creemos que construir herramientas que permitan a un usuario cualquiera tener constancia de su estado físico es algo esencial, aún más si esta herramienta es de fácil acceso con un uso intuitivo.

Este proyecto es también una forma de ampliar nuestros conocimientos en librerías que no hemos explorado lo suficientemente a fondo durante el curso académico y creemos que por nuestra cuenta podríamos avanzar bastante más.

## Estructura

El proyecto consta de tres bloques principales. Un cliente Android, un cliente web y un backend. El backend sigue una arquitectura similar a microservicios y también hace de puente entre nuestros clientes y APIs externas que sean necesarios para nuestros clientes como por ejemplo, el módulo de inteligencia artificial para generar dietas o el módulo de información nutricional.

## Cliente Android

#### Funcionalidades Principales:

1. Registrar usuarios a la base de datos
2. Registrar rutinas y información recibida de Wear OS.

#### Ejecución (depurar):

1. Seleccionamos y abrimos la carpeta de android-client medienta el editor Android Studio.
2. El editor debería ser capaza de actualizar los paquetes de Gradle si es necesario, si no probar con el proyecto en la rama master.
3. Hacer clic sobre Debug para ejecutar la aplicación.

- Detalles editor Android Studio usado:

Android Studio Hedgehog | 2023.1.1
Build #AI-231.9392.1.2311.11076708, built on November 9, 2023

Runtime version: 17.0.7+0-b2043.56-10550314 amd6
VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.

## Cliente Web

- Funcionalidades Principales:

1. Iniciar sesión.
2. Consultar dietas e información nutricional.
3. Acceso a asitenta por inteligencia artificial.
4. Generación de dietas a medida.

- Ejecución:

1. Acceder a la carpeta web-client y ejecutamos los comandos:

```shell
npm i
npm run dev
```

Para acceder a las rutas vamos contra ``localhost:5173/`` que es donde está ejecuntándose la aplicación.

## REST Base

## Funcionalidades Principales

1. **Iniciar sesión**
    - Permite a los usuarios autenticarse en el sistema mediante su correo electrónico y contraseña.
    - Endpoint: `POST /login`

2. **Registrar un nuevo usuario**
    - Permite registrar un nuevo usuario en el sistema.
    - Endpoint: `POST /insertar`

3. **Consultar dietas e información nutricional**
    - Permite a los usuarios obtener información detallada de sus dietas y planes nutricionales.
    - Endpoints:
      - `POST /getdietausuario`
      - `POST /getlistdietasusuario`
      - `POST /getalimentos`

4. **Gestión de dietas**
    - Registrar una nueva dieta.
    - Modificar una dieta existente.
    - Consultar una dieta específica.
    - Endpoints:
      - `POST /registrardieta`
      - `PUT /modificardieta`
      - `POST /getdietausuario`

5. **Gestión de rutinas**
    - Registrar una nueva rutina.
    - Modificar una rutina existente.
    - Consultar una rutina específica.
    - Endpoints:
      - `POST /registrarrutina`
      - `PUT /modificarrutina`
      - `POST /getrutina`

6. **Gestión de usuarios**
    - Consultar información de usuario.
    - Modificar datos de usuario.
    - Endpoints:
      - `GET /info/{email}`
      - `PUT /modificardatosusuario`

## Ejecución

La API se puede ejecutar tanto con Eclipse como con IntelliJ IDEA (Ultimate, Education o Community). A continuación, se detallan los pasos para configurar y ejecutar el proyecto.

### Requisitos Previos

- Java 17 o superior
- Maven 3.6.3 o superior
- MongoDB instalado y en ejecución
- Tener instalado Eclipse o IntellliJ IDEA

Abrir la carpeta del proyecto con IDE y ejecutar.

Para acceder a la documentación de la API simplemente hay que iniciarla y acceder al enlace ``http://localhost:8080/swagger-ui/``<br>
Para desplegar la documentación se ha degradado la versión de Spring Boot a 2.6x, ya que versiones iguales o superiores a la 3
no son, en el tiempo de redacción de este artículo, compatibles con SpringFox. Detalles en [este post](https://stackoverflow.com/questions/71549614/springfox-type-javax-servlet-http-httpservletrequest-not-present).

## REST External Services

Esta API REST proporciona, entre otros servicios, una interfaz para integrar la funcionalidad de generación de texto de ChatGPT en aplicaciones externas. Utiliza endpoints específicos para enviar solicitudes a ChatGPT y recibir respuestas generadas por el modelo de lenguaje. La integración de esta API permite a los aprovechar el potencial de ChatGPT para integrar el asistente virtual que ofrece nuestro sistema.

- Funcionalidades Principales:

1. Lanzamiento de Prompts: Permite enviar prompts o estímulos de entrada a ChatGPT para generar respuestas de texto coherentes y contextualmente relevantes.
2. Control de Parámetros: Proporciona opciones para ajustar parámetros como la longitud máxima de la respuesta.
3. Integración Sencilla: Ofrece una interfaz intuitiva y documentada para facilitar la integración con sistemas externos, incluyendo ejemplos de solicitud y respuesta.

Este modulo forma parte del external services donde podemos enconrtrar otros servicios como el de información nutricional.

## Ejecución

1. Abrir el proyecto con Visual Studio (Para el desarollo se utilizó VS 2022 en su versión 17.9.5).
2. Vamos a Debug, y Start Debugging, de manera alternativa

### Requisitos Previos

- .NET 8.0 SDK
- .NET 8.0 Runtime
- Herramientas de desarrollo con ASP Dotnet
- Tener instalado Visual Studio 2022
