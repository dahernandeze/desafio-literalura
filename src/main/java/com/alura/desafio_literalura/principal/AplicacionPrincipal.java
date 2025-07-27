package com.alura.desafio_literalura.principal;

import com.alura.desafio_literalura.model.entity.Autor;
import com.alura.desafio_literalura.model.entity.Libro;
import com.alura.desafio_literalura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class AplicacionPrincipal {

    @Autowired
    private LibroService libroService;

    private Scanner scanner = new Scanner(System.in);

    public void ejecutar() {
        mostrarBienvenida();

        int opcion = 0;
        while (opcion != 8) {
            mostrarMenu();
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                procesarOpcion(opcion);
            } catch (NumberFormatException e) {
                System.out.println("❌ Por favor ingrese un número válido.");
            }
        }

        System.out.println("👋 ¡Gracias por usar LiterAlura!");
        scanner.close();
    }

    private void mostrarBienvenida() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("📚 BIENVENIDO AL CATÁLOGO DE LIBROS DE GUTENDEX 📚");
        System.out.println("=".repeat(60));
        System.out.println("Sistema para buscar, guardar y consultar libros");
        System.out.println("=".repeat(60) + "\n");
    }

    private void mostrarMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("🔖 MENÚ PRINCIPAL");
        System.out.println("=".repeat(50));
        System.out.println("1. 🔍 Buscar libro por título (API → BD)");
        System.out.println("2. 📋 Listar libros guardados");
        System.out.println("3. 👤 Buscar libros por autor");
        System.out.println("4. 🌍 Buscar libros por idioma");
        System.out.println("5. 🏆 Top 10 libros más descargados");
        System.out.println("6. 📊 Estadísticas por idioma");
        System.out.println("7. 👥 Buscar autores vivos en un anio");
        System.out.println("8. 🚪 Salir");
        System.out.println("=".repeat(50));
        System.out.print("Seleccione una opción (1-8): ");
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                buscarLibroPorTitulo();
                break;
            case 2:
                listarLibrosGuardados();
                break;
            case 3:
                buscarLibrosPorAutor();
                break;
            case 4:
                buscarLibrosPorIdioma();
                break;
            case 5:
                mostrarTop10Libros();
                break;
            case 6:
                mostrarEstadisticasPorIdioma();
                break;
            case 7:
                buscarAutoresVivosEnAnio();
                break;
            case 8:
                System.out.println("🔄 Cerrando aplicación...");
                break;
            default:
                System.out.println("❌ Opción no válida. Por favor seleccione entre 1 y 8.");
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("🔍 BUSCAR LIBRO POR TÍTULO");
        System.out.println("=".repeat(40));
        System.out.print("Ingrese el título del libro: ");
        String titulo = scanner.nextLine().trim();

        if (titulo.isEmpty()) {
            System.out.println("❌ El título no puede estar vacío.");
            return;
        }

        System.out.println("🔄 Buscando en la API de Gutendex...");
        List<Libro> libros = libroService.buscarYGuardarLibro(titulo);

        if (libros.isEmpty()) {
            System.out.println("❌ No se encontraron libros con ese título.");
        } else {
            System.out.println("✅ Se encontraron " + libros.size() + " libro(s):");
            mostrarListaLibros(libros);
        }
    }

    private void listarLibrosGuardados() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("📋 LIBROS GUARDADOS EN LA BASE DE DATOS");
        System.out.println("=".repeat(40));

        List<Libro> libros = libroService.obtenerTodosLosLibros();

        if (libros.isEmpty()) {
            System.out.println("❌ No hay libros guardados en la base de datos.");
            System.out.println("💡 Usa la opción 1 para buscar y guardar libros.");
        } else {
            System.out.println("📚 Total de libros: " + libros.size());
            mostrarListaLibros(libros);
        }
    }

    private void buscarLibrosPorAutor() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("👤 BUSCAR LIBROS POR AUTOR");
        System.out.println("=".repeat(40));
        System.out.print("Ingrese el nombre del autor: ");
        String autor = scanner.nextLine().trim();

        if (autor.isEmpty()) {
            System.out.println("❌ El nombre del autor no puede estar vacío.");
            return;
        }

        List<Libro> libros = libroService.buscarLibrosPorAutor(autor);

        if (libros.isEmpty()) {
            System.out.println("❌ No se encontraron libros de ese autor en la base de datos.");
        } else {
            System.out.println("✅ Se encontraron " + libros.size() + " libro(s) del autor:");
            mostrarListaLibros(libros);
        }
    }

    private void buscarLibrosPorIdioma() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("🌍 BUSCAR LIBROS POR IDIOMA");
        System.out.println("=".repeat(40));
        System.out.println("Idiomas comunes: en (inglés), es (espaniol), fr (francés), de (alemán), pt (portugués)");
        System.out.print("Ingrese el código del idioma: ");
        String idioma = scanner.nextLine().trim().toLowerCase();

        if (idioma.isEmpty()) {
            System.out.println("❌ El idioma no puede estar vacío.");
            return;
        }

        List<Libro> libros = libroService.buscarLibrosPorIdioma(idioma);

        if (libros.isEmpty()) {
            System.out.println("❌ No se encontraron libros en ese idioma.");
        } else {
            System.out.println("✅ Se encontraron " + libros.size() + " libro(s) en " + idioma + ":");
            mostrarListaLibros(libros);
        }
    }

    private void mostrarTop10Libros() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("🏆 TOP 10 LIBROS MÁS DESCARGADOS");
        System.out.println("=".repeat(40));

        List<Libro> topLibros = libroService.obtenerTop10Libros();

        if (topLibros.isEmpty()) {
            System.out.println("❌ No hay libros en la base de datos.");
        } else {
            System.out.println("📊 Ranking de libros más descargados:");
            for (int i = 0; i < topLibros.size(); i++) {
                Libro libro = topLibros.get(i);
                System.out.println(String.format("🥇 %d. %s",
                        (i + 1),
                        libro.getTitulo()));
                System.out.println(String.format("   👤 Autor: %s", libro.getPrimerAutor()));
                System.out.println(String.format("   📥 Descargas: %,d", libro.getNumeroDescargas()));
                System.out.println();
            }
        }
    }

    private void mostrarEstadisticasPorIdioma() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("📊 ESTADÍSTICAS POR IDIOMA");
        System.out.println("=".repeat(40));

        List<Object[]> estadisticas = libroService.obtenerEstadisticasPorIdioma();

        if (estadisticas.isEmpty()) {
            System.out.println("❌ No hay datos estadísticos disponibles.");
        } else {
            System.out.println("📈 Distribución de libros por idioma:");
            for (Object[] stat : estadisticas) {
                String idioma = (String) stat[0];
                Long cantidad = (Long) stat[1];
                System.out.println(String.format("🌍 %s: %d libro(s)", idioma, cantidad));
            }
        }
    }

    private void buscarAutoresVivosEnAnio() {
        System.out.println("\n" + "=".repeat(40));
        System.out.println("👥 BUSCAR AUTORES VIVOS EN UN AniO");
        System.out.println("=".repeat(40));
        System.out.print("Ingrese el anio: ");

        try {
            int anio = Integer.parseInt(scanner.nextLine().trim());

            if (anio < 1 || anio > 2024) {
                System.out.println("❌ Por favor ingrese un anio válido.");
                return;
            }

            List<Autor> autores = libroService.buscarAutoresVivosEnAnio(anio);

            if (autores.isEmpty()) {
                System.out.println("❌ No se encontraron autores vivos en el anio " + anio + ".");
            } else {
                System.out.println("✅ Autores vivos en " + anio + " (" + autores.size() + " encontrados):");
                for (Autor autor : autores) {
                    System.out.println(String.format("👤 %s (%d - %s)",
                            autor.getNombre(),
                            autor.getAnioNacimiento(),
                            autor.getAnioFallecimiento() != null ? autor.getAnioFallecimiento().toString() : "presente"));
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Por favor ingrese un anio válido.");
        }
    }

    private void mostrarListaLibros(List<Libro> libros) {
        System.out.println();
        for (int i = 0; i < libros.size() && i < 10; i++) { // Limitar a 10 resultados
            Libro libro = libros.get(i);
            System.out.println("📖 " + (i + 1) + ". " + libro.getTitulo());
            System.out.println("   👤 Autor: " + libro.getPrimerAutor());
            System.out.println("   🌍 Idioma: " + libro.getPrimerIdioma());
            if (libro.getNumeroDescargas() != null) {
                System.out.println("   📥 Descargas: " + String.format("%,d", libro.getNumeroDescargas()));
            }
            System.out.println();
        }

        if (libros.size() > 10) {
            System.out.println("... y " + (libros.size() - 10) + " libro(s) más.");
        }
    }
}
