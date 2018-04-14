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
    private static final String TABLE = "bloggestter.Usuarios";

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
                query = "INSERT INTO bloggestter.Usuarios\n"
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
                + "WHERE  UsuarioBorrdado=0 and UsuarioClave=? and UsuarioCorreo=?";
        parameters.add(new QueryParameterPojo(1, logininfo.getUsuarioClave(), 2));
        parameters.add(new QueryParameterPojo(2, logininfo.getUsuarioCorreo(), 2));
        data.put("query", query);
        data.put("tipo", 1);
        try {
            rs = dao.sqlAction(data, parameters);
            if (rs.next()) {
                System.out.println("ENTRO");
                resultado.put("a", "l");
                resultado.put("e", "Bienvenido " + rs.getNString("UsuarioNombre"));
                resultado.put("u", new UsuarioPojo(rs.getInt("IdUsuarios"), rs.getNString("UsuarioNombre"), rs.getNString("UsuarioApp"), rs.getNString("UsuarioApm"),
                        rs.getNString("UsuarioCorreo"), rs.getNString("UsuarioClave"), rs.getNString("UsuarioImagen"), rs.getDate("UsuarioFecha"), rs.getBoolean("UsuarioBorrdado"),
                        rs.getNString("UsuarioUserName"), rs.getInt("TipoUsuarios_IdTipoUsuario"), rs.getInt("Idiomas_IdIdioma")));
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
        query = "SELECT UsuarioUserName FROM " + TABLE + " where UsuarioUserName=?";
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
                query = "SELECT UsuarioCorreo FROM " + TABLE + " where UsuarioCorreo=?";
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
