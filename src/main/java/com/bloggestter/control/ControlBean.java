/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloggestter.control;

import com.bloggestter.pojos.UsuarioPojo;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Fernando
 */
@ManagedBean
@ViewScoped
public class ControlBean implements Serializable {


    /**
     * Metodo con el que se verifica la sesion del usuario
     *
     * @return
     */
    public UsuarioPojo obtenerUsuario() {
        UsuarioPojo u = null;
        Map<String, Object> map = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        if (map.get("usuario") != null) {
            u = (UsuarioPojo) map.get("usuario");
        }

        return u;
    }

    public void cambiarIdioma() {
        
    }

    /**
     * Metodo con el cual se hace el cierre de la sesion
     */
    public void cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuario");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("../");
        } catch (IOException ex) {
            Logger.getLogger(ControlBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
