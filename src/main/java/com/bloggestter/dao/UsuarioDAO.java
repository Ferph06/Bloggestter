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
import java.util.Date;
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
    private final DAOGenerico dao;
    private final Map<String, Object> data;
    private ResultSet rs;
    private static final String TABLE = "blog.Usuarios";

    /**
     * Metodo constructor con los parametros necesario
     */
    public UsuarioDAO() {
        this.parameters = new ArrayList<>();
        this.dao = new DAOGenerico();
        this.data = new HashMap<>();
        data.put("query", "");
        data.put("tipo", 0);
        rs = null;
    }

    public boolean editarUsuario(UsuarioPojo p) {
        boolean exito = false;
        try {
            parameters = UsuarioPojo.parameterEU(p);
            query = "UPDATE " + TABLE + " SET\n"
                    + "	nombreUsuarios =?,\n"
                    + "	appUsuarios = ?,\n"
                    + "	apmUsuarios =?,\n"
                    + "	username = ?\n"
                    + "WHERE idUsuarios = ?";
            exito = dao.CUD(query, parameters);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, "Error en crear usuario", ex);
        } finally {
            dao.limpiarPool();
            this.cerrarResultado();
        }

        return exito;
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
                query = "INSERT INTO blog.usuarios\n"
                        + "(\n"
                        + "nombreUsuarios,\n"
                        + "appUsuarios,\n"
                        + "apmUsuarios,\n"
                        + "email,\n"
                        + "username,\n"
                        + "borrado,\n"
                        + "activo,\n"
                        + "Idiomas_idIdiomas,\n"
                        + "Roles_idRoles,\n"
                        + "ImagenUsuario,\n"
                        + "clave)\n"
                        + "VALUES\n"
                        + "(?,?,?,?,?,?,?,?,?,?,?);\n";
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
     * Metodo con el cual se logea al usuario
     *
     * @param logininfo
     * @return
     */
    public Map<String, Object> loginUsuario(UsuarioPojo logininfo) {
        Map<String, Object> resultado = new HashMap<>();
        resultado.put("a", "nl");
        resultado.put("e", "Correo o contraseña invalidos ,favor de intentar de nuevo");
        resultado.put("u", null);
        query = "SELECT * FROM " + TABLE + " "
                + "WHERE  borrado=0 and clave=? and email=?";
        parameters.add(new QueryParameterPojo(1, logininfo.getUsuarioClave(), 2));
        parameters.add(new QueryParameterPojo(2, logininfo.getUsuarioCorreo(), 2));
        data.put("query", query);
        data.put("tipo", 1);
        try {
            rs = dao.sqlAction(data, parameters);
            if (rs.next()) {
                System.out.println("ENTRO");
                resultado.put("a", "l");
                resultado.put("e", "Bienvenido " + rs.getNString("nombreUsuarios"));
                resultado.put("u", new UsuarioPojo(rs.getInt("idUsuarios"), rs.getNString("nombreUsuarios"), rs.getNString("appUsuarios"), rs.getNString("apmUsuarios"),
                        rs.getNString("email"), rs.getNString("clave"), rs.getString("ImagenUsuario"),new Date(), rs.getBoolean("borrado"),
                        rs.getNString("username"), rs.getInt("Roles_idRoles"), rs.getInt("Idiomas_IdIdiomas")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, "Error en existe usuario", ex);
        } finally {
            dao.limpiarPool();
            this.cerrarResultado();
        }
        return resultado;
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
        query = "SELECT username FROM " + TABLE + " where username=?";
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
                query = "SELECT email FROM " + TABLE + " where email=?";
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
