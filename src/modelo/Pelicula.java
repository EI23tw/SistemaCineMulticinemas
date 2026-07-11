package modelo;

public class Pelicula {
    //Atributos
    private String titulo;
    private String genero;
    private String horario;
    private int numeroSala;
    private estructuras.MatrizAsientos salaAsientos;

// Constructor
    public Pelicula(String titulo, String genero, String horario, int numeroSala) {
        this.titulo = titulo;
        this.genero = genero;
        this.horario = horario;
        this.numeroSala = numeroSala;
        // Inicializamos la matriz única para esta función
        this.salaAsientos = new estructuras.MatrizAsientos(5, 5);
    }
    
    // Getters
    public String getTitulo() { return titulo; }
    public String getGenero() { return genero; }
    public String getHorario() { return horario; }
    public int getNumeroSala() { return numeroSala; }

    // Setters
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setGenero(String genero) { this.genero = genero; }
    public void setHorario(String horario) { this.horario = horario; }
    public void setNumeroSala(int numeroSala) { this.numeroSala = numeroSala; }
    
    // NUEVO: Getter para que la ventana pueda pedir los asientos de ESTA película
    public estructuras.MatrizAsientos getSalaAsientos() {
        return salaAsientos;
    }

    // Método para mostrar la info de la película fácilmente
    @Override
    public String toString() {
        return titulo + " (" + genero + ") - " + horario + " [Sala " + numeroSala + "]";
    }
}