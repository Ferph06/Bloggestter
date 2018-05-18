/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloggestter.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase que sirve como modelo para el blog
 *
 * @author ferph
 */
public class BlogPojo implements Serializable {

    private int idblog;
    private String titulo;
    private List<CategoriaPojo> categorias;
    private boolean borrado;
    private Date fecha;
    private String by;
    private String contenido;
    private int usuario;

    public BlogPojo(String titulo, boolean borrado, String contenido, int idusuario) {
        this.titulo = titulo;
        this.borrado = borrado;
        this.contenido = contenido;
        this.usuario = idusuario;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public int getIdblog() {
        return idblog;
    }

    public void setIdblog(int idblog) {
        this.idblog = idblog;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<CategoriaPojo> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaPojo> categorias) {
        this.categorias = categorias;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public static List<QueryParameterPojo> INSERU(BlogPojo p) {
        List<QueryParameterPojo> parametes = new ArrayList<>();
        qlist.add(new QueryParameterPojo(1, u.getUsuarioNombre(), 2));
        qlist.add(new QueryParameterPojo(2, u.getUsuarioApp(), 2));
        qlist.add(new QueryParameterPojo(3, u.getUsuarioApm(), 2));
        qlist.add(new QueryParameterPojo(4, u.getUsuarioUserName(), 2));
        qlist.add(new QueryParameterPojo(5, u.getIdUsuario(), 1));
        return parametes;
    }

}
