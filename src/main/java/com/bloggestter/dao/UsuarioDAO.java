/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloggestter.dao;

import com.bloggestter.pojos.QueryParameterPojo;
import com.bloggestter.pojos.UsuarioPojo;
import com.bloggestter.util.DAOGenerico;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase con la cual se obtiene la informacion de la base de datos del usuario
 *
 * @author FernandoPH
 */
public class UsuarioDAO implements Serializable {

    private String query;
    private List<QueryParameterPojo> parameters;
    private DAOGenerico dao;
    private Map<String, Object> data;
    private ResultSet rs;
    /**
     * 
     */
    public UsuarioDAO() {
        this.parameters = new ArrayList<>();
        this.dao = new DAOGenerico();
        this.data = new HashMap<>();
        data.put("query", "");
        data.put("tipo", 0);
        rs = null;
    }
    /**
     * 
     * @param pojo
     * @return 
     */
    public Map<String, Object> crearUsuario(UsuarioPojo pojo) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta = this.exiteUsuario(pojo);
        if (respuesta.get("e").equals("")) {
            try {
                parameters = UsuarioPojo.parametersCU(pojo);
                query = "INSERT INTO usuarios\n"
                        + "(UsuarioNombre,UsuarioApp,UsuarioApm,UsuarioClave,\n"
                        + "UsuarioCorreo,UsuarioUserName,UsuarioBorrdado,\n"
                        + "TipoUsuarios_IdTipoUsuario,Idiomas_IdIdioma,\n"
                        + "UsuarioImagen,UsuarioFecha)\n"
                        + "VALUES\n"
                        + "(?,?,?,?,?,?,?,?,?,?,?);";
                boolean exito = dao.CUD(query, parameters);
                if (exito) {
                    respuesta.put("a", "c");
                }
            } catch (Exception ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, "Error en crear usuario", ex);
            } finally {
                dao.limpiarPool();
                this.cerrarResultado();
            }
        }
        return respuesta;
    }

    /**
     * Metodo con el cual se valida si el Nombre de usuario o el correo ya
     * existe
     *
     * @param pojo
     * @return
     */
    private Map<String, Object> exiteUsuario(UsuarioPojo pojo) {
        Map<String, Object> existe = new HashMap<>();
        existe.put("a", "nc");
        existe.put("e", "");
        query = "SELECT UsuarioUserName FROM usuarios where UsuarioUserName=?";
        QueryParameterPojo qpp = new QueryParameterPojo();
        qpp.setPosicion(1);
        qpp.setTipo(2);
        qpp.setObj(pojo.getUsuarioUserName());
        parameters.add(qpp);
        data.put("query", query);
        data.put("tipo", 1);
        try {
            rs = dao.sqlAction(data, parameters);
            if (rs.next()) {
                existe.put("e", "El nombre de usuario ya existe por favor escoga otro.");
            }
            if (existe.get("e").equals("")) {
                query = "SELECT UsuarioCorreo FROM usuarios where UsuarioCorreo=?";
                qpp.setPosicion(1);
                qpp.setTipo(2);
                qpp.setObj(pojo.getUsuarioCorreo());
                parameters.add(qpp);
                data.put("query", query);
                data.put("tipo", 1);
                rs = dao.sqlAction(data, parameters);
                if (rs.next()) {
                    existe.put("e", "El correo ingresado ya esta en uso por favor escoga otro.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, "Error en existe usuario", ex);
        } finally {
            dao.limpiarPool();
            this.cerrarResultado();
        }
        return existe;
    }

    /**
     * Metodo con el cual se cierra el resultado local
     */
    private void cerrarResultado() {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
