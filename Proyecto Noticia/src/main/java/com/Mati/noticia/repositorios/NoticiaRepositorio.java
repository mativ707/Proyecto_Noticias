package com.Mati.noticia.repositorios;

import com.Mati.noticia.entidades.Noticia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiaRepositorio extends JpaRepository<Noticia, String> {

    @Query("SELECT n FROM Noticia n WHERE n.titulo = :titulo")
    public Noticia buscarNoticiaPorTitulo(@Param("titulo") String titulo);
    
    @Query("SELECT n FROM Noticia n ORDER BY n.fechaDeCarga DESC")
    public List<Noticia> ordenarNoticiasPorFecha();
}
