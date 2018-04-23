/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloggestter.util;

import com.bloggestter.pojos.QueryParameterPojo;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Clase con la cual se hace la conexion al resources
 *
 * @author FernandoPh
 */
public class DAOGenerico implements Serializable {

    private DataSource datasource;
    private final String pool = "jdbc/Bloggestter";
    private ResultSet result;
    private PreparedStatement prepared;
    private Statement statemt;
    private String query;

    /**
     * Metodo con el cual se inicial el data source
     */
    private void init() {
        try {
            Context context = new InitialContext();
            datasource = (DataSource) context.lookup(pool);
        } catch (NamingException ex) {
            Logger.getLogger(DAOGenerico.class.getName()).log(Level.SEVERE, "Error init generico", ex);
        }
    }

    /**
     * Metodo constructor que incia el es data source
     */
    public DAOGenerico() {
        init();
    }

    /**
     * Metodo con el cual se crea la conexion
     *
     * @return
     */
    private Connection crearConexion() {
        Connection connectionreturn = null;
        try {
            connectionreturn = datasource.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DAOGenerico.class.getName()).log(Level.SEVERE, "Error crear la conexion", ex);
        }
        return connectionreturn;
    }



    /**
     * Metodo para cerrar las variable de dml
     */
    private void cerrarAuxiliares() {
        if (statemt != null) {
            try {
                statemt.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAOGenerico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (result != null) {
            try {
                result.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAOGenerico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (prepared != null) {
            try {
                prepared.close();
            } catch (SQLException ex) {
                Logger.getLogger(DAOGenerico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Metodo con el cual se cierran todas las variables de sql
     */
    public void limpiarPool() {
        this.cerrarAuxiliares();
    }

    /**
     * Metodo para hace una busqueda por los parametros necesarios los
     * parametros van junto a su tipo separados por comas
     *
     * @param consulta
     * @param parametros
     * @return el resultado de la consulta ResultSet
     */
    public ResultSet sqlAction(Map<String, Object> consulta, List<QueryParameterPojo> parametros) {
        try (Connection con = this.crearConexion()) {
            query = (String) consulta.get("query");
            int tipo = (int) consulta.get("tipo");
            if (tipo == 0) {
                try {
                    statemt = con.createStatement();
                    result = statemt.executeQuery(query);
                } catch (SQLException ex) {
                    Logger.getLogger(DAOGenerico.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (tipo == 1) {
                try {
                    prepared = con.prepareStatement(query);
                    this.agregarCampos(parametros);
                    result = prepared.executeQuery();
                } catch (SQLException ex) {
                    Logger.getLogger(DAOGenerico.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOGenerico.class.getName()).log(Level.SEVERE, "Error en sqlAction", ex);
        }
        return result;
    }

    /**
     * Metodo con el cual se agrega la consulta para un create,update o delete
     *
     * @param consulta
     * @param parametros
     * @return
     */
    public boolean CUD(String consulta, List<QueryParameterPojo> parametros) {
        boolean exito = false;
        try (Connection con = this.crearConexion()) {
            prepared = con.prepareStatement(consulta);
            this.agregarCampos(parametros);
            exito = prepared.executeUpdate() == 1;
        } catch (SQLException ex) {
            Logger.getLogger(DAOGenerico.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exito;
    }

    /**
     * Metodo con el cual se agregan los campos a la query
     *
     * @param parametros
     */
    private void agregarCampos(List<QueryParameterPojo> parametros) {
        parametros.stream().filter(l -> l.getTipo() == 1).forEachOrdered(l -> {
            try {
                prepared.setInt(l.getPosicion(), QueryParameterPojo.convertirAEntero(l));
            } catch (SQLException ex) {
                Logger.getLogger(DAOGenerico.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        parametros.stream().filter(l -> l.getTipo() == 2).forEachOrdered(l -> {
            try {
                prepared.setString(l.getPosicion(), QueryParameterPojo.convertirATexto(l));
            } catch (SQLException ex) {
                Logger.getLogger(DAOGenerico.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        parametros.stream().filter(l -> l.getTipo() == 3).forEachOrdered(l -> {
            try {
                prepared.setDate(l.getPosicion(), new java.sql.Date(QueryParameterPojo.convertirAFecha(l).getTime()));
            } catch (SQLException ex) {
                Logger.getLogger(DAOGenerico.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        parametros.stream().filter(l -> l.getTipo() == 4).forEachOrdered(l -> {
            try {
                prepared.setBoolean(l.getPosicion(), QueryParameterPojo.convertirABoolean(l));
            } catch (SQLException ex) {
                Logger.getLogger(DAOGenerico.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
