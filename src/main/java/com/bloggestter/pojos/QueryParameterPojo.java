/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloggestter.pojos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Familia
 */
public class QueryParameterPojo implements Serializable {

    private int tipo;
    private Object obj;
    private int posicion;

    public QueryParameterPojo() {

    }

    public QueryParameterPojo(int tipo, Object obj, int posicion) {
        this.obj = obj;
        this.tipo = tipo;
        this.posicion = posicion;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public static boolean convertirABoolean(QueryParameterPojo pojo) {
        return (boolean) pojo.obj;
    }

    public static int convertirAEntero(QueryParameterPojo pojo) {
        return (int) pojo.obj;
    }

    public static String convertirATexto(QueryParameterPojo pojo) {
        return (String) pojo.obj;
    }

    public static Date convertirAFecha(QueryParameterPojo pojo) {
        return (Date) pojo.obj;
    }
}
