/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.controllers;

import com.mim.mrp.ejb.TblmaterialFacade;
import com.mim.mrp.models.Tblmaterial;
import com.mim.mrp.models.Tblproducto;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 *
 * @author NORE
 */
@Named("mtl")
@ViewScoped
public class MaterialCtrl implements Serializable {

    @EJB
    private TblmaterialFacade mtlFacade;

    private Tblmaterial material = new Tblmaterial();
    private Part file;
    private final String ruta = "C:\\Users\\NORE\\Documents\\GitHub\\simulacion\\robbStark\\mrpMIM\\web\\imagenes\\material\\";

    @PostConstruct
    private void init() {
        System.out.println("material form ignited");
    }

    public void guardarImagen(String nombre, InputStream in) {

        try {
            OutputStream out = new FileOutputStream(new File(ruta + nombre));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();
            System.out.println("Archivo Guardado");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void upload() {
        try {
            System.out.println("da fuck happened?: " + file.getContentType());
            guardarImagen(file.getSubmittedFileName(), file.getInputStream());
            material.setImagen(file.getSubmittedFileName());
            mtlFacade.create(material);
            material = new Tblmaterial();
            //gridCtrl.updateList();
        } catch (Exception e) {
            // Error handling
            System.out.println("hubo algun error");
        }
    }

    //Getters - Setters
    public Tblmaterial getMaterial() {
        return material;
    }

    public void setMaterial(Tblmaterial material) {
        this.material = material;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

}
