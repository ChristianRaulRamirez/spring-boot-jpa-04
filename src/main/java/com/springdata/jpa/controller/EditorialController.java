package com.springdata.jpa.controller;

import com.springdata.jpa.entities.Editorial;
import com.springdata.jpa.entities.Libro;
import com.springdata.jpa.services.EditorialService;
import com.springdata.jpa.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/editoriales")
public class EditorialController {

    @Autowired
    private EditorialService editorialService;

    @Autowired
    private LibroService libroService;

    @GetMapping("/nuevo") // Muestra el formulario para una nueva editorial
    public String mostrarFormularioNuevaEditorial(Model model) {
        model.addAttribute("editorial", new Editorial()); // Se crea una nueva instancia de Editorial
        return "editorial/formulario_editorial"; // Nombre de la vista del formulario de creación
    }

    @PostMapping("/guardar")
    public String guardarEditorial(@ModelAttribute Editorial editorial) {
        Editorial editorialGuardada = editorialService.guardarEditorial(editorial);
        return "redirect:/editoriales/listar"; // Redirige a la lista de editoriales
    }

    @GetMapping({"/listar","/"})
    public String listarEditoriales(Model model) {
        List<Editorial> editoriales = editorialService.listarTodasLasEditoriales();
        model.addAttribute("editoriales", editoriales);
        return "editorial/listar_editoriales"; // Nombre de la vista (listar_editoriales.html)
    }

    @GetMapping("/{id}")
    public String mostrarEditorial(@PathVariable Long id, Model model) {
        Optional<Editorial> editorialOptional = editorialService.buscarPorId(id);
        if (editorialOptional.isPresent()) {
            Editorial editorial = editorialOptional.get();
            model.addAttribute("editorial", editorial);
            model.addAttribute("libros", editorial.getLibros());
        }
        return "editorial/mostrar_editorial"; // Nombre de la vista (mostrar_editorial.html)
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditarEditorial(@PathVariable Long id, Model model) {
        Optional<Editorial> editorial = editorialService.buscarPorId(id);
        editorial.ifPresent(value -> model.addAttribute("editorial", value));
        return "editorial/formulario_editorial"; // Nombre de la vista para el formulario de edición (formulario_editorial.html)
    }

    @PostMapping("/{id}/actualizar")
    public String actualizarEditorial(@PathVariable Long id, @ModelAttribute Editorial editorial) {
        Optional<Editorial> editorialOptional = editorialService.buscarPorId(id);
        if (editorialOptional.isPresent()) {
            Editorial editorialActual = editorialOptional.get();
            editorialActual.setNombre(editorial.getNombre());
            // Actualizar la editorial en la base de datos
            editorialService.actualizarEditorial(editorialActual);
        }
        return "redirect:/editoriales/listar"; // Redirige a la lista de editoriales
    }

    @GetMapping("/{id}/eliminar")
    public String eliminarEditorial(@PathVariable Long id) {
        editorialService.eliminarEditorial(id);
        return "redirect:/editoriales/listar"; // Redirige a la lista de editoriales
    }

    @GetMapping("/{id}/libros")
    public String mostrarLibrosDeEditorial(@PathVariable Long id, Model model) {
        Optional<Editorial> editorialOptional = editorialService.buscarPorId(id);
        if (editorialOptional.isPresent()) {
            Editorial editorial = editorialOptional.get();
            model.addAttribute("editorial", editorial);
            model.addAttribute("libros", editorial.getLibros());
        }
        return "editorial/mostrar_libros_editorial"; // Nombre de la vista (mostrar_libros_editorial.html)
    }


}
