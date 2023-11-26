package com.springdata.jpa.services;

import com.springdata.jpa.entities.Editorial;

import java.util.List;
import java.util.Optional;

public interface EditorialService {

    Editorial guardarEditorial(Editorial editorial);

    Optional<Editorial> buscarPorId(Long id);

    Optional<Editorial> buscarPorNombre(String nombre);

    List<Editorial> listarTodasLasEditoriales();

    Editorial actualizarEditorial(Editorial editorial);

    void eliminarEditorial(Long id);
}
