/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.controllers;

import com.mim.mrp.ejb.TblOrdenTrabajoFacade;
import com.mim.mrp.ejb.TblOrdencompraFacade;
import com.mim.mrp.ejb.TblPlanProducctionFacade;
import com.mim.mrp.ejb.TblProduccionActividadFacade;
import com.mim.mrp.ejb.TblordenclienteFacade;
import com.mim.mrp.ejb.TblusuariosFacade;
import com.mim.mrp.models.TblOrdenTrabajo;
import com.mim.mrp.models.TblOrdencompra;
import com.mim.mrp.models.TblPlanProducction;
import com.mim.mrp.models.TblProduccionActividad;
import com.mim.mrp.models.Tblordencliente;
import com.mim.mrp.models.Tblusuarios;
import com.mim.mrp.util.mps.MPSEvent;
import com.mim.mrp.util.mps.ProductionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author NORE
 */
@Named("scheduleMps")
@ViewScoped
public class MpsScheduleCtrl implements Serializable {

    @EJB
    TblOrdencompraFacade ordenCompraFacade;

    @EJB
    TblusuariosFacade usuarioFacade;

    @EJB
    TblOrdenTrabajoFacade trabajoFacade;

    @EJB
    TblPlanProducctionFacade planFacade;

    @EJB
    TblProduccionActividadFacade actividadFacade;

    @Inject
    OrdersHolder orderHolder;

    @Inject
    LayoutCtrl layout;

    private ScheduleModel eventModel;
    private Tblordencliente ordenCliente;
    private List<TblOrdencompra> comprasList;
    private ScheduleEvent event = new DefaultScheduleEvent();
    private String hi;
    private String obj;
    private String colorText = "#0000ff "; //good: #0000ff , bad: d83737
    private boolean stark = false;
    private TblOrdenTrabajo trabajo = new TblOrdenTrabajo();
    private TblPlanProducction produccion = new TblPlanProducction();
    private List<TblProduccionActividad> mpsActivity = new ArrayList<>();

    @PostConstruct
    private void init() {
        System.out.println("ignited MPS schedule");
        ordenCliente = orderHolder.getCurrentClientOrder();
        obj = String.valueOf(ordenCliente.getCantidad());
        comprasList = ordenCompraFacade.findAll(ordenCliente.getIdTblOrdencliente());

        //trabajo.setTblUsuariosidTblUsuarios(usuarioFacade.findUser(layout.getUsuario(), layout.getPassword()));
        trabajo.setTblUsuariosidTblUsuarios(usuarioFacade.find(1));
        trabajo.setTblOrdenclienteidTblOrdencliente(ordenCliente);
        trabajo.setFechaCaptura(new Date());
        trabajo.setTblProductoidTblProducto(ordenCliente.getTblProductoidTblProducto());
        trabajo.setEstatus(0);

        produccion.setTblOrdenclienteidTblOrdencliente(ordenCliente);

        initModel(comprasList, ordenCliente);
    }

    private void initModel(List<TblOrdencompra> comprasList, Tblordencliente ordenCliente) {
        eventModel = new DefaultScheduleModel();
        ProductionEvent p1 = new ProductionEvent();
        p1.setOrdenCliente(ordenCliente);
        ScheduleEvent fechaCompra = new DefaultScheduleEvent("fecha compra cliente", ordenCliente.getFechacaptura(), ordenCliente.getFechacaptura(), p1);
        ((DefaultScheduleEvent) fechaCompra).setEditable(false);

        ProductionEvent p2 = new ProductionEvent();
        p2.setOrdenCliente(ordenCliente);
        ScheduleEvent fechaVencimiento = new DefaultScheduleEvent("fecha entrega cliente", ordenCliente.getFechadeentrega(), ordenCliente.getFechadeentrega(), p2);

        eventModel.addEvent(fechaCompra);
        eventModel.addEvent(fechaVencimiento);

        for (TblOrdencompra cp : comprasList) {
            ProductionEvent p3 = new ProductionEvent();
            p3.setOrdenCompra(cp);
            ScheduleEvent llegadaMat = new DefaultScheduleEvent(cp.getTblmaterial().getNombre(), cp.getFechaEntrega(), cp.getFechaEntrega(), p3);
            eventModel.addEvent(llegadaMat);
        }
    }

    public void addEvent(ActionEvent actionEvent) {
        //System.out.println("HOLAAAAAAAAAAAAAAAAAAAAA!!!!!!!!!!!");

        int cantidad = calcula();

        if (event.getId() == null) {

            cantidad = cantidad + Integer.parseInt(event.getTitle());
            ((DefaultScheduleEvent) event).setEditable(true);
            eventModel.addEvent(event);

        } else {

            //System.out.println("i dont give a fuck: "+((DefaultScheduleEvent)actionEvent.getSource()).getTitle());
            //cantidad = cantidad + Integer.parseInt(event.getTitle());
            eventModel.updateEvent(event);
            cantidad = calcula();

        }

        hi = String.valueOf(cantidad);
        if (cantidad > Integer.parseInt(obj)) {
            stark = false;
            colorText = "#d83737";
        } else if (cantidad == Integer.parseInt(obj)) {
            colorText = "#0000ff";
            stark = true;
        } else if (cantidad < Integer.parseInt(obj)) {
            colorText = "#0000ff";
            stark = false;
        }
        RequestContext.getCurrentInstance().update("mpsForm:ctf");
        //oncomplete="PF('myschedule').update();PF('eventDialog').hide();"
        RequestContext.getCurrentInstance().execute("PF('myschedule').update();PF('eventDialog').hide();");
        event = new DefaultScheduleEvent();
    }

    public void onEventSelect(SelectEvent selectEvent) {
        //System.out.println("EVENTO SELECCIONADO");
        event = (ScheduleEvent) selectEvent.getObject();
        if (event.getData() == null) {
            RequestContext.getCurrentInstance().execute("PF('eventDialog').show();");
        }
    }

    public void deleteEvent(ActionEvent e) {
        eventModel.getEvents().remove(event);
        RequestContext.getCurrentInstance().execute("PF('myschedule').update();PF('eventDialog').hide();");
        hi = String.valueOf(calcula());
        RequestContext.getCurrentInstance().update("mpsForm:ctf");
    }

    public void onDateSelect(SelectEvent selectEvent) {
        Date dt = (Date) selectEvent.getObject();
        boolean add = false;
        for (ScheduleEvent sh : eventModel.getEvents()) {
            if (sh.getData() != null) {
                if (((ProductionEvent) sh.getData()).getOrdenCliente() != null) {
                    if (((ProductionEvent) sh.getData()).getOrdenCliente().getFechacaptura().after(dt)) {
                        add = true;
                        //System.out.println("invalida");
                    }

                    if (((ProductionEvent) sh.getData()).getOrdenCliente().getFechadeentrega().before(dt)) {
                        add = true;
                        //System.out.println("invalida");
                    }
                }

                if (((ProductionEvent) sh.getData()).getOrdenCompra() != null) {
                    //System.out.println("checar orden compra");
                    if (((ProductionEvent) sh.getData()).getOrdenCompra().getFechaEntrega().after(dt)) {
                        add = true;
                        //System.out.println("invalida");
                    } else {
                        //System.out.println("da fuck?");
                    }
                } else {
                    //System.out.println("this shit is null");
                }
            }
        }

        if (!add) {
            //System.out.println("ESSSSSSS VALIDAAAAAAAAAAAAA");
            ProductionEvent pe = new ProductionEvent();
            event = new DefaultScheduleEvent("", dt, dt);
            //RequestContext.getCurrentInstance().update("eventDetails");
            RequestContext.getCurrentInstance().execute("PF('eventDialog').show();");
        }
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private int calcula() {
        int cantidad = 0;
        for (ScheduleEvent evn : eventModel.getEvents()) {
            if (((DefaultScheduleEvent) evn).getData() == null) {
                //System.out.println(evn.getTitle());
                cantidad = cantidad + Integer.parseInt(evn.getTitle());
            }
        }
        return cantidad;
    }

    public String guardarPlan() {
        if (stark) {
            //System.out.println("SAVE DATA!!!!");

            SortedSet<Date> dates = new TreeSet<>();
            for (ScheduleEvent ev : eventModel.getEvents()) {
                if (((DefaultScheduleEvent) ev).getData() == null) {
                    //System.out.println("FECHAS: " + ((DefaultScheduleEvent) ev).getStartDate());
                    dates.add(((DefaultScheduleEvent) ev).getStartDate());
                    //TblProduccionActividad( Date fecha, int cantidad)
                    int cantidad = Integer.parseInt(((DefaultScheduleEvent) ev).getTitle());
                    mpsActivity.add(new TblProduccionActividad(((DefaultScheduleEvent) ev).getStartDate(), cantidad));
                }
            }
            //System.out.println("IS THIS WHAT YOURE LOOKING FOR????: " + dates.first());
            trabajo.setFechaInicio(dates.first());
            produccion.setTblOrdenTrabajoIdtblOrdenTrabajo(trabajoFacade.find(trabajoFacade.createOrder(trabajo)));
            int id = planFacade.createMPS(produccion);

            //PERSIST ACTIVIDAD LIST HERE
            for (TblProduccionActividad act : mpsActivity) {
                act.setTblPlanProducctionIdtblPlanProducction(planFacade.find(id));
                actividadFacade.create(act);
            }
        }
        return "estatusCompras?faces-redirect=true";
    }

    //Getter - Setter
    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public String getHi() {
        return hi;
    }

    public void setHi(String hi) {
        this.hi = hi;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(MPSEvent event) {
        this.event = event;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public String getColorText() {
        return colorText;
    }

    public void setColorText(String colorText) {
        this.colorText = colorText;
    }

}
