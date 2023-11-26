    package com.springdata.jpa.services.impl;

    import com.springdata.jpa.entities.Categoria;
    import com.springdata.jpa.entities.Libro;
    import com.springdata.jpa.repositories.LibroRepository;
    import com.springdata.jpa.services.LibroService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Optional;

    @Service
    public class LibroServiceImpl implements LibroService {

        @Autowired
        private LibroRepository libroRepository;

        @Override
        public Libro saveLibro(Libro libro) {
            return libroRepository.save(libro);
        }

        @Override
        public Optional<Libro> buscarPorId(Long id) {
            return libroRepository.findById(id);
        }

        @Override
        public Optional<Libro> buscarPorTitulo(String titulo) {
            return libroRepository.findByTitulo(titulo);
        }

        @Override
        public List<Libro> listarTodosLosLibros() {
            return libroRepository.findAll();
        }

        @Override
        public Libro actualizarLibro(Libro libro) {
            return libroRepository.save(libro);
        }

        @Override
        public void eliminarLibro(Long id) {
            libroRepository.deleteById(id);
        }

        @Override
        public List<Libro> buscarPorCategoria(Categoria categoria) {
            return libroRepository.findByCategoria(categoria);
        }
    }
