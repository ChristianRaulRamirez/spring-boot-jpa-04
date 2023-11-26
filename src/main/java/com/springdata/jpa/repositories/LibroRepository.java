package com.springdata.jpa.repositories;

import com.springdata.jpa.entities.Categoria;
import com.springdata.jpa.entities.Libro;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {

    Optional<Libro> findByTitulo(String titulo);

    List<Libro> findByCategoria(Categoria categoria);

}
