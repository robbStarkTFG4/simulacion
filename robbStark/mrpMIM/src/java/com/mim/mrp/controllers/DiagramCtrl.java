/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.controllers;

import com.google.gson.Gson;
import com.mim.mrp.ejb.TblrecetaFacade;
import com.mim.mrp.models.Tbldetallereceta;
import com.mim.mrp.models.Tblreceta;
import com.mim.mrp.util.diagram.DiagramNode;
import com.mim.mrp.util.diagram.Kinect;
import com.mim.mrp.util.diagram.NetworkElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.diagram.ConnectEvent;
import org.primefaces.event.diagram.ConnectionChangeEvent;
import org.primefaces.event.diagram.DisconnectEvent;
import org.primefaces.model.diagram.DefaultDiagramModel;
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
@Named("diagram")
@ViewScoped
public class DiagramCtrl implements Serializable {

    @Inject
    ProductHolder catalogCtrl;

    @EJB
    TblrecetaFacade recetaFacade;

    private List<Tblreceta> recetasList;
    private Tblreceta current;
    private DefaultDiagramModel model;
    private boolean suspendEvent;
    private List<Kinect> kinectList = new ArrayList<>();

    @PostConstruct
    private void init() {
        recetasList = recetaFacade.findAll3(catalogCtrl.getCurrent().getIdTblProducto());
    }

    public void recetaListener(ValueChangeEvent e) {
        //System.out.println("HOLAAAAAAAAAAAAAA");
        Tblreceta temp = (Tblreceta) e.getNewValue();
        current = temp;
        //System.out.println("descripcion: " + temp.getDescripcion());
        initDiagram(temp.getTbldetallerecetaList());
    }

    private void initDiagram(List<Tbldetallereceta> tbldetallerecetaList) {
        model = new DefaultDiagramModel();
        model.setMaxConnections(-1);

        model.getDefaultConnectionOverlays().add(new ArrowOverlay(20, 20, 1, 1));
        StraightConnector connector = new StraightConnector();
        connector.setPaintStyle("{strokeStyle:'#98AFC7', lineWidth:3}");
        connector.setHoverPaintStyle("{strokeStyle:'#5C738B'}");
        model.setDefaultConnector(connector);

        /*DiagramNode product = new DiagramNode(new NetworkElement(current.getTblProductoidTblProducto().getNombre()), "10em", "6em");
         EndPoint endPointCA2 = createRectangleEndPoint(EndPointAnchor.BOTTOM);
         EndPoint endPointCC2 = createDotEndPoint(EndPointAnchor.TOP);
         endPointCA2.setSource(true);

         product.addEndPoint(endPointCA2);
         product.addEndPoint(endPointCC2);
         model.addElement(product);*/
        for (Tbldetallereceta dtl : tbldetallerecetaList) {

            DiagramNode computerA = new DiagramNode(new NetworkElement(dtl.getTblMaterialidTblMateria().getNombre()), "10em", "6em");
            EndPoint endPointCA = createRectangleEndPoint(EndPointAnchor.BOTTOM);
            EndPoint endPointCC = createDotEndPoint(EndPointAnchor.TOP);
            endPointCA.setSource(true);
            computerA.setMtl(dtl.getTblMaterialidTblMateria());
            computerA.addEndPoint(endPointCA);
            computerA.addEndPoint(endPointCC);
            model.addElement(computerA);

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

    public void onConnect(ConnectEvent event) {
        if (!suspendEvent) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Connected",
                    "From " + event.getSourceElement().getData() + " To " + event.getTargetElement().getData());
            System.out.println(((DiagramNode) event.getSourceElement()).getMtl().getNombre());
            System.out.println(((DiagramNode) event.getTargetElement()).getMtl().getNombre());

            FacesContext.getCurrentInstance().addMessage(null, msg);

            //RequestContext.getCurrentInstance().update("form:msgs");
            Kinect ki = new Kinect(((DiagramNode) event.getSourceElement()).getMtl().getIdTblMateria(), ((DiagramNode) event.getTargetElement()).getMtl().getIdTblMateria());
            ki.setDataFrom((NetworkElement) event.getSourceElement().getData());
            ki.setDataTo((NetworkElement) event.getTargetElement().getData());
            kinectList.add(ki);
        } else {
            suspendEvent = false;
        }
    }

    public void onDisconnect(DisconnectEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Disconnected",
                "From " + event.getSourceElement().getData() + " To " + event.getTargetElement().getData());

        FacesContext.getCurrentInstance().addMessage(null, msg);

        //RequestContext.getCurrentInstance().update("form:msgs");
        int from = ((DiagramNode) event.getSourceElement()).getMtl().getIdTblMateria();
        int to = ((DiagramNode) event.getTargetElement()).getMtl().getIdTblMateria();
        Kinect kin = null;
        for (Kinect ki : kinectList) {
            if ((ki.getIdFrom() == from) && (ki.getIdTo() == to)) {
                kin = ki;
                break;
            }
        }
        kinectList.remove(kin);
    }

    public void onConnectionChange(ConnectionChangeEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Connection Changed",
                "Original Source:" + event.getOriginalSourceElement().getData()
                + ", New Source: " + event.getNewSourceElement().getData()
                + ", Original Target: " + event.getOriginalTargetElement().getData()
                + ", New Target: " + event.getNewTargetElement().getData());

        FacesContext.getCurrentInstance().addMessage(null, msg);

        //RequestContext.getCurrentInstance().update("form:msgs");
        suspendEvent = true;

        int fromOld = ((DiagramNode) event.getOriginalSourceElement()).getMtl().getIdTblMateria();
        int toOld = ((DiagramNode) event.getOriginalTargetElement()).getMtl().getIdTblMateria();

        int fromNew = ((DiagramNode) event.getNewSourceElement()).getMtl().getIdTblMateria();
        int toNew = ((DiagramNode) event.getNewTargetElement()).getMtl().getIdTblMateria();

        for (Kinect ki : kinectList) {
            if ((ki.getIdFrom() == fromOld) && (ki.getIdTo() == toOld)) {
                ki.setIdFrom(fromNew);
                ki.setIdTo(toNew);
                ki.setDataFrom((NetworkElement) event.getNewSourceElement().getData());
                ki.setDataTo((NetworkElement) event.getNewTargetElement().getData());
                break;
            }
        }
    }

    public void saveDiagram() {
        //System.out.println("roberth baratheon");
        /*for (Kinect ki : kinectList) {
         System.out.println("from: " + ki.getIdFrom() + " to: " + ki.getIdTo());
         }*/
        Gson gson = new Gson();

        recetaFacade.saveDiagram(current.getIdTblReceta(), gson.toJson(kinectList));
        model.getElements().clear();
        current = null;
    }

    private void actListener(ActionEvent e) {
        System.out.println("hola desde acciones");
    }
    //Getter - Setter

    public List<Tblreceta> getRecetasList() {
        return recetasList;
    }

    public void setRecetasList(List<Tblreceta> recetasList) {
        this.recetasList = recetasList;
    }

    public Tblreceta getCurrent() {
        return current;
    }

    public void setCurrent(Tblreceta current) {
        this.current = current;
    }

    public DefaultDiagramModel getModel() {
        return model;
    }

    public void setModel(DefaultDiagramModel model) {
        this.model = model;
    }

}
