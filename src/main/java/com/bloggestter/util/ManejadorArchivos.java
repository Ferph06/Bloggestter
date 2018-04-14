/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloggestter.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;
import org.primefaces.model.DefaultStreamedContent;

/**
 * Clase auxiliar con la cual se maneja los archivos
 *
 * @author ferph
 */
public class ManejadorArchivos implements Serializable {

    public static final String RUTA_BASE = System.getProperty("catalina.base").substring(0, System.getProperty("catalina.base").lastIndexOf("domain")).concat("bloggestter/");
    public static final String IMG_USUARIO = "usuarios/";
    public static final String IMG_BLOGFONDO = "blog/fondo/";
    public static final String IMG_MULTIMEDIA = "blog/multimedia/";
    private final String CONTENTIMG = "image/jpg";
    private String rutaimg;

    /**
     * Metodo constructor con el cual se crean las carpetas necesarias en caso
     * de que no existan
     */
    public ManejadorArchivos() {
        rutaimg = "";
        this.crearCarpetas();
        this.crearCarpetasHijas();
    }

    /**
     * Metodo con el cual se obtienen las imagenes del portal
     *
     * @param nombre
     * @param tipo
     * @return
     */
    public DefaultStreamedContent obtenerImagen(String nombre, int tipo) {
        File fl = null;
        DefaultStreamedContent contenido = null;
        switch (tipo) {
            case 1:
                fl = new File(RUTA_BASE + IMG_USUARIO + nombre);
                if (fl.exists()) {
                    contenido = this.traerImagen(fl, nombre);
                }
                break;
            case 2:

                break;
            case 3:

                break;
            default:

                break;
        }

        return contenido;
    }

    /**
     * Metodo con el cual se regresa la imagen
     *
     * @return
     */
    private DefaultStreamedContent traerImagen(File fl, String filename) {
        DefaultStreamedContent contenido = null;
        try {
            byte[] arraybyte = Files.readAllBytes(fl.toPath());
            if (arraybyte.length > 0) {
                contenido = new DefaultStreamedContent(new ByteArrayInputStream(arraybyte), CONTENTIMG, filename);
            }
        } catch (IOException ex) {
            Logger.getLogger(ManejadorArchivos.class.getName()).log(Level.SEVERE, "Error en trae imagenes", ex);
        }
        return contenido;
    }

    /**
     * Metodo con el que se crea la carpeta principal
     */
    private void crearCarpetas() {
        Path principal = Paths.get(RUTA_BASE);
        if (!principal.toFile().exists()) {
            principal.toFile().mkdir();
        }
    }

    /**
     * Metodo con el que se crea las carpetas hijas
     */
    private void crearCarpetasHijas() {
        Path blog = Paths.get(RUTA_BASE + "blog/");
        Path blogmultimedia = Paths.get(RUTA_BASE + IMG_MULTIMEDIA);
        Path blogfondo = Paths.get(RUTA_BASE + IMG_BLOGFONDO);
        Path usuario = Paths.get(RUTA_BASE + IMG_USUARIO);
        if (!blog.toFile().exists() && !blogfondo.toFile().exists() && !blogmultimedia.toFile().exists()
                && !usuario.toFile().exists()) {
            blog.toFile().mkdir();
            blogfondo.toFile().mkdir();
            blogmultimedia.toFile().mkdir();
            usuario.toFile().mkdir();
        }
    }

    /**
     * Metodo con el cual se sube la imagen general
     *
     * @param part
     * @param tipo 1-usuario,2-fondo,3-multimedia cualquier otro en raiz
     * @return
     */
    public String subirImagenes(Part part, int tipo) {
        UUID id = UUID.randomUUID();
        switch (tipo) {
            case 1:
                rutaimg = id.toString().split("-")[0] + "-" + part.getSubmittedFileName();
                String nombrefile = RUTA_BASE + IMG_USUARIO + rutaimg;
                try {
                    this.escribirEnDisco(part.getInputStream(), new File(nombrefile).toPath());
                } catch (IOException ex) {
                    Logger.getLogger(ManejadorArchivos.class.getName()).log(Level.SEVERE, "Error en subir imagenes", ex);
                }
                break;
            case 2:

                break;
            case 3:

                break;
            default:

                break;
        }
        return rutaimg;
    }

    /**
     * Metodo con el cual se sube el archivo a la ruta deseada
     *
     * @param is
     * @param path
     */
    private void escribirEnDisco(InputStream is, Path path) {
        try (InputStream aux = is) {
            Files.copy(aux, path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            Logger.getLogger(ManejadorArchivos.class.getName()).log(Level.SEVERE, "Error en subir imagenes", ex);
        }
    }

}
