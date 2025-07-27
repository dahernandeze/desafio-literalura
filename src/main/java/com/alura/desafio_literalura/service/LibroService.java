package com.alura.desafio_literalura.service;

import com.alura.desafio_literalura.model.dto.AutorDto;
import com.alura.desafio_literalura.model.dto.LibroDto;
import com.alura.desafio_literalura.model.entity.Autor;
import com.alura.desafio_literalura.model.entity.Libro;
import com.alura.desafio_literalura.repository.AutorRepository;
import com.alura.desafio_literalura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private GutendexService gutendexService;

    /**
     * Busca un libro en la API y lo guarda en la base de datos
     */
    public List<Libro> buscarYGuardarLibro(String titulo) {
        System.out.println("Buscando libro: " + titulo);

        // Buscar en la API
        List<LibroDto> librosDto = gutendexService.buscarLibrosPorTitulo(titulo);

        if (librosDto.isEmpty()) {
            System.out.println("No se encontraron libros con el título: " + titulo);
            return new ArrayList<>();
        }

        List<Libro> librosGuardados = new ArrayList<>();

        for (LibroDto libroDto : librosDto) {
            // Verificar si el libro ya existe en la base de datos
            if (!libroRepository.existsById(libroDto.getId())) {
                Libro libro = convertirDtoAEntidad(libroDto);
                Libro libroGuardado = libroRepository.save(libro);
                librosGuardados.add(libroGuardado);
                System.out.println("Libro guardado: " + libroGuardado.getTitulo());
            } else {
                System.out.println("El libro ya existe en la base de datos: " + libroDto.getTitle());
                // Obtener el libro existente y agregarlo a la lista
                Optional<Libro> libroExistente = libroRepository.findById(libroDto.getId());
                libroExistente.ifPresent(librosGuardados::add);
            }
        }

        return librosGuardados;
    }

    /**
     * Convierte un LibroDto a una entidad Libro
     */
    private Libro convertirDtoAEntidad(LibroDto libroDto) {
        Libro libro = new Libro();
        libro.setId(libroDto.getId());
        libro.setTitulo(libroDto.getTitle());
        libro.setIdiomas(libroDto.getLanguages());
        libro.setNumeroDescargas(libroDto.getDownloadCount());
        libro.setTemas(libroDto.getSubjects());

        // Convertir autores
        if (libroDto.getAuthors() != null) {
            List<Autor> autores = new ArrayList<>();
            for (AutorDto autorDto : libroDto.getAuthors()) {
                Autor autor = new Autor();
                autor.setNombre(autorDto.getName());
                autor.setAnioNacimiento(autorDto.getBirthYear());
                autor.setAnioFallecimiento(autorDto.getDeathYear());
                autores.add(autor);
            }
            libro.setAutores(autores);
        }

        return libro;
    }

    /**
     * Obtiene todos los libros de la base de datos
     */
    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    /**
     * Busca libros por título en la base de datos
     */
    public List<Libro> buscarLibrosPorTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    /**
     * Busca libros por autor en la base de datos
     */
    public List<Libro> buscarLibrosPorAutor(String autor) {
        return libroRepository.findByAutorNombreContainingIgnoreCase(autor);
    }

    /**
     * Busca libros por idioma en la base de datos
     */
    public List<Libro> buscarLibrosPorIdioma(String idioma) {
        return libroRepository.findByIdioma(idioma);
    }

    /**
     * Obtiene los 10 libros más descargados
     */
    public List<Libro> obtenerTop10Libros() {
        return libroRepository.findTop10ByOrderByNumeroDescargasDesc();
    }

    /**
     * Obtiene estadísticas de libros por idioma
     */
    public List<Object[]> obtenerEstadisticasPorIdioma() {
        return libroRepository.countLibrosByIdioma();
    }

    /**
     * Busca libros por tema
     */
    public List<Libro> buscarLibrosPorTema(String tema) {
        return libroRepository.findByTemaContainingIgnoreCase(tema);
    }

    /**
     * Obtiene un libro por ID
     */
    public Optional<Libro> obtenerLibroPorId(Long id) {
        return libroRepository.findById(id);
    }

    /**
     * Busca autores vivos en un anio específico
     */
    public List<Autor> buscarAutoresVivosEnAnio(Integer anio) {
        return autorRepository.findAutoresVivosEnAnio(anio);
    }

    /**
     * Obtiene todos los autores ordenados por nombre
     */
    public List<Autor> obtenerTodosLosAutores() {
        return autorRepository.findAllByOrderByNombreAsc();
    }
}