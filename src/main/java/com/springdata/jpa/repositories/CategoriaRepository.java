package com.springdata.jpa.repositories;

import com.springdata.jpa.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

    Optional<Categoria> findByNombre(String nombre);

}
