package modelo;

public class Reserva {
    // Variable estática para generar IDs únicos automáticamente
    private static int contadorId = 1; 
    
    private int idReserva;
    private String nombreCliente;
    private Pelicula pelicula;
    private String asientosReservados; // Guardaremos los nombres visuales, ej: "A1, A2"
    private double totalPagar;

    // Constructor
    public Reserva(String nombreCliente, Pelicula pelicula, String asientosReservados, double totalPagar) {
        this.idReserva = contadorId++; // Asigna el ID actual y luego lo incrementa para el siguiente
        this.nombreCliente = nombreCliente;
        this.pelicula = pelicula;
        this.asientosReservados = asientosReservados;
        this.totalPagar = totalPagar;
    }

    // Getters
    public int getIdReserva() { return idReserva; }
    public String getNombreCliente() { return nombreCliente; }
    public Pelicula getPelicula() { return pelicula; }
    public String getAsientosReservados() { return asientosReservados; }
    public double getTotalPagar() { return totalPagar; }

    // Setters
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public void setPelicula(Pelicula pelicula) { this.pelicula = pelicula; }
    public void setAsientosReservados(String asientosReservados) { this.asientosReservados = asientosReservados; }
    public void setTotalPagar(double totalPagar) { this.totalPagar = totalPagar; }

    // Método para imprimir el ticket en la consola o en la interfaz gráfica
    @Override
    public String toString() {
        return "Ticket #" + idReserva + " | Cliente: " + nombreCliente + 
               " | Película: " + pelicula.getTitulo() + 
               " | Asientos: " + asientosReservados + 
               " | Total: $" + totalPagar;
    }
}