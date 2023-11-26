package com.springdata.jpa.controller;

import com.springdata.jpa.entities.Categoria;
import com.springdata.jpa.entities.Libro;
import com.springdata.jpa.services.CategoriaService;
import com.springdata.jpa.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private LibroService libroService;

    @GetMapping({"/listar","/"})
    public String listarCategorias(Model model) {
        List<Categoria> categorias = categoriaService.listarTodasLasCategorias();
        model.addAttribute("categorias", categorias);
        return "categoria/listar_categorias"; // Nombre de la vista (listar_categorias.html)
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevaCategoria(Model model) {
        Categoria categoria = new Categoria();
        model.addAttribute("categoria", categoria);
        return "categoria/formulario_categoria"; // Nombre de la vista para el formulario (formulario_categoria.html)
    }

    @PostMapping("/guardar")
    public String guardarCategoria(@ModelAttribute Categoria categoria) {
        Categoria categoriaGuardada = categoriaService.guardarCategoria(categoria);
        // Cargar los libros asociados a la categoría
        List<Libro> libros = libroService.buscarPorCategoria(categoriaGuardada);
        categoriaGuardada.setLibros(libros);
        categoriaService.actualizarCategoria(categoriaGuardada);
        return "redirect:/categorias/listar"; // Redirige a la lista de categorías
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditarCategoria(@PathVariable Long id, Model model) {
        Optional<Categoria> categoria = categoriaService.buscarPorId(id);
        categoria.ifPresent(value -> model.addAttribute("categoria", value));
        return "categoria/formulario_categoria"; // Nombre de la vista para el formulario de edición (formulario_categoria.html)
    }

    @PostMapping("/{id}/actualizar")
    public String actualizarCategoria(@PathVariable Long id, @ModelAttribute Categoria categoria) {
        Categoria categoriaActual = categoriaService.buscarPorId(id).orElse(null);
        if (categoriaActual != null) {
            categoria.setLibros(categoriaActual.getLibros());
            categoriaService.actualizarCategoria(categoria);
        }
        return "redirect:/categorias/listar"; // Redirige a la lista de categorías
    }

    @GetMapping("/{id}/eliminar")
    public String eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return "redirect:/categorias/listar"; // Redirige a la lista de categorías
    }
}
