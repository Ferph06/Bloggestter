/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloggestter.bean;

import com.bloggestter.dao.UsuarioDAO;
import com.bloggestter.pojos.UsuarioPojo;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ferph
 */
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

    private UsuarioPojo usuario;
    private UsuarioDAO dao;

    /**
     * Metodo contructor de la vista
     */
    @PostConstruct
    public void init() {
        usuario = (UsuarioPojo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        dao = new UsuarioDAO();
    }

    public UsuarioPojo getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioPojo usuario) {
        this.usuario = usuario;
    }
    
    

}
