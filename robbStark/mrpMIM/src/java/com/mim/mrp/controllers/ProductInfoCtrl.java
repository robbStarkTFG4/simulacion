/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mim.mrp.ejb.TblmaterialFacade;
import com.mim.mrp.ejb.TblrecetaFacade;
import com.mim.mrp.models.Tbldetallereceta;
import com.mim.mrp.models.Tblmaterial;
import com.mim.mrp.models.Tblproducto;
import com.mim.mrp.models.Tblreceta;
import com.mim.mrp.util.diagram.DiagramNode;
import com.mim.mrp.util.diagram.Kinect;
import com.mim.mrp.util.diagram.NetworkElement;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StraightConnector;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;
import org.primefaces.model.diagram.endpoint.RectangleEndPoint;
import org.primefaces.model.diagram.overlay.ArrowOverlay;

/**
 *
 * @author NORE
 */
@Named("prDetail")
@ViewScoped
public class ProductInfoCtrl implements Serializable {

    @Inject
    private ProductHolder catalogCtrl;

    @Inject
    private DiagramCtrl diagram;

    @EJB
    private TblrecetaFacade recetaFacade;

    private Tblproducto selectedProduct;
    private List<Tblreceta> recetaList;
    private DefaultDiagramModel model;
    private List<Kinect> kinectList = null;

    @PostConstruct
    private void init() {
        selectedProduct = catalogCtrl.getCurrent();
        recetaList = recetaFacade.findAll(selectedProduct.getIdTblProducto());
        System.out.println("ProductController init");
    }

    public String gotoDiagram() {
        //diagram.setRecetasList(recetaFacade.findAll2(catalogCtrl.getCurrent().getIdTblProducto()));
        return "diagramaReceta?faces-redirect=true";
    }

    public void diagaramaListener(ActionEvent event) {

        Tblreceta receta = (Tblreceta) event.getComponent().getAttributes().get("selectedReceta");
        System.out.println("receta: " + receta.getDescripcion());

        /*Type type = new TypeToken<List<DataObject1>>() {
         }.getType();
         available = new Gson().fromJson(forInfo.getInfoAdd(), type);*/
        Type type = new TypeToken<List<Kinect>>() {
        }.getType();
        kinectList = new Gson().fromJson(receta.getFabricasion(), type);

        createDiagram(kinectList, receta.getTbldetallerecetaList());
        RequestContext.getCurrentInstance().update("prDetail:diagramaD");
    }

    private void createDiagram(List<Kinect> kinectList, List<Tbldetallereceta> tbldetallerecetaList) {
        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);

        model.getDefaultConnectionOverlays().add(new ArrowOverlay(20, 20, 1, 1));
        StraightConnector connector = new StraightConnector();
        connector.setPaintStyle("{strokeStyle:'#98AFC7', lineWidth:3}");
        connector.setHoverPaintStyle("{strokeStyle:'#5C738B'}");
        model.setDefaultConnector(connector);

        for (Tbldetallereceta dtl : tbldetallerecetaList) {

            DiagramNode computerA = new DiagramNode(new NetworkElement(dtl.getTblMaterialidTblMateria().getNombre(), "computer-icon.png"), "10em", "6em");
            EndPoint endPointCA = createRectangleEndPoint(EndPointAnchor.BOTTOM); // siempre es inicio
            EndPoint endPointCC = createDotEndPoint(EndPointAnchor.TOP);
            endPointCA.setSource(true);
            computerA.setMtl(dtl.getTblMaterialidTblMateria());
            computerA.addEndPoint(endPointCA);
            computerA.addEndPoint(endPointCC);
            //computerA.setX(String.valueOf(pos));
            //computerA.setY(String.valueOf(pos));

            model.addElement(computerA);

        }

        for (Kinect ki : kinectList) {
            DiagramNode nodeFrom = null;
            DiagramNode nodeTo = null;
            for (Element el : model.getElements()) {
                DiagramNode iterator = ((DiagramNode) el);
                if (ki.getIdFrom() == iterator.getMtl().getIdTblMateria()) {
                    nodeFrom = iterator;
                } else if (ki.getIdTo() == iterator.getMtl().getIdTblMateria()) {
                    nodeTo = iterator;
                }
            }
            if ((nodeFrom != null) && (nodeTo != null)) {
                model.connect(new Connection(nodeFrom.getEndPoints().get(0), nodeTo.getEndPoints().get(1)));
            }
        }
    }

    private EndPoint createRectangleEndPoint(EndPointAnchor anchor) {
        RectangleEndPoint endPoint = new RectangleEndPoint(anchor);
        endPoint.setScope("network");
        endPoint.setSource(true);
        endPoint.setStyle("{fillStyle:'#98AFC7'}");
        endPoint.setHoverStyle("{fillStyle:'#5C738B'}");

        return endPoint;
    }

    private EndPoint createDotEndPoint(EndPointAnchor anchor) {
        DotEndPoint endPoint = new DotEndPoint(anchor);
        endPoint.setScope("network");
        endPoint.setTarget(true);
        endPoint.setStyle("{fillStyle:'#98AFC7'}");
        endPoint.setHoverStyle("{fillStyle:'#5C738B'}");

        return endPoint;
    }

    // getters -setters
    public Tblproducto getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Tblproducto selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<Tblreceta> getRecetaList() {
        return recetaList;
    }

    public void setRecetaList(List<Tblreceta> recetaList) {
        this.recetaList = recetaList;
    }

    public DefaultDiagramModel getModel() {
        return model;
    }

    public void setModel(DefaultDiagramModel model) {
        this.model = model;
    }

}
