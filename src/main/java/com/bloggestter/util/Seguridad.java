/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bloggestter.util;

import com.bloggestter.bean.UsuarioBean;
import com.bloggestter.pojos.UsuarioPojo;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ferph
 */
public class Seguridad implements Filter {
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest req = ((HttpServletRequest) request);
        UsuarioPojo u = (UsuarioPojo) req.getSession().getAttribute("usuario");
        if (u == null) {
            String path = req.getContextPath();
            ((HttpServletResponse) response).sendRedirect(path + "/index.bloggestter");
        }
        chain.doFilter(request, response);
    }
    
    @Override
    public void destroy() {
        
    }
    
}
