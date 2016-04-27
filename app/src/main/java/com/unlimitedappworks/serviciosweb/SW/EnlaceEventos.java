/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unlimitedappworks.serviciosweb.SW;

/**
 *
 * @author Luis Fernando
 */
public interface EnlaceEventos {

    public abstract void onRecibir(String datos);

    public abstract void onError(Exception e);
}
