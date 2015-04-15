/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.util.diagram;

/**
 *
 * @author NORE
 */
public class Kinect {

    private int idFrom;
    private int idTo;
    private String descripcion;
    private NetworkElement dataFrom;
    private NetworkElement dataTo;

    public Kinect() {
    }

    public Kinect(int idFrom, int idTo) {
        this.idFrom = idFrom;
        this.idTo = idTo;
    }

    public int getIdFrom() {
        return idFrom;
    }

    public void setIdFrom(int idFrom) {
        this.idFrom = idFrom;
    }

    public int getIdTo() {
        return idTo;
    }

    public void setIdTo(int idTo) {
        this.idTo = idTo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Kinect[idTo=" + idTo + ", idFrom=" + idFrom + ", descripcion=" + descripcion + "]"; //To change body of generated methods, choose Tools | Templates.
    }

    public void setDataFrom(NetworkElement networkElement) {
        this.dataFrom = networkElement;
    }

    public void setDataTo(NetworkElement networkElement) {
        this.dataTo = networkElement;
    }

}
