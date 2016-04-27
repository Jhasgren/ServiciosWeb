/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unlimitedappworks.serviciosweb.SW;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Luis Fernando
 */
public class Enlace extends EnlaceAdapter {

    private String direccion;
    private List<EnlaceEventos> enlaceEventos;
    private Thread hilo;

    public Enlace(String direccion) {
        this.direccion = direccion;
        enlaceEventos = new ArrayList<>();
        hilo = new Thread() {
            long ti;

            @Override
            public void run() {
                try {
                    URLConnection c = new URL(Enlace.this.direccion).openConnection();
                    c.setConnectTimeout(5000);
                    InputStream is = (InputStream) c.getContent();
                    StringBuilder sb = new StringBuilder();
                    ti = System.currentTimeMillis();
                    while (System.currentTimeMillis() - ti < 500) {
                        if (is.available() > 0) {
                            byte[] buffer = new byte[is.available()];
                            is.read(buffer);
                            sb.append(new String(buffer, Charset.forName("utf-8")));
                            ti = System.currentTimeMillis();
                        }
                    }
                    onRecibir(sb.toString());
                    for (EnlaceEventos eventos : enlaceEventos) {
                        eventos.onRecibir(sb.toString());
                    }
                } catch (Exception ex) {
                    onError(ex);
                    for (EnlaceEventos eventos : enlaceEventos) {
                        eventos.onError(ex);
                    }
                }
            }

        };

    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Enlace navegar() {
        hilo.start();
        return this;
    }

}
