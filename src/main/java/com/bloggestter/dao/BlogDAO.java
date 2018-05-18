/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloggestter.dao;

import com.bloggestter.pojos.BlogPojo;
import com.bloggestter.pojos.QueryParameterPojo;
import com.bloggestter.util.DAOGenerico;
import com.bloggestter.util.ManejadorArchivos;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
public class BlogDAO implements Serializable {

    private String query;
    private DAOGenerico dao;
    private List<QueryParameterPojo> params;
    private final Map<String, Object> data;
    private ResultSet rs;
    private static final String TABLE = "blog.blog";

    public BlogDAO() {
        this.params = new ArrayList<>();
        this.dao = new DAOGenerico();
        this.data = new HashMap<>();
        data.put("query", "");
        data.put("tipo", 0);
        rs = null;
    }

    public boolean agregarBlog(BlogPojo pojo) {
        boolean exito = false;
        query = "INSERT INTO blog.blog\n"
                + "(\n"
                + "BlogTitulo,\n"
                + "borrado,\n"
                + "Usuarios_idUsuarios,\n"
                + "blog)\n"
                + "VALUES\n"
                + "(\n"
                + "?,\n"
                + "?,\n"
                + "?,\n"
                + "?);";
        return exito;
    }

    public boolean agregarFavorito(BlogPojo p) {
        boolean exito = false;

        return exito;
    }

    public List<BlogPojo> obtenerTodos() throws IOException {
        List<BlogPojo> ls = new ArrayList<>();
        query = "SELECT * FROM " + TABLE + " WHERE borrado=0 ";
        data.put("query", query);
        try {
            rs = this.dao.sqlAction(data, params);
            while (rs.next()) {
                StringBuilder texto = new StringBuilder();
                BufferedReader reader = null;
                String htmlemail = "";
                String linea = "";
                BlogPojo p = new BlogPojo();
                p.setTitulo(rs.getString("BlogTitulo"));
                p.setIdblog(rs.getInt("idBlog"));
                Path path = Paths.get(ManejadorArchivos.RUTA_BASE + ManejadorArchivos.HTML + "" + rs.getNString("blog"));
                try {
                    reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
                    while ((linea = reader.readLine()) != null) {
                        texto.append(linea).append("\n");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    if (reader != null) {
                        reader.close();
                    }
                }
                p.setContenido(texto.toString());
                p.setBorrado(false);
                ls.add(p);
            }
        } catch (SQLException e) {
            Logger.getLogger(BlogDAO.class.getName()).log(Level.SEVERE, null, e);
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

    public List<BlogPojo> obtenerFavoritos(int idu) {
        List<BlogPojo> ls = new ArrayList<>();

        return ls;
    }

}
