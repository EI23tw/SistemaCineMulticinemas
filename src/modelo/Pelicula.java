package modelo;

public class Pelicula {
    private String titulo;
    private String genero;
    private String horario;
    private int numeroSala;

    // Constructor
    public Pelicula(String titulo, String genero, String horario, int numeroSala) {
        this.titulo = titulo;
        this.genero = genero;
        this.horario = horario;
        this.numeroSala = numeroSala;
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

    // Método para mostrar la info de la película fácilmente
    @Override
    public String toString() {
        return titulo + " (" + genero + ") - " + horario + " [Sala " + numeroSala + "]";
    }
}