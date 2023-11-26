package com.springdata.jpa.services;

import com.springdata.jpa.entities.Categoria;
import com.springdata.jpa.entities.Libro;

import java.util.List;
import java.util.Optional;

public interface LibroService {

    Libro saveLibro(Libro libro);

    Optional<Libro> buscarPorId(Long id);

    Optional<Libro> buscarPorTitulo(String titulo);

    List<Libro> listarTodosLosLibros();

    Libro actualizarLibro(Libro libro);

    void eliminarLibro(Long id);

    List<Libro> buscarPorCategoria(Categoria categoria);
}
