/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.controllers;

import com.mim.mrp.ejb.TbldetallerecetaFacade;
import com.mim.mrp.ejb.TblmaterialFacade;
import com.mim.mrp.ejb.TblrecetaFacade;
import com.mim.mrp.models.Tbldetallereceta;
import com.mim.mrp.models.Tblmaterial;
import com.mim.mrp.models.Tblproducto;
import com.mim.mrp.models.Tblreceta;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.DragDropEvent;

/**
 *
 * @author NORE
 */
@Named("receta")
@ViewScoped
public class RecetaCreateCtrl implements Serializable {

    @EJB
    private TblmaterialFacade mtlFacade;
    @EJB
    private TbldetallerecetaFacade dtlFacade;
    @EJB
    private TblrecetaFacade recetaFacade;
    @Inject
    ProductHolder catalog;

    private List<Tblmaterial> mtlList;
    private List<Tbldetallereceta> selectedList;
    private Tblproducto producto;
    private Tblreceta createReceta = new Tblreceta();
    private Tblmaterial currentMtl;

    @PostConstruct
    private void init() {
        mtlList = mtlFacade.findAll();
        selectedList = new ArrayList<>();
        producto = catalog.getCurrent();
    }

    public void onCarDrop(DragDropEvent ddEvent) {
        Tblmaterial car = ((Tblmaterial) ddEvent.getData());

        boolean add = false;
        for (Tbldetallereceta mtlList1 : selectedList) {
            if ((car.getIdTblMateria().intValue() == mtlList1.getTblMaterialidTblMateria().getIdTblMateria().intValue())) {
                add = true;
            }
        }
        if (!add) {
            //System.out.println("agregar");
            Tbldetallereceta detalle = new Tbldetallereceta();
            detalle.setTblMaterialidTblMateria(car);
            selectedList.add(detalle);
        } else {
            //System.out.println("no agregar");
        }

    }

    public void guardar() {
        createReceta.setTblProductoidTblProducto(catalog.getCurrent());
        //createReceta.setTbldetallerecetaList(selectedList);
        Tblreceta temp = recetaFacade.createReceta(createReceta);
        for (Tbldetallereceta dt : selectedList) {
            System.out.println("id receta: " + temp.getIdTblReceta());
            System.out.println("material: " + dt.getTblMaterialidTblMateria().getNombre());
            dt.setTblRecetaidTblReceta(createReceta);

            dtlFacade.createDtl(dt);
        }
        createReceta = new Tblreceta();
        selectedList.clear();
    }

    // Getters -Setters
    public List<Tblmaterial> getMtlList() {
        return mtlList;
    }

    public void setMtlList(List<Tblmaterial> mtlList) {
        this.mtlList = mtlList;
    }

    public List<Tbldetallereceta> getSelectedList() {
        return selectedList;
    }

    public void setSelectedList(List<Tbldetallereceta> selectedList) {
        this.selectedList = selectedList;
    }

    public Tblreceta getCreateReceta() {
        return createReceta;
    }

    public void setCreateReceta(Tblreceta createReceta) {
        this.createReceta = createReceta;
    }

    public Tblmaterial getCurrentMtl() {
        return currentMtl;
    }

    public void setCurrentMtl(Tblmaterial currentMtl) {
        this.currentMtl = currentMtl;
    }

}
