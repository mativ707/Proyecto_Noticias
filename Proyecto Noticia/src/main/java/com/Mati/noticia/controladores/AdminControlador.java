package com.Mati.noticia.controladores;

import com.Mati.noticia.excepciones.MiException;
import com.Mati.noticia.servicios.NoticiaServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminControlador {

    @Autowired
    private NoticiaServicio noticiaServicio;

    @GetMapping("/registrar")
    public String registrar() {
        return "noticia_form.html";
    }

    @PostMapping("/registro")
    public String registrarNoticia(@RequestParam String titulo, @RequestParam String cuerpoNoticia,
            ModelMap modelo) {

        try {
            noticiaServicio.crearNoticia(titulo, cuerpoNoticia);
            //Mensaje de exito
            modelo.put("exito", "La noticia se ha cargado con éxito!");
            return "redirect:/inicio";
            
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "noticia_form.html"; //Volvemos a cargar el formulario
        }
    }

    @GetMapping("/modificar/{id}")
    public String modificarNoticia(@PathVariable String id, ModelMap modelo) {

        //Obtenemos la noticia mediante su id
        modelo.addAttribute("noticia", noticiaServicio.getOne(id));

        return "noticiaModificar.html";
    }

    @PostMapping("/modificar/{id}")
    public String modificarNoticia(@PathVariable String id, String titulo,
            String cuerpoNoticia, ModelMap modelo) {

        modelo.put("noticia", noticiaServicio.getOne(id));

        try {
            noticiaServicio.modificarNoticia(id, titulo, cuerpoNoticia);
            modelo.put("exito", "Se ha modificado con éxito la noticia!");
            return "redirect:/inicio";
            
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "noticiaModificar.html";
        }
    }
    
    @PostMapping("/eliminar/{id}")
    public String eliminarNoticia(@PathVariable String id, ModelMap modelo){
        
        modelo.put("noticia", noticiaServicio.getOne(id));
        
        try {
            noticiaServicio.eliminarNoticia(id);
            modelo.put("exito", "La noticia se eliminó con éxito!");
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "inicio.html";
        }
        
        return "redirect:/inicio";
    }
}
