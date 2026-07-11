package modelo;

public class Asiento {
    //atributos
    private int fila;
    private int columna;
    private boolean ocupado;

    // Constructor
    public Asiento(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.ocupado = false; // Todo asiento nuevo nace libre por defecto
    }

    // Getters
    public int getFila() { return fila; }
    public int getColumna() { return columna; }
    public boolean isOcupado() { return ocupado; }

    // Setters
    public void setFila(int fila) { this.fila = fila; }
    public void setColumna(int columna) { this.columna = columna; }
    public void setOcupado(boolean ocupado) { this.ocupado = ocupado; }

    // Método para obtener el nombre visual del asiento (ej. A1, B3)
    public String getNombreAsiento() {
        // Convertimos el índice de la fila a una letra (0 -> A, 1 -> B, etc.)
        char letraFila = (char) ('A' + fila);
        // Sumamos 1 a la columna para que empiece en 1 y no en 0
        return letraFila + String.valueOf(columna + 1);
    }
}