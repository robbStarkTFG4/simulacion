/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.util.mps;

import java.io.Serializable;
import java.util.Date;
import org.primefaces.model.ScheduleEvent;

/**
 *
 * @author NORE
 */
public class MPSEvent implements ScheduleEvent, Serializable {

    private String id;
    private String title;
    private Date startDate;
    private Date endDate;
    private boolean allDay;
    private String styleClass;
    private ProductionEvent data;
    private boolean editable;
    private String description;

    public MPSEvent() {
    }

    public MPSEvent(String title, Date startDate, Date endDate, ProductionEvent data) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.data = data;
    }

    public MPSEvent(String title, Date startDate, Date endDate) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String string) {
        id = string;
    }

    @Override
    public ProductionEvent getData() {
        return data;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public Date getEndDate() {
        return endDate;
    }

    @Override
    public boolean isAllDay() {
        return allDay;
    }

    @Override
    public String getStyleClass() {
        return null;
    }

    @Override
    public boolean isEditable() {
        return editable;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
