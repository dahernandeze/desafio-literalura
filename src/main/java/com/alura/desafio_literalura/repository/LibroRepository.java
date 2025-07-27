package com.alura.desafio_literalura.repository;

import com.alura.desafio_literalura.model.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Buscar libros por título (case insensitive)
    @Query("SELECT l FROM Libro l WHERE LOWER(l.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))")
    List<Libro> findByTituloContainingIgnoreCase(@Param("titulo") String titulo);

    // Buscar libros por idioma
    @Query("SELECT l FROM Libro l JOIN l.idiomas i WHERE i = :idioma")
    List<Libro> findByIdioma(@Param("idioma") String idioma);

    // Buscar libros por autor
    @Query("SELECT l FROM Libro l JOIN l.autores a WHERE LOWER(a.nombre) LIKE LOWER(CONCAT('%', :autor, '%'))")
    List<Libro> findByAutorNombreContainingIgnoreCase(@Param("autor") String autor);

    // Obtener los 10 libros más descargados
    List<Libro> findTop10ByOrderByNumeroDescargasDesc();

    // Buscar libros por tema
    @Query("SELECT l FROM Libro l JOIN l.temas t WHERE LOWER(t) LIKE LOWER(CONCAT('%', :tema, '%'))")
    List<Libro> findByTemaContainingIgnoreCase(@Param("tema") String tema);

    // Contar libros por idioma
    @Query("SELECT i, COUNT(l) FROM Libro l JOIN l.idiomas i GROUP BY i ORDER BY COUNT(l) DESC")
    List<Object[]> countLibrosByIdioma();

    // Verificar si existe un libro por ID
    boolean existsById(Long id);
}