package com.springdata.jpa.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    /*
    orphanRemoval es un atributo específico del ORM que marca la entidad
    secundaria a eliminar cuando ya no se haga referencia a ella desde
    la entidad principal. Por defecto se encuentra desactivado,
    es decir, a false.
    **/
    @OneToMany(mappedBy = "categoria",cascade = CascadeType.ALL)
    private List<Libro> libros = new ArrayList<>();

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
