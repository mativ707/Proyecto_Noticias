package com.Mati.noticia.servicios;

import com.Mati.noticia.entidades.Noticia;
import com.Mati.noticia.excepciones.MiException;
import com.Mati.noticia.repositorios.NoticiaRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticiaServicio {

    @Autowired
    private NoticiaRepositorio noticiaRepo;

    @Transactional
    public void crearNoticia(String titulo, String cuerpo) throws MiException {
        validar(titulo, cuerpo);

        //Creamos la nueva noticia
        Noticia noticia = new Noticia();

        //Seteamos atributos
        noticia.setCuerpo(cuerpo);
        noticia.setTitulo(titulo);
        noticia.setFechaDeCarga(new Date());
        //Guardamos
        noticiaRepo.save(noticia);
    }

    @Transactional
    public void eliminarNoticia(String id) throws MiException {
        
        Optional<Noticia> respuestaTitulo = noticiaRepo.findById(id);

        if (respuestaTitulo.isPresent()) {
            // Completamos para obtener la noticia mediante el .get()
            Noticia noticia = respuestaTitulo.get();

            //Eliminamos
            noticiaRepo.delete(noticia);
        }
    }

    public void modificarNoticia(String id, String titulo, String cuerpo) throws MiException {
        validar(titulo, cuerpo);

        Optional<Noticia> respuestaTitulo = noticiaRepo.findById(id);

        if (respuestaTitulo.isPresent()) {
            // Completamos para obtener la noticia mediante el .get()
            Noticia noticia = respuestaTitulo.get();

            //Seteamos atributos
            noticia.setCuerpo(cuerpo);
            noticia.setTitulo(titulo);

            //Guardamos
            noticiaRepo.save(noticia);
        }
    }

    @Transactional
    public Noticia getOne(String id) {
        return noticiaRepo.getOne(id);
    }

    public List<Noticia> listarNoticias() {
        List<Noticia> noticias = new ArrayList();

        noticias = noticiaRepo.ordenarNoticiasPorFecha();

        // Ordena la lista de noticias por fecha de carga de más reciente a más antigua
        //Collections.sort(noticias, (Noticia noticia1, Noticia noticia2) -> noticia2.getFechaDeCarga().compareTo(noticia1.getFechaDeCarga()));
        return noticias;
    }

    public void validar(String titulo, String cuerpo) throws MiException {
        if (titulo.isEmpty() || titulo == null) {
            throw new MiException("El titulo no puede estar vacio");
        }
        if (cuerpo.isEmpty() || cuerpo == null) {
            throw new MiException("El cuerpo no puede estar vacio");
        }
    }
}
