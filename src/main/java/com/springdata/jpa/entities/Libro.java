package com.springdata.jpa.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@Entity
//@ToString
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "editorial_id")
    private Editorial editorial;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores = new ArrayList<>();

    @Override
    public String toString() {
        String autoresStr = autores.stream()
                .map(a -> a.getNombre()) // Aquí asumo que el nombre del autor se llama 'nombre', ajusta esto según tu estructura
                .collect(Collectors.joining(", "));

        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", categoria=" + ((categoria == null) ? null : categoria.getId()) +
                ", editorial=" + ((editorial == null) ? null : editorial.getId()) +
                ", autores=[" + autoresStr + "]" +
                '}';
    }
}
