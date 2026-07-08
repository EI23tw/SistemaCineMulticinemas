package controlador;

import estructuras.ArbolPeliculas;
import estructuras.ColaReservas;
import estructuras.MatrizAsientos;
import modelo.Pelicula;
import modelo.Reserva;
import java.util.List;

public class ControladorCine {
    
    // Instanciamos nuestras 3 estructuras de datos obligatorias
    private ArbolPeliculas catalogoPeliculas;
    private ColaReservas colaCompras;
    // Un arreglo de matrices para manejar múltiples salas (ej. 3 salas en el cine)
    private MatrizAsientos[] salas;

    public ControladorCine() {
        this.catalogoPeliculas = new ArbolPeliculas();
        this.colaCompras = new ColaReservas();
        this.salas = new MatrizAsientos[3]; 

        // Inicializamos las matrices de asientos (ej. salas pequeñas de 5 filas x 5 columnas)
        for (int i = 0; i < 3; i++) {
            salas[i] = new MatrizAsientos(5, 5);
        }

        cargarDatosDePrueba();
    }

    // Como no podemos usar bases de datos, cargamos datos en memoria al iniciar
    private void cargarDatosDePrueba() {
        catalogoPeliculas.insertar(new Pelicula("Avengers", "Acción", "18:00", 1));
        catalogoPeliculas.insertar(new Pelicula("Interstellar", "Ciencia Ficción", "20:00", 2));
        catalogoPeliculas.insertar(new Pelicula("Shrek", "Comedia", "16:00", 3));
    }

    // --- FUNCIONALIDAD 1: Consultar películas disponibles ---
    public List<Pelicula> obtenerCartelera() {
        return catalogoPeliculas.obtenerPeliculasOrdenadas();
    }
// --- FUNCIONALIDAD 1.1: Buscar películas por género, horario o sala ---
    public java.util.List<Pelicula> buscarPeliculas(String criterio) {
        java.util.List<Pelicula> todas = obtenerCartelera(); // Sacamos todas del árbol
        java.util.List<Pelicula> filtradas = new java.util.ArrayList<>();
        
        String textoBuscado = criterio.toLowerCase().trim();

        for (Pelicula p : todas) {
            // Convertimos la sala (que es int) a texto para poder compararla
            String salaTexto = String.valueOf(p.getNumeroSala());
            
            // Si el texto escrito coincide con el género, el horario o la sala, lo agregamos
            if (p.getGenero().toLowerCase().contains(textoBuscado) || 
                p.getHorario().toLowerCase().contains(textoBuscado) || 
                salaTexto.equals(textoBuscado)) {
                
                filtradas.add(p);
            }
        }
        return filtradas;
    }

    // Método de apoyo para obtener los asientos de una película/sala
    public MatrizAsientos getSala(int numeroSala) {
        // Restamos 1 porque los arreglos en Java empiezan en 0, pero las salas en 1
        return salas[numeroSala - 1];
    }

    // --- FUNCIONALIDAD 2: Reservar asientos (Encola reserva) ---
    public void registrarReserva(String cliente, Pelicula p, String asientos, double total) {
        Reserva nuevaReserva = new Reserva(cliente, p, asientos, total);
        colaCompras.encolar(nuevaReserva); // Mandamos el ticket a la cola
    }

    // --- FUNCIONALIDAD 3: Confirmar compra (Desencola) ---
    public Reserva procesarSiguienteCompra() {
        return colaCompras.desencolar(); // Atendemos al primero en la fila
    }

    // --- FUNCIONALIDAD 4: Generar reporte de ocupación ---
    public String generarReporteOcupacion(int numeroSala) {
        MatrizAsientos sala = getSala(numeroSala);
        int totalAsientos = sala.getFilas() * sala.getColumnas();
        int ocupados = 0;

        for (int i = 0; i < sala.getFilas(); i++) {
            for (int j = 0; j < sala.getColumnas(); j++) {
                if (sala.getAsiento(i, j).isOcupado()) {
                    ocupados++;
                }
            }
        }
        
        int disponibles = totalAsientos - ocupados;
        return "Estadísticas Sala " + numeroSala + ":\n" +
               "Asientos Vendidos: " + ocupados + "\n" +
               "Asientos Disponibles: " + disponibles;
    }
}