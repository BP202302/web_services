package com.combustisv.controller;

import com.combustisv.service.PrecioService;
import com.combustisv.service.GasolineraService;
import com.combustisv.service.TipoCombustibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/precios")
public class PrecioController {

    @Autowired
    private PrecioService precioService;
    @Autowired
    private GasolineraService gasolineraService;
    @Autowired
    private TipoCombustibleService tipoService;

    @GetMapping
    public String listarPrecios(
            @RequestParam(required = false) Long tipoId,
            @RequestParam(required = false) Long gasolineraId,
            Model model) {

        if (tipoId != null) {
            model.addAttribute("precios", precioService.listarPorTipo(tipoId));
        } else if (gasolineraId != null) {
            model.addAttribute("precios", precioService.listarPorGasolinera(gasolineraId));
        } else {
            model.addAttribute("precios", precioService.listarOrdenadosPorPrecio());
        }

        model.addAttribute("tipos", tipoService.listarTodos());
        model.addAttribute("gasolineras", gasolineraService.listarActivas());
        model.addAttribute("tipoSeleccionado", tipoId);
        model.addAttribute("gasolineraSeleccionada", gasolineraId);
        return "precios";
    }

    @GetMapping("/comparador")
    public String comparador(Model model) {
        model.addAttribute("precios", precioService.listarVigentes());
        model.addAttribute("tipos", tipoService.listarTodos());
        model.addAttribute("gasolineras", gasolineraService.listarActivas());
        return "comparador";
    }
}
