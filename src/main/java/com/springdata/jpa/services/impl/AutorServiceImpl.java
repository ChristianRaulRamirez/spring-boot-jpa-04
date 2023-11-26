package com.springdata.jpa.services.impl;

import com.springdata.jpa.entities.Autor;
import com.springdata.jpa.entities.Libro;
import com.springdata.jpa.repositories.AutorRepository;
import com.springdata.jpa.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorServiceImpl implements AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public Autor guardarAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    @Override
    public Optional<Autor> buscarPorId(Long id) {
        return autorRepository.findById(id);
    }

    @Override
    public Optional<Autor> buscarPorNombre(String nombre) {
        return autorRepository.findByNombre(nombre);
    }

    @Override
    public List<Autor> listarTodosLosAutores() {
        return autorRepository.findAll();
    }

    @Override
    public Autor actualizarAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    @Override
    public void eliminarAutor(Long id) throws ClassNotFoundException {
        Optional<Autor> optionalAutor = autorRepository.findById(id);

        if (optionalAutor.isPresent()) {
            Autor autor = optionalAutor.get();
            eliminarRelacionesDeAutor(autor);
            autorRepository.deleteById(id);
        } else {
            throw new ClassNotFoundException("Error");
        }
    }

    @Override
    public List<Autor> buscarPorIds(List<Long> ids) {
        return autorRepository.findAllById(ids);
    }

    private void eliminarRelacionesDeAutor(Autor autor) {
        for (Libro libro : autor.getLibros()) {
            libro.getAutores().remove(autor);
        }
        autor.getLibros().clear();
    }
}
