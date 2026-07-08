package estructuras;

import modelo.Asiento; // Traemos la clase Asiento de nuestro modelo

public class MatrizAsientos {
    // Declaramos una matriz bidimensional (arreglo de arreglos) de tipo Asiento
    private Asiento[][] asientos;
    private int filas;
    private int columnas;

    // Constructor: Al crear la matriz, le decimos de qué tamaño será la sala
    public MatrizAsientos(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.asientos = new Asiento[filas][columnas];
        inicializarAsientos();
    }

    // Método privado que llena la matriz con asientos nuevos (desocupados por defecto)
    private void inicializarAsientos() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                // Creamos un nuevo asiento dándole su coordenada de fila y columna
                asientos[i][j] = new Asiento(i, j);
            }
        }
    }

    // Método para obtener un asiento específico (para cuando el cliente quiera reservarlo)
    public Asiento getAsiento(int fila, int columna) {
        // Validamos que no nos pidan un asiento fuera de los límites de la sala
        if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
            return asientos[fila][columna];
        }
        return null; 
    }

    // Método útil para el reporte de ocupación: ¿Están todos los asientos vendidos?
    public boolean estaLlena() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (!asientos[i][j].isOcupado()) {
                    return false; // Si encuentra al menos uno libre, no está llena
                }
            }
        }
        return true; // Si terminó el ciclo y no encontró libres, está llena
    }

    // Getters
    public int getFilas() { return filas; }
    public int getColumnas() { return columnas; }
}