/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloggestter.control;

import com.bloggestter.dao.UsuarioDAO;
import com.bloggestter.pojos.UsuarioPojo;
import com.bloggestter.util.ManejadorArchivos;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 * Clase que sirve como controlador para el login y creacion del usuario
 *
 * @author FernandoPh
 */
@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {

    private UsuarioPojo usuario;
    private UsuarioDAO usuarioDao;
    private Part imgUsuario;
    private ManejadorArchivos utileria;

    @PostConstruct
    public void init() {
        usuario = new UsuarioPojo();
        usuarioDao = new UsuarioDAO();
    }

    public UsuarioPojo getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioPojo usuario) {
        this.usuario = usuario;
    }

    public Part getImgUsuario() {
        return imgUsuario;
    }

    public void setImgUsuario(Part imgUsuario) {
        this.imgUsuario = imgUsuario;
    }

    /**
     * Metodo con el cual se crea un usuario
     */
    public void crearUsuario() {
        if (usuario != null) {
            utileria = new ManejadorArchivos();
            if (imgUsuario != null) {
                usuario.setUsuarioImagen(utileria.subirImagenes(imgUsuario, 1));
            } else {
                usuario.setUsuarioImagen("");
            }
            usuario.setIdtipoUsuario(2);
            usuario.setIdIdioma(1);
            usuario.setUsuarioFecha(new Date());
            Map<String, Object> res = usuarioDao.crearUsuario(usuario);
            if (this.validarRespuesta(res)) {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage("Usuario creado"));
            }
        }
    }

    /**
     * Metodo con el cual se valida la respuesta de nuestra creacion del usuario
     *
     * @param map
     * @return
     */
    private boolean validarRespuesta(Map<String, Object> map) {
        boolean valido = true;
        if ((!"".equals((String) map.get("e"))) && !"c".equals((String) map.get("a"))) {
            valido = false;
            FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage((String) map.get("e")));
        }
        return valido;
    }

    /**
     * Metodo con el cual se crea al usuario
     */
    public void loginUsuario() {
        if (usuario != null) {
            Map<String, Object> res = this.usuarioDao.loginUsuario(usuario);
            if (((String) res.get("a")).equals("l")) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", (UsuarioPojo) res.get("u"));
                try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("./../comun/perfilUsuario.bloggestter?faces-redirect=true");
                } catch (IOException ex) {
                    Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage((String) res.get("e")));
            }
        }
    }

}
