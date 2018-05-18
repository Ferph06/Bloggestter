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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private static final String TABLE = "blog.";

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

        return ls;
    }

    public List<SubCategoriaPojo> obtenerSubcategorias() {
        List<SubCategoriaPojo> ls = new ArrayList<>();

        return ls;
    }

    public List<CategoriaPojo> categoriasFavoritas() {
        List<CategoriaPojo> ls = new ArrayList<>();

        return ls;
    }

}
