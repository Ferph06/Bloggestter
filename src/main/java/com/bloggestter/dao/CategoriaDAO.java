/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloggestter.dao;

import com.bloggestter.pojos.CategoriaPojo;
import com.bloggestter.pojos.QueryParameterPojo;
import com.bloggestter.pojos.SubCategoriaPojo;
import com.bloggestter.util.DAOGenerico;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ferph
 */
public class CategoriaDAO implements Serializable {

    private String query;
    private DAOGenerico dao;
    private List<QueryParameterPojo> params;
    private final Map<String, Object> data;
    private ResultSet rs;
    private static final String TABLE = "blog.categorias";

    public CategoriaDAO() {
        this.params = new ArrayList<>();
        this.dao = new DAOGenerico();
        this.data = new HashMap<>();
        data.put("query", "");
        data.put("tipo", 0);
        rs = null;
    }

    public List<CategoriaPojo> getAll() {
        List<CategoriaPojo> ls = new ArrayList<>();
        query = "SELECT * FROM " + TABLE + " WHERE borrado=0";
        data.put("query", query);
        try {
            rs = this.dao.sqlAction(data, params);
            while (rs.next()) {
                CategoriaPojo p = new CategoriaPojo();
                p.setIdCategoria(rs.getInt("idCategorias"));
                p.setCategoria(rs.getNString("categoria"));
                p.setBorrado(false);
                ls.add(p);
            }
        } catch (SQLException e) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ls;
    }

    public List<SubCategoriaPojo> obtenerSubcategorias() {
        List<SubCategoriaPojo> ls = new ArrayList<>();
        query = "SELECT * FROM blog.subcategorias WHERE borrado=0";
        data.put("query", query);
        try {
            rs = this.dao.sqlAction(data, params);
            while (rs.next()) {
                SubCategoriaPojo sp = new SubCategoriaPojo();
                sp.setIdsubcategoria(rs.getInt("idSubcategorias"));
                sp.setSubcategoria(rs.getNString("subcategoria"));
            }
        } catch (SQLException e) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ls;
    }

    public List<CategoriaPojo> categoriasFavoritas(int id) {
        List<CategoriaPojo> ls = new ArrayList<>();
        query = "SELECT * FROM " + TABLE + " WHERE borrado=0 ";
        data.put("query", query);
        try {
            rs = this.dao.sqlAction(data, params);
            while (rs.next()) {
                CategoriaPojo p = new CategoriaPojo();
                p.setIdCategoria(rs.getInt("idCategorias"));
                p.setCategoria(rs.getNString("categoria"));
                p.setBorrado(false);
                ls.add(p);
            }
        } catch (SQLException e) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ls;
    }

}
