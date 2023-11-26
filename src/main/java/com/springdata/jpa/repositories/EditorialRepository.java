package com.springdata.jpa.repositories;

import com.springdata.jpa.entities.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial,Long> {

    Optional<Editorial> findByNombre(String nombre);

}
