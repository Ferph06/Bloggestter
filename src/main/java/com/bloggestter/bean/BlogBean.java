/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloggestter.bean;

import com.bloggestter.dao.BlogDAO;
import com.bloggestter.dao.CategoriaDAO;
import com.bloggestter.pojos.BlogPojo;
import com.bloggestter.pojos.CategoriaPojo;
import com.bloggestter.pojos.UsuarioPojo;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 * Clase la cual sirve como controlador de la seccion referente al blog
 *
 * @author ferph
 */
@ManagedBean
@ViewScoped
public class BlogBean implements Serializable {

    private List<BlogPojo> lsBlog;
    private List<CategoriaPojo> lsCat;
    private UsuarioPojo us;
    private BlogDAO dao;
    private CategoriaDAO cdao;
    private BlogPojo blog;
    /**
     * Metodo que hace la construccion de la informacion que va a estar en la
     * vista principal En caso de no tener usuario se muestra un top 10 de los
     * mejores post conforme a los me gusta En caso de tener usuario se muestran
     * los mejores post de las categorias que tiene como me gusta si no las
     * tiene se muestra un general
     */
    @PostConstruct
    public void init() {
        blog=new BlogPojo();
        
        lsCat = new ArrayList<>();
        lsBlog = new ArrayList<>();
        cdao=new CategoriaDAO();
        lsCat=cdao.getAll();
        BlogDAO bdo=new BlogDAO();
        try {
            lsBlog=bdo.obtenerTodos();
        } catch (IOException ex) {
            Logger.getLogger(BlogBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        Map<String, Object> map = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        if (map.get("usuario") != null) {
            UsuarioPojo us=(UsuarioPojo) map.get("usuario");
            blog.setBy(us.getUsuarioUserName());
            blog.setFecha(new Date());
        } else {

        }
        if(map.get("blog")!=null){
            blog=(BlogPojo) map.get("blog");
        }
    }

    public void crearBlog() {

    }

    public void agregarFavorito() {

    }
    public void verCategorias(){
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("./../comun/categorias.bloggestter?faces-redirect=true");
        } catch (IOException ex) {
            Logger.getLogger(BlogBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void verBlog(BlogPojo pojo){
         try {
              FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("blog",pojo);
            FacesContext.getCurrentInstance().getExternalContext().redirect("./../comun/blog.bloggestter?faces-redirect=true");
        } catch (IOException ex) {
            Logger.getLogger(BlogBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void verSubCategorias(int id){
        
    }
    
    public List<BlogPojo> getLsBlog() {
        return lsBlog;
    }

    public void setLsBlog(List<BlogPojo> lsBlog) {
        this.lsBlog = lsBlog;
    }

    public List<CategoriaPojo> getLsCat() {
        return lsCat;
    }

    public void setLsCat(List<CategoriaPojo> lsCat) {
        this.lsCat = lsCat;
    }

    public BlogPojo getBlog() {
        return blog;
    }

    public void setBlog(BlogPojo blog) {
        this.blog = blog;
    }
    
    
}
