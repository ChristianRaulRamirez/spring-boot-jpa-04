package com.springdata.jpa.services.impl;

import com.springdata.jpa.entities.Editorial;
import com.springdata.jpa.repositories.EditorialRepository;
import com.springdata.jpa.services.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditorialServiceImpl implements EditorialService {

    @Autowired
    private EditorialRepository editorialRepository;

    @Override
    public Editorial guardarEditorial(Editorial editorial) {
        return editorialRepository.save(editorial);
    }

    @Override
    public Optional<Editorial> buscarPorId(Long id) {
        return editorialRepository.findById(id);
    }

    @Override
    public Optional<Editorial> buscarPorNombre(String nombre) {
        return editorialRepository.findByNombre(nombre);
    }

    @Override
    public List<Editorial> listarTodasLasEditoriales() {
        return editorialRepository.findAll();
    }

    @Override
    public Editorial actualizarEditorial(Editorial editorial) {
        return editorialRepository.save(editorial);
    }

    @Override
    public void eliminarEditorial(Long id) {
        editorialRepository.deleteById(id);
    }
}
