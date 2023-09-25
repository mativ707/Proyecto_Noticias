package com.Mati.noticia.controladores;

import com.Mati.noticia.entidades.Noticia;
import com.Mati.noticia.entidades.Usuario;
import com.Mati.noticia.excepciones.MiException;
import com.Mati.noticia.servicios.NoticiaServicio;
import com.Mati.noticia.servicios.UsuarioServicio;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/") //localhost:8080/
public class PortalControlador {

    @Autowired
    private NoticiaServicio noticiaServicio;
    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/")
    public String index(ModelMap modelo) {
        return "index.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')") //Debe de estar logueado para acceder a este controlador
    @GetMapping("/inicio")
    public String inicio(ModelMap modelo, HttpSession session) {
        
        List<Noticia> noticias = noticiaServicio.listarNoticias();
        modelo.addAttribute("noticias", noticias);
        
        Usuario logueado = (Usuario) session.getAttribute("usuarioSession");

        if (logueado.getRol().toString().equals("ADMIN")) {
            return "inicio.html";
        }

        return "inicio.html";
    }

    @GetMapping("/registrar")
    public String registrar() {
        return "registro.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String email, @RequestParam String password,
            @RequestParam String nombre, String password2, ModelMap modelo) {

        try {
            usuarioServicio.registrarUsuario(nombre, email, password);

            modelo.put("exito", "El usuario se ha registrado con éxito!");

            return "redirect:/";

        } catch (MiException ex) {

            modelo.put("error", ex.getMessage());
            modelo.put("nombre", nombre);
            modelo.put("email", email);

            return "registro.html";
        }

    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {

        if (error != null) {
            modelo.put("error", "El usuario o contraseña son inválidos");
        }

        return "login.html";
    }

}
