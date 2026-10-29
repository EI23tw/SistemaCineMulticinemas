# 🎬 SistemaCineMulticinemas

Este proyecto es una aplicación de escritorio desarrollada en **Java** utilizando el entorno de desarrollo NetBeans (con Ant). Fue creado como proyecto final para la asignatura de **Algoritmos y Estructuras de Datos**, demostrando el dominio de estructuras dinámicas y estáticas en memoria RAM (sin uso de bases de datos).

## 📋 Descripción del Proyecto

El sistema simula el funcionamiento interno de un cine, permitiendo a los usuarios visualizar la cartelera, reservar asientos y gestionar las ventas. La arquitectura del software está construida bajo el patrón **MVC (Modelo-Vista-Controlador)**, separando claramente la lógica matemática de la interfaz visual.

La información del sistema, como las películas, asientos y reservaciones, se procesa íntegramente en la memoria de la computadora, asegurando que las operaciones se ejecuten en tiempo real. 

## 🛠️ Tecnologías y Requisitos Técnicos

*   **Lenguaje:** Java.
*   **Interfaz Gráfica:** Java Swing.
*   **Herramienta de Construcción:** Ant.
*   **IDE Recomendado:** Apache NetBeans.
*   **Versión de Java:** JDK 17 o superior.
*   **Almacenamiento:** En memoria RAM (uso prohibido de Bases de Datos o archivos externos).

## 🏗️ Estructuras de Datos Implementadas

Para cumplir con las reglas del proyecto, el motor lógico del software se construyó desde cero integrando tres estructuras de datos principales:

*   **🌳 Árboles Binarios de Búsqueda (Trees):** Utilizados en la clase `ArbolPeliculas.java` para almacenar y organizar el catálogo de películas (por género, horario y sala). Esto permite realizar búsquedas y filtrados alfabéticos inmediatos en la cartelera mediante un recorrido In-Order.
*   **🧍‍♂️🧍‍♀️ Colas (Queues):** Implementadas a través de la clase `ColaReservas.java`. Aplica el concepto FIFO (First In, First Out) para gestionar las reservas generadas, manteniendo a los clientes en espera en la memoria RAM hasta que el "cajero" procesa los tickets y los atiende secuencialmente.
*   **🔲 Matrices (2D Arrays):** Encapsuladas en la clase `MatrizAsientos.java`, se emplean para modelar matemáticamente la distribución física (filas y columnas) de las salas de cine. 

## 🚀 Funcionalidades Principales

El sistema, a través de su menú de pestañas, ofrece las siguientes características:

1.  **Cartelera:** Visualización del catálogo de películas en una tabla dinámica (`JTable`), incluyendo un buscador para filtrar por nombre o género.
2.  **Comprar Entradas:** Un simulador visual de la sala de cine (`Grid Layout` de botones). Los asientos libres se muestran en verde y, al seleccionarlos y registrar un nombre de cliente (validado mediante Expresiones Regulares), cambian a color rojo (ocupado).
3.  **Confirmar / Cola:** Procesamiento de los clientes en espera. Incluye la funcionalidad nativa de Java para exportar o imprimir digitalmente el ticket/recibo (PDF) de cada reserva atendida.
4.  **Reportes:** Un sistema de auditoría que escanea las matrices en tiempo real y utiliza la clase `StringBuilder` para generar un desglose detallado de la ocupación (asientos vendidos y disponibles) por cada sala específica.

## 📦 Estructura de Paquetes (MVC)

El proyecto respeta el encapsulamiento orientado a objetos mediante la siguiente división:

*   `modelo`: Contiene las clases base (`Pelicula.java`, `Asiento.java`, `Reserva.java`).
*   `estructuras`: Contiene la lógica dura de los Árboles, Colas y Matrices.
*   `controlador`: La clase `ControladorCine.java`, el puente donde se instancian las estructuras, se definen los datos de prueba y se orquesta el comportamiento del negocio.
*   `vista`: Ventanas interactivas (`VentanaPrincipal.java`) diseñadas con los componentes de Java Swing.

---
*Desarrollado para el curso de Algoritmos y Estructuras de Datos.*
