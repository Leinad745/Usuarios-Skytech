package com.ServicioJuegos.SkyTech.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.ServicioJuegos.SkyTech.service.JuegoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import com.ServicioJuegos.SkyTech.model.Juego;


@RestController
@RequestMapping("/api/juegos")
public class JuegoController {
    @Autowired
    private JuegoService juegoService;

    @GetMapping("/{tituloJuego}")
    public Juego findByTituloJuego(@PathVariable String tituloJuego) {
        return juegoService.findByTituloJuego(tituloJuego);
    }
    
}
