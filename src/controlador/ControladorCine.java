package controlador;

import estructuras.ArbolPeliculas;
import estructuras.ColaReservas;
import modelo.Pelicula;
import modelo.Reserva;
import java.util.List;

public class ControladorCine {
    
    // Instanciamos nuestras 3 estructuras de datos obligatorias
    private ArbolPeliculas catalogoPeliculas;
    private ColaReservas colaCompras;

    public ControladorCine() {
        this.catalogoPeliculas = new ArbolPeliculas();
        this.colaCompras = new ColaReservas();
        cargarDatosDePrueba();
    }

    // Como no podemos usar bases de datos, cargamos datos en memoria al iniciar
    private void cargarDatosDePrueba() {
        // ============================================================
        // PROGRAMACIÓN: SALA 1 (Horarios: 15:40 | 18:00 | 20:20)
        // ============================================================
        catalogoPeliculas.insertar(new Pelicula("Shrek", "Comedia", "15:40", 1));
        catalogoPeliculas.insertar(new Pelicula("Avengers", "Acción", "18:00", 1));
        catalogoPeliculas.insertar(new Pelicula("El Padrino", "Drama", "20:20", 1));
        // PROGRAMACIÓN: SALA 2 (Horarios: 16:15 | 19:40 | 22:00)
        catalogoPeliculas.insertar(new Pelicula("El Laberinto del Fauno", "Fantasía", "16:15", 2));
        catalogoPeliculas.insertar(new Pelicula("El Caballero de la Noche", "Acción", "19:40", 2));
        catalogoPeliculas.insertar(new Pelicula("Interstellar", "Ciencia Ficción", "22:00", 2));
        // PROGRAMACIÓN: SALA 3 (Horarios: 15:40 | 19:40 | 22:40)
        catalogoPeliculas.insertar(new Pelicula("Titanic", "Romance", "15:40", 3));
        catalogoPeliculas.insertar(new Pelicula("Alien", "Terror", "19:40", 3));
        catalogoPeliculas.insertar(new Pelicula("Manchester Frente al Mar", "Drama", "22:40", 3));
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

    // --- FUNCIONALIDAD 2: Reservar asientos (Encola reserva) ---
    public void registrarReserva(String cliente, Pelicula p, String asientos, double total) {
        Reserva nuevaReserva = new Reserva(cliente, p, asientos, total);
        colaCompras.encolar(nuevaReserva); // Mandamos el ticket a la cola
    }

    // --- FUNCIONALIDAD 3: Confirmar compra (Desencola) ---
    public Reserva procesarSiguienteCompra() {
        return colaCompras.desencolar(); // Atendemos al primero en la fila
    }

    // --- FUNCIONALIDAD 4: Generar reporte de ocupación mejorado ---
    public String generarReporteOcupacion(int numeroSala) {
        int ocupadosTotal = 0;
        int disponiblesTotal = 0;
        
        StringBuilder reporte = new StringBuilder();
        reporte.append("=== REPORTE SALA ").append(numeroSala).append(" ===\n\n");

        // Recorremos el árbol de películas
        for (Pelicula p : catalogoPeliculas.obtenerPeliculasOrdenadas()) {
            
            // Filtramos solo las películas que se proyectan en esta sala
            if (p.getNumeroSala() == numeroSala) {
                estructuras.MatrizAsientos m = p.getSalaAsientos();
                int ocupados = 0;
                int totalAsientos = m.getFilas() * m.getColumnas();
                
                for (int i = 0; i < m.getFilas(); i++) {
                    for (int j = 0; j < m.getColumnas(); j++) {
                        if (m.getAsiento(i, j).isOcupado()) ocupados++;
                    }
                }
                
                int disponibles = totalAsientos - ocupados;
                ocupadosTotal += ocupados;
                disponiblesTotal += disponibles;
                
                reporte.append("- ").append(p.getTitulo()).append(" (").append(p.getHorario()).append(")\n");
                reporte.append("  Vendidos: ").append(ocupados).append(" | Libres: ").append(disponibles).append("\n");
            }
        }
        
        reporte.append("\n===========================\n");
        reporte.append("TOTAL VENDIDOS EN LA SALA: ").append(ocupadosTotal).append("\n");
        reporte.append("TOTAL LIBRES EN LA SALA: ").append(disponiblesTotal).append("\n");
        
        return reporte.toString();
    }
}