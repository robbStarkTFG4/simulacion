/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.util.abc;

import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author NORE
 */
public class AbcModel implements Serializable, Comparator<AbcModel> {

    private int id;
    private String nombre;
    private int volumenUnidades;
    private double costoUnitario;
    private double volumenDolares;
    private double volumenPorcientoDlls;
    private String clase;

    public AbcModel() {
    }

    public AbcModel(int id, int volumenUnidades, double costoUnitario, double volumenDolares) {
        this.id = id;
        this.volumenUnidades = volumenUnidades;
        this.costoUnitario = costoUnitario;
        this.volumenDolares = volumenDolares;
    }

    public AbcModel(int id, String nombre, int volumenUnidades, double costoUnitario, double volumenDolares) {
        this.id = id;
        this.nombre = nombre;
        this.volumenUnidades = volumenUnidades;
        this.costoUnitario = costoUnitario;
        this.volumenDolares = volumenDolares;

    }

    public AbcModel(int id, String nombre, int volumenUnidades, double costoUnitario, double volumenDolares, double volumenPorcientoDlls) {
        this.id = id;
        this.nombre = nombre;
        this.volumenUnidades = volumenUnidades;
        this.costoUnitario = costoUnitario;
        this.volumenDolares = volumenDolares;
        this.volumenPorcientoDlls = volumenPorcientoDlls;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVolumenUnidades() {
        return volumenUnidades;
    }

    public void setVolumenUnidades(int volumenUnidades) {
        this.volumenUnidades = volumenUnidades;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public double getVolumenDolares() {
        return volumenDolares;
    }

    public void setVolumenDolares(double volumenDolares) {
        this.volumenDolares = volumenDolares;
    }

    public double getVolumenPorcientoDlls() {
        return volumenPorcientoDlls;
    }

    public void setVolumenPorcientoDlls(double volumenPorcientoDlls) {
        this.volumenPorcientoDlls = volumenPorcientoDlls;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int compare(AbcModel o1, AbcModel o2) {
        return Double.compare(o1.getVolumenDolares(), o2.getVolumenDolares());
    }

}
