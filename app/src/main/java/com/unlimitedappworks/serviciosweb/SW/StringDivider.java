/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unlimitedappworks.serviciosweb.SW;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luis Fernando
 */
public class StringDivider {

    private String datos, inicio, fin;
    private int indice;
    private List<Object> lista;

    public StringDivider(String datos, String inicio, String fin) {
        this.datos = datos;
        this.inicio = inicio;
        this.fin = fin;
        init();
    }

    private void init() {
        indice = 0;
        lista = new ArrayList<>();
        String elemento;
        while ((elemento = getDivString()) != null) {
            lista.add(elemento);
        }
    }

    private String getDivString() {
        int a = datos.indexOf(inicio, indice);
        int b = datos.indexOf(fin, a + 1) + 1;
        if (!inicio.equals(fin)) {
            int c = datos.indexOf(inicio, a + 1);
            while (c < b && c >= 0) {
                c = datos.indexOf(inicio, c + 1);
                b = datos.indexOf(fin, b + 1) + 1;
            }
        }
        indice = b + 1;
        if (a <= -1 || b <= -1) {
            return null;
        }
        return datos.substring(a, b);
    }

    public String getDatos() {
        return datos;
    }

    public void setDatos(String datos) {
        this.datos = datos;
        init();
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
        init();
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
        init();
    }

    public List<Object> getLista() {
        return lista;
    }

}
