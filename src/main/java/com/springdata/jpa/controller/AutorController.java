package com.springdata.jpa.controller;

import com.springdata.jpa.entities.Autor;
import com.springdata.jpa.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping({"/listar","/"})
    public String listarAutores(Model model) {
        List<Autor> autores = autorService.listarTodosLosAutores();
        model.addAttribute("autores", autores);
        return "autor/listar_autores";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoAutor(Model model) {
        model.addAttribute("autor", new Autor());
        return "autor/formulario_autor";
    }

    @PostMapping("/guardar")
    public String guardarAutor(@ModelAttribute Autor autor) {
        autorService.guardarAutor(autor);
        return "redirect:/autores/listar";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarAutor(@PathVariable Long id, Model model) {
        Optional<Autor> autor = autorService.buscarPorId(id);
        autor.ifPresent(value -> model.addAttribute("autor", value));
        return "autor/formulario_autor"; // Nombre de la vista para el formulario (formulario_autor.html)
    }

    @PostMapping("/actualizar")
    public String actualizarAutor(@ModelAttribute Autor autor) {
        autorService.actualizarAutor(autor);
        return "redirect:/autores/listar"; // Redirige a la lista de autores
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAutor(@PathVariable Long id) throws ClassNotFoundException {
        autorService.eliminarAutor(id);
        return "redirect:/autores/listar"; // Redirige a la lista de autores
    }
}
