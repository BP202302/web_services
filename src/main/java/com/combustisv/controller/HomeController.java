package com.combustisv.controller;

import com.combustisv.service.PrecioService;
import com.combustisv.service.GasolineraService;
import com.combustisv.service.TipoCombustibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private PrecioService precioService;
    @Autowired
    private GasolineraService gasolineraService;
    @Autowired
    private TipoCombustibleService tipoService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("precios", precioService.listarVigentes());
        model.addAttribute("gasolineras", gasolineraService.listarActivas());
        model.addAttribute("tipos", tipoService.listarTodos());
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
