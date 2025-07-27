package com.alura.desafio_literalura.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LibroDto {


    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("authors")
    private List<AutorDto> authors;

    @JsonProperty("translators")
    private List<AutorDto> translators;

    @JsonProperty("subjects")
    private List<String> subjects;

    @JsonProperty("bookshelves")
    private List<String> bookshelves;

    @JsonProperty("languages")
    private List<String> languages;

    @JsonProperty("copyright")
    private Boolean copyright;

    @JsonProperty("media_type")
    private String mediaType;

    @JsonProperty("formats")
    private Map<String, String> formats;

    @JsonProperty("download_count")
    private Integer downloadCount;

    // Constructores
    public LibroDto() {}

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AutorDto> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AutorDto> authors) {
        this.authors = authors;
    }

    public List<AutorDto> getTranslators() {
        return translators;
    }

    public void setTranslators(List<AutorDto> translators) {
        this.translators = translators;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public List<String> getBookshelves() {
        return bookshelves;
    }

    public void setBookshelves(List<String> bookshelves) {
        this.bookshelves = bookshelves;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Boolean getCopyright() {
        return copyright;
    }

    public void setCopyright(Boolean copyright) {
        this.copyright = copyright;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Map<String, String> getFormats() {
        return formats;
    }

    public void setFormats(Map<String, String> formats) {
        this.formats = formats;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    // Método auxiliar para obtener el primer autor
    public String getPrimerAutor() {
        if (authors != null && !authors.isEmpty()) {
            return authors.get(0).getName();
        }
        return "Autor desconocido";
    }

    // Método auxiliar para obtener el primer idioma
    public String getPrimerIdioma() {
        if (languages != null && !languages.isEmpty()) {
            return languages.get(0);
        }
        return "Idioma desconocido";
    }

    @Override
    public String toString() {
        return "LibroDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authors=" + authors +
                ", languages=" + languages +
                ", downloadCount=" + downloadCount +
                '}';
    }
}
