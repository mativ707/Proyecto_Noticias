package com.Mati.noticia.controladores;

import com.Mati.noticia.entidades.Noticia;
import com.Mati.noticia.servicios.NoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/noticia") //localhost:8080/noticia

public class NoticiaControlador {

    @Autowired
    private NoticiaServicio noticiaServicio;
    
    @GetMapping("/verNoticia/{id}")
    public String MostrarNoticias(@PathVariable String id, ModelMap modelo) {
        //Obtenemos la noticia mediante su id
        Noticia noticia = noticiaServicio.getOne(id);
        modelo.addAttribute("noticia", noticia);
        
        return "verNoticia.html";
    }

}
