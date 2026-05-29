package com.combustisv.controller;

import com.combustisv.model.*;
import com.combustisv.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired private GasolineraService gasolineraService;
    @Autowired private PrecioService precioService;
    @Autowired private TipoCombustibleService tipoService;

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("totalGasolineras", gasolineraService.listarTodas().size());
        model.addAttribute("totalPrecios", precioService.listarVigentes().size());
        model.addAttribute("totalTipos", tipoService.listarTodos().size());
        return "admin/dashboard";
    }

    // === GASOLINERAS CRUD ===
    @GetMapping("/gasolineras")
    public String listarGasolineras(Model model) {
        model.addAttribute("gasolineras", gasolineraService.listarTodas());
        return "admin/gasolineras";
    }

    @GetMapping("/gasolineras/nueva")
    public String nuevaGasolinera(Model model) {
        model.addAttribute("gasolinera", new Gasolinera());
        return "admin/gasolinera-form";
    }

    @PostMapping("/gasolineras/guardar")
    public String guardarGasolinera(@Valid @ModelAttribute Gasolinera gasolinera,
                                     BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) return "admin/gasolinera-form";
        gasolineraService.guardar(gasolinera);
        redirect.addFlashAttribute("mensaje", "Gasolinera guardada exitosamente");
        return "redirect:/admin/gasolineras";
    }

    @GetMapping("/gasolineras/editar/{id}")
    public String editarGasolinera(@PathVariable Long id, Model model) {
        gasolineraService.buscarPorId(id).ifPresent(g -> model.addAttribute("gasolinera", g));
        return "admin/gasolinera-form";
    }

    @GetMapping("/gasolineras/eliminar/{id}")
    public String eliminarGasolinera(@PathVariable Long id, RedirectAttributes redirect) {
        gasolineraService.eliminar(id);
        redirect.addFlashAttribute("mensaje", "Gasolinera eliminada");
        return "redirect:/admin/gasolineras";
    }

    // === PRECIOS CRUD ===
    @GetMapping("/precios")
    public String listarPrecios(Model model) {
        model.addAttribute("precios", precioService.listarTodos());
        return "admin/precios";
    }

    @GetMapping("/precios/nuevo")
    public String nuevoPrecio(Model model) {
        model.addAttribute("precio", new Precio());
        model.addAttribute("gasolineras", gasolineraService.listarActivas());
        model.addAttribute("tipos", tipoService.listarTodos());
        return "admin/precio-form";
    }

    @PostMapping("/precios/guardar")
    public String guardarPrecio(@Valid @ModelAttribute Precio precio,
                                 BindingResult result, Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            model.addAttribute("gasolineras", gasolineraService.listarActivas());
            model.addAttribute("tipos", tipoService.listarTodos());
            return "admin/precio-form";
        }
        precioService.guardar(precio);
        redirect.addFlashAttribute("mensaje", "Precio guardado exitosamente");
        return "redirect:/admin/precios";
    }

    @GetMapping("/precios/eliminar/{id}")
    public String eliminarPrecio(@PathVariable Long id, RedirectAttributes redirect) {
        precioService.eliminar(id);
        redirect.addFlashAttribute("mensaje", "Precio eliminado");
        return "redirect:/admin/precios";
    }
}
