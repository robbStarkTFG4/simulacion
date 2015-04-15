/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.controllers;

import com.mim.mrp.ejb.TblproductoFacade;
import com.mim.mrp.models.Tblproducto;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Scanner;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 *
 * @author NORE
 */
@Named("productForm")
@ViewScoped
public class NewProductCtrl implements Serializable {

    private Part file;
    private String fileContent;
    private Tblproducto newbi = new Tblproducto();
    private final String ruta = "C:\\Users\\NORE\\Documents\\GitHub\\simulacion\\robbStark\\mrpMIM\\web\\imagenes\\productos\\";

    @Inject
    ProductHolder gridCtrl;

    @EJB
    private TblproductoFacade productFacade;

    @PostConstruct
    private void init() {
        //conver.begin();
    }

    public void handleFileUpload(AjaxBehaviorEvent event) {
        System.out.println("file size: " + file.getSize());
        System.out.println("file type: " + file.getContentType());
        System.out.println("file info: " + file.getHeader("Content-Disposition"));
    }

    public void upload() {
        try {
            System.out.println("da fuck happened?: " + file.getContentType());
            guardarImagen(file.getSubmittedFileName(), file.getInputStream());
            newbi.setImagen(file.getSubmittedFileName());
            productFacade.create(newbi);
            newbi = new Tblproducto();
            //gridCtrl.updateList();
        } catch (Exception e) {
            // Error handling
        }
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

//getters- setters
    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public Tblproducto getNewbi() {
        return newbi;
    }

    public void setNewbi(Tblproducto newbi) {
        this.newbi = newbi;
    }

}
