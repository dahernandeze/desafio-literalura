package com.alura.desafio_literalura.repository;

import com.alura.desafio_literalura.model.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    // Buscar autores por nombre
    @Query("SELECT a FROM Autor a WHERE LOWER(a.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Autor> findByNombreContainingIgnoreCase(@Param("nombre") String nombre);

    // Buscar autores vivos en un anio específico
    @Query("SELECT a FROM Autor a WHERE a.anioNacimiento <= :anio AND (a.anioFallecimiento IS NULL OR a.anioFallecimiento >= :anio)")
    List<Autor> findAutoresVivosEnAnio(@Param("anio") Integer anio);

    // Buscar autores nacidos en un rango de anios
    @Query("SELECT a FROM Autor a WHERE a.anioNacimiento BETWEEN :anioInicio AND :anioFin")
    List<Autor> findByAnioNacimientoBetween(@Param("anioInicio") Integer anioInicio, @Param("anioFin") Integer anioFin);

    // Obtener todos los autores ordenados por nombre
    List<Autor> findAllByOrderByNombreAsc();
}