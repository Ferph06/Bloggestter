/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloggestter.bean;

import com.bloggestter.dao.BlogDAO;
import com.bloggestter.pojos.BlogPojo;
import com.bloggestter.pojos.UsuarioPojo;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
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
    private UsuarioPojo us;
    private BlogDAO dao;

    /**
     * Metodo que hace la construccion de la informacion que va a estar en la
     * vista principal En caso de no tener usuario se muestra un top 10 de los
     * mejores post conforme a los me gusta En caso de tener usuario se muestran
     * los mejores post de las categorias que tiene como me gusta si no las
     * tiene se muestra un general
     */
    @PostConstruct
    public void init() {
        Map<String, Object> map = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        if (map.get("usuario") != null) {

        } else {

        }
    }

}
