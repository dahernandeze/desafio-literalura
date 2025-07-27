package com.alura.desafio_literalura.model.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    private Long id;

    @Column(nullable = false, length = 1000)
    private String titulo;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autores;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "libro_idiomas", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "idioma")
    private List<String> idiomas;

    @Column(name = "numero_descargas")
    private Integer numeroDescargas;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "libro_temas", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "tema", length = 500)
    private List<String> temas;

    // Constructores
    public Libro() {}

    public Libro(Long id, String titulo, List<String> idiomas, Integer numeroDescargas) {
        this.id = id;
        this.titulo = titulo;
        this.idiomas = idiomas;
        this.numeroDescargas = numeroDescargas;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
        if (autores != null) {
            autores.forEach(autor -> autor.setLibro(this));
        }
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Integer numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public List<String> getTemas() {
        return temas;
    }

    public void setTemas(List<String> temas) {
        this.temas = temas;
    }

    // Métodos auxiliares
    public String getPrimerAutor() {
        if (autores != null && !autores.isEmpty()) {
            return autores.get(0).getNombre();
        }
        return "Autor desconocido";
    }

    public String getPrimerIdioma() {
        if (idiomas != null && !idiomas.isEmpty()) {
            return idiomas.get(0);
        }
        return "Idioma desconocido";
    }

    @Override
    public String toString() {
        return "========== LIBRO ==========\n" +
                "ID: " + id + "\n" +
                "Título: " + titulo + "\n" +
                "Autor: " + getPrimerAutor() + "\n" +
                "Idiomas: " + idiomas + "\n" +
                "Número de descargas: " + numeroDescargas + "\n" +
                "============================\n";
    }
}