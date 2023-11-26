package com.springdata.jpa.controller;

import com.springdata.jpa.entities.Autor;
import com.springdata.jpa.entities.Categoria;
import com.springdata.jpa.entities.Editorial;
import com.springdata.jpa.entities.Libro;
import com.springdata.jpa.services.AutorService;
import com.springdata.jpa.services.CategoriaService;
import com.springdata.jpa.services.EditorialService;
import com.springdata.jpa.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @Autowired
    private EditorialService editorialService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private AutorService autorService;

    @GetMapping({"/listar","/"})
    public String listarLibros(Model model) {
        List<Libro> libros = libroService.listarTodosLosLibros();
        model.addAttribute("libros", libros);
        return "libro/listar_libros"; // Nombre de la vista (listar_libros.html)
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoLibro(Model model) {
        Libro libro = new Libro();
        model.addAttribute("libro", libro);
        model.addAttribute("editoriales", editorialService.listarTodasLasEditoriales());
        model.addAttribute("categorias", categoriaService.listarTodasLasCategorias());
        model.addAttribute("autores", autorService.listarTodosLosAutores());
        return "libro/formulario_libro"; // Nombre de la vista para el formulario (formulario_libro.html)
    }

    @PostMapping("/guardar")
    public String guardarLibro(@ModelAttribute Libro libro, @RequestParam("editorialId") Long editorialId,
                               @RequestParam("categoriaId") Long categoriaId, @RequestParam("autoresIds") List<Long> autoresIds) {
        // Obtener y asignar la editorial y la categoría al libro
        Optional<Editorial> editorial = editorialService.buscarPorId(editorialId);
        editorial.ifPresent(libro::setEditorial);

        Optional<Categoria> categoria = categoriaService.buscarPorId(categoriaId);
        categoria.ifPresent(libro::setCategoria);

        // Obtener y asignar los autores al libro
        List<Autor> autores = autorService.buscarPorIds(autoresIds);
        libro.setAutores(new ArrayList<>(autores));

        libroService.saveLibro(libro);
        return "redirect:/libros/listar"; // Redirige a la lista de libros
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditarLibro(@PathVariable Long id, Model model) {
        Optional<Libro> libro = libroService.buscarPorId(id);
        if (libro.isPresent()) {
            model.addAttribute("libro", libro.get());
            model.addAttribute("editoriales", editorialService.listarTodasLasEditoriales());
            model.addAttribute("categorias", categoriaService.listarTodasLasCategorias());
            model.addAttribute("autores", autorService.listarTodosLosAutores());
        }
        return "libro/formulario_libro"; // Nombre de la vista para el formulario de edición (formulario_libro.html)
    }

    @PostMapping("/{id}/actualizar")
    public String actualizarLibro(@PathVariable Long id, @ModelAttribute Libro libro,
                                  @RequestParam("editorialId") Long editorialId,
                                  @RequestParam("categoriaId") Long categoriaId,
                                  @RequestParam("autoresIds") List<Long> autoresIds) {
        // Obtener y asignar la editorial y la categoría al libro
        Optional<Editorial> editorial = editorialService.buscarPorId(editorialId);
        editorial.ifPresent(libro::setEditorial);

        Optional<Categoria> categoria = categoriaService.buscarPorId(categoriaId);
        categoria.ifPresent(libro::setCategoria);

        // Obtener y asignar los autores al libro
        List<Autor> autores = autorService.buscarPorIds(autoresIds);
        libro.setAutores(new ArrayList<>(autores));

        libro.setId(id); // Establecer el ID del libro
        libroService.actualizarLibro(libro);
        return "redirect:/libros/listar"; // Redirige a la lista de libros
    }

    @GetMapping("/{id}/autores")
    public String mostrarAutoresDeLibro(@PathVariable Long id, Model model) {
        Optional<Libro> libroOptional = libroService.buscarPorId(id);
        System.out.println("LIBRO OPTIONAL" + libroOptional);
        if (libroOptional.isPresent()) {
            Libro libro = libroOptional.get();
            model.addAttribute("libro", libro);
            model.addAttribute("autores", libro.getAutores());
        }
        return "libro/mostrar_autores_libro"; // Nombre de la vista (mostrar_autores_libro.html)
    }


    @GetMapping("/{id}/eliminar")
    public String eliminarLibro(@PathVariable Long id) {
        libroService.eliminarLibro(id);
        return "redirect:/libros/listar"; // Redirige a la lista de libros
    }
}
