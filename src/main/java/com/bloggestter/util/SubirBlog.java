/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloggestter.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.primefaces.json.JSONObject;

/**
 *
 * @author ferph
 */
@WebServlet(name = "SubirBlog", urlPatterns = {"/SubirBlog"})
//@MultipartConfig
public class SubirBlog extends HttpServlet {

    private static OutputStreamWriter OUTPUTWRITER;
    private static FileOutputStream OUTPUTSTREAM;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JSONObject json = new JSONObject();
        request.setCharacterEncoding("utf8");
        System.out.println("LLEGA");
        response.setContentType("application/json");
        int idU = Integer.parseInt(request.getHeader("idus"));
        String titulo = String.valueOf(request.getHeader("titulo"));
        UUID uuid = UUID.randomUUID();
        String name = uuid.toString().split("-")[0] + "" + titulo + "" + idU + ".txt";
        String ruta = ManejadorArchivos.RUTA_BASE + ManejadorArchivos.HTML + "" + name;
        try {
            OUTPUTSTREAM = new FileOutputStream(new File(ruta), false);
            OUTPUTWRITER = new OutputStreamWriter(OUTPUTSTREAM, StandardCharsets.ISO_8859_1);
            OUTPUTWRITER.write(request.getParameter("fl"));
            json.put("exito", true);
            json.put("ruta", ruta);
            response.setStatus(200);
            response.getWriter().write(json.toString());
        } catch (FileNotFoundException ex) {
            json.put("exito", false);
            json.put("ruta", name);
            response.setStatus(500);
             response.getWriter().write(json.toString());
            Logger.getLogger(SubirBlog.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            SubirBlog.cerrarFileWrite();
        }
    }

    /**
     * Metodo para cerrar el filewriter usado para crear el archivo
     */
    private static void cerrarFileWrite() {

        if (OUTPUTWRITER != null) {
            try {
                OUTPUTWRITER.flush();
                OUTPUTSTREAM.close();
                OUTPUTWRITER.close();
            } catch (IOException ex) {
                Logger.getLogger(SubirBlog.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
