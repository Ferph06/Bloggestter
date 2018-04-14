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
 * Clase la cual modela la entidad de usuarios
 *
 * @author FernandoPh
 */
public class UsuarioPojo implements Serializable {

    private int idUsuario;
    private String usuarioNombre;
    private String usuarioApp;
    private String usuarioApm;
    private String usuarioCorreo;
    private String usuarioClave;
    private String usuarioImagen;
    private Date usuarioFecha;
    private boolean usuarioBorrado;
    private String usuarioUserName;
    private int idtipoUsuario;
    private int idIdioma;
    
    public UsuarioPojo() {

    }
    /**
     * Metodo constructor para llenar el usuario
     * @param idUsuario
     * @param usuarioNombre
     * @param usuarioApp
     * @param usuarioApm
     * @param usuarioCorreo
     * @param usuarioClave
     * @param usuarioImagen
     * @param usuarioFecha
     * @param usuarioBorrado
     * @param usuarioUserName
     * @param idtipoUsuario
     * @param idIdioma 
     */
    public UsuarioPojo(int idUsuario, String usuarioNombre, String usuarioApp, String usuarioApm, String usuarioCorreo, String usuarioClave, String usuarioImagen, Date usuarioFecha, boolean usuarioBorrado, String usuarioUserName, int idtipoUsuario, int idIdioma) {
        this.idUsuario = idUsuario;
        this.usuarioNombre = usuarioNombre;
        this.usuarioApp = usuarioApp;
        this.usuarioApm = usuarioApm;
        this.usuarioCorreo = usuarioCorreo;
        this.usuarioClave = usuarioClave;
        this.usuarioImagen = usuarioImagen;
        this.usuarioFecha = usuarioFecha;
        this.usuarioBorrado = usuarioBorrado;
        this.usuarioUserName = usuarioUserName;
        this.idtipoUsuario = idtipoUsuario;
        this.idIdioma = idIdioma;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getUsuarioApp() {
        return usuarioApp;
    }

    public void setUsuarioApp(String usuarioApp) {
        this.usuarioApp = usuarioApp;
    }

    public String getUsuarioApm() {
        return usuarioApm;
    }

    public void setUsuarioApm(String usuarioApm) {
        this.usuarioApm = usuarioApm;
    }

    public String getUsuarioCorreo() {
        return usuarioCorreo;
    }

    public void setUsuarioCorreo(String usuarioCorreo) {
        this.usuarioCorreo = usuarioCorreo;
    }

    public String getUsuarioClave() {
        return usuarioClave;
    }

    public void setUsuarioClave(String usuarioClave) {
        this.usuarioClave = usuarioClave;
    }

    public String getUsuarioImagen() {
        return usuarioImagen;
    }

    public void setUsuarioImagen(String usuarioImagen) {
        this.usuarioImagen = usuarioImagen;
    }

    public Date getUsuarioFecha() {
        return usuarioFecha;
    }

    public void setUsuarioFecha(Date usuarioFecha) {
        this.usuarioFecha = usuarioFecha;
    }

    public boolean isUsuarioBorrado() {
        return usuarioBorrado;
    }

    public void setUsuarioBorrado(boolean usuarioBorrado) {
        this.usuarioBorrado = usuarioBorrado;
    }

    public String getUsuarioUserName() {
        return usuarioUserName;
    }

    public void setUsuarioUserName(String usuarioUserName) {
        this.usuarioUserName = usuarioUserName;
    }

    public int getIdtipoUsuario() {
        return idtipoUsuario;
    }

    public void setIdtipoUsuario(int idtipoUsuario) {
        this.idtipoUsuario = idtipoUsuario;
    }

    public int getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(int idIdioma) {
        this.idIdioma = idIdioma;
    }

    /**
     *
     * @param u
     * @return
     */
    public static List<QueryParameterPojo> parametersCU(UsuarioPojo u) {
        List<QueryParameterPojo> qlist = new ArrayList<>();
        qlist.add(new QueryParameterPojo(1, u.getUsuarioNombre(), 2));
        qlist.add(new QueryParameterPojo(2, u.getUsuarioApp(), 2));
        qlist.add(new QueryParameterPojo(3, u.getUsuarioApm(), 2));
        qlist.add(new QueryParameterPojo(4, u.getUsuarioClave(), 2));
        qlist.add(new QueryParameterPojo(5, u.getUsuarioCorreo(), 2));
        qlist.add(new QueryParameterPojo(6, u.getUsuarioUserName(), 2));
        qlist.add(new QueryParameterPojo(7, u.isUsuarioBorrado(), 4));
        qlist.add(new QueryParameterPojo(8, u.getIdtipoUsuario(), 1));
        qlist.add(new QueryParameterPojo(9, u.getIdIdioma(), 1));
        qlist.add(new QueryParameterPojo(10, u.getUsuarioImagen(), 2));
        qlist.add(new QueryParameterPojo(11, u.getUsuarioFecha(), 3));
        return qlist;
    }

}
