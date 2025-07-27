package com.alura.desafio_literalura.service;

import com.alura.desafio_literalura.model.dto.GutendexResponse;
import com.alura.desafio_literalura.model.dto.LibroDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

import java.util.Collections;
import java.util.List;

@Service
public class GutendexService {

    private final RestTemplate restTemplate;
    private static final String BASE_URL = "https://gutendex.com/books";

    public GutendexService() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Busca libros por título en la API de Gutendex
     */
    public List<LibroDto> buscarLibrosPorTitulo(String titulo) {
        try {
            String url = BASE_URL + "?search=" + titulo.replace(" ", "+");
            System.out.println("Consultando URL: " + url);

            GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);

            if (response != null && response.getResults() != null) {
                System.out.println("Encontrados " + response.getResults().size() + " libros");
                return response.getResults();
            }

            return Collections.emptyList();

        } catch (RestClientException e) {
            System.err.println("Error al consultar la API de Gutendex: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * Busca libros por autor en la API de Gutendex
     */
    public List<LibroDto> buscarLibrosPorAutor(String autor) {
        try {
            String url = BASE_URL + "?search=" + autor.replace(" ", "%20");
            System.out.println("Consultando URL: " + url);

            GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);

            if (response != null && response.getResults() != null) {
                // Filtrar resultados que realmente contengan el autor en el nombre
                List<LibroDto> librosFiltrados = response.getResults().stream()
                        .filter(libro -> libro.getAuthors() != null &&
                                libro.getAuthors().stream()
                                        .anyMatch(a -> a.getName().toLowerCase().contains(autor.toLowerCase())))
                        .toList();

                System.out.println("Encontrados " + librosFiltrados.size() + " libros del autor");
                return librosFiltrados;
            }

            return Collections.emptyList();

        } catch (RestClientException e) {
            System.err.println("Error al consultar la API de Gutendex: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * Obtiene libros por idioma
     */
    public List<LibroDto> buscarLibrosPorIdioma(String idioma) {
        try {
            String url = BASE_URL + "?languages=" + idioma;
            System.out.println("Consultando URL: " + url);

            GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);

            if (response != null && response.getResults() != null) {
                System.out.println("Encontrados " + response.getResults().size() + " libros en " + idioma);
                return response.getResults();
            }

            return Collections.emptyList();

        } catch (RestClientException e) {
            System.err.println("Error al consultar la API de Gutendex: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * Obtiene los libros más populares (sin parámetros específicos)
     */
    public List<LibroDto> obtenerLibrosPopulares() {
        try {
            String url = BASE_URL + "?sort=popular";
            System.out.println("Consultando libros populares: " + url);

            GutendexResponse response = restTemplate.getForObject(url, GutendexResponse.class);

            if (response != null && response.getResults() != null) {
                System.out.println("Encontrados " + response.getResults().size() + " libros populares");
                // Limitar a los primeros 20 resultados
                return response.getResults().stream().limit(20).toList();
            }

            return Collections.emptyList();

        } catch (RestClientException e) {
            System.err.println("Error al consultar la API de Gutendex: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}