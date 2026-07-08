package estructuras;

import modelo.Pelicula;
import java.util.ArrayList;
import java.util.List;

public class ArbolPeliculas {

    // 1. Clase interna para el Nodo del Árbol
    private class Nodo {
        Pelicula pelicula;
        Nodo izquierdo;
        Nodo derecho;

        public Nodo(Pelicula pelicula) {
            this.pelicula = pelicula;
            this.izquierdo = null;
            this.derecho = null;
        }
    }

    private Nodo raiz;

    // Constructor del Árbol vacío
    public ArbolPeliculas() {
        this.raiz = null;
    }

    // 2. Método principal para insertar una nueva película
    public void insertar(Pelicula pelicula) {
        raiz = insertarRecursivo(raiz, pelicula);
    }

    // 3. Lógica recursiva de inserción (Orden alfabético por título)
    private Nodo insertarRecursivo(Nodo actual, Pelicula pelicula) {
        // Si llegamos a un espacio vacío, aquí va el nuevo nodo
        if (actual == null) {
            return new Nodo(pelicula);
        }

        // Comparamos los títulos para decidir si va a la rama izquierda o derecha
        int comparacion = pelicula.getTitulo().compareToIgnoreCase(actual.pelicula.getTitulo());
        
        if (comparacion < 0) {
            // Es alfabéticamente menor, va a la izquierda
            actual.izquierdo = insertarRecursivo(actual.izquierdo, pelicula);
        } else {
            // Es alfabéticamente mayor (o igual), va a la derecha
            actual.derecho = insertarRecursivo(actual.derecho, pelicula);
        }
        
        return actual;
    }

    // 4. Método para obtener todas las películas ordenadas (Recorrido In-Order)
    public List<Pelicula> obtenerPeliculasOrdenadas() {
        List<Pelicula> lista = new ArrayList<>();
        recorridoInOrder(raiz, lista);
        return lista;
    }

    // Lógica recursiva para recorrer el árbol de Izquierda -> Raíz -> Derecha
    private void recorridoInOrder(Nodo nodo, List<Pelicula> lista) {
        if (nodo != null) {
            recorridoInOrder(nodo.izquierdo, lista);
            lista.add(nodo.pelicula); // Visitamos el nodo central
            recorridoInOrder(nodo.derecho, lista);
        }
    }
}