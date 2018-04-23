/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloggestter.bean;

import com.bloggestter.dao.UsuarioDAO;
import com.bloggestter.pojos.UsuarioPojo;
import com.bloggestter.util.ManejadorArchivos;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;

/**
 *
 * @author ferph
 */
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

    private UsuarioPojo usuario;
    private UsuarioDAO dao;
    private DefaultStreamedContent imgU;
    private ManejadorArchivos utileria;

    /**
     * Metodo contructor de la vista
     */
    @PostConstruct
    public void init() {
        utileria = new ManejadorArchivos();
        usuario = (UsuarioPojo) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        dao = new UsuarioDAO();
        if (usuario != null) {
            imgU = utileria.obtenerImagen(usuario.getUsuarioImagen(), 1);
        }
    }

    public DefaultStreamedContent getImgU() {
        return imgU;
    }

    public void setImgU(DefaultStreamedContent imgU) {
        this.imgU = imgU;
    }

    public UsuarioPojo getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioPojo usuario) {
        this.usuario = usuario;
    }

    public void actualizarUsuario() {
        if (usuario != null) {
            boolean exito = this.dao.editarUsuario(usuario);
            if (exito) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario",usuario);
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage("Se ha editado con éxito "));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Error al editar"));
            }
        }
    }

}
