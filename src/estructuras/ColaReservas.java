package estructuras;
import modelo.Reserva; // Importamos la clase Reserva que acabamos de crear
public class ColaReservas {
    // 1. Creamos una clase interna privada para representar los Nodos de la cola
    private class Nodo {
        Reserva reserva;
        Nodo siguiente;

        public Nodo(Reserva reserva) {
            this.reserva = reserva;
            this.siguiente = null;
        }
    }
    // Punteros para saber dónde empieza y dónde termina la cola
    private Nodo frente;
    private Nodo fin;
    private int tamano;

    // Constructor de la Cola vacía
    public ColaReservas() {
        this.frente = null;
        this.fin = null;
        this.tamano = 0;
    }

    // Método para ENCOLAR (Agregar una reserva al final de la cola)
    public void encolar(Reserva reserva) {
        Nodo nuevoNodo = new Nodo(reserva);
        if (estaVacia()) {
            frente = nuevoNodo; // Si está vacía, el nuevo es el primero
        } else {
            fin.siguiente = nuevoNodo; // Si hay gente, se pone detrás del último
        }
        fin = nuevoNodo; // Ahora el nuevo es el último
        tamano++;
    }

    // Método para DESENCOLAR (Atender a la primera reserva de la cola)
    public Reserva desencolar() {
        if (estaVacia()) {
            return null; // No hay a quién atender
        }
        Reserva reservaAtendida = frente.reserva;
        frente = frente.siguiente; // El frente ahora es el que seguía en la fila
        
        if (frente == null) {
            fin = null; // Si sacamos al único que había, la cola queda vacía
        }
        tamano--;
        return reservaAtendida;
    }

    // Método para verificar si la cola está vacía
    public boolean estaVacia() {
        return frente == null;
    }

    // Método para saber cuántas reservas hay en espera
    public int getTamano() {
        return tamano;
    }
}
