package com.Mati.noticia.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Noticia {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2") //Esto genera una cadena de texto alfanumérica unica y asi evitamos que se repita
    private String id;
    
    private String titulo;
    private String cuerpo;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDeCarga;
    
    
    public Noticia() {
    }

    public Noticia(String id, String titulo, String cuerpo, Date fechaDeCarga) {
        this.id = id;
        this.titulo = titulo;
        this.cuerpo = cuerpo;
        this.fechaDeCarga = fechaDeCarga;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public Date getFechaDeCarga() {
        return fechaDeCarga;
    }

    public void setFechaDeCarga(Date fechaDeCarga) {
        this.fechaDeCarga = fechaDeCarga;
    }
    
    
}
