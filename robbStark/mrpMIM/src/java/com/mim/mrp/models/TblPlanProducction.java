/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author NORE
 */
@Entity
@Table(name = "tbl_plan_producction")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblPlanProducction.findAll", query = "SELECT t FROM TblPlanProducction t"),
    @NamedQuery(name = "TblPlanProducction.findByIdtblPlanProducction", query = "SELECT t FROM TblPlanProducction t WHERE t.idtblPlanProducction = :idtblPlanProducction")})
public class TblPlanProducction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtbl_plan_producction")
    private Integer idtblPlanProducction;
    @JoinColumn(name = "TblOrdencliente_idTblOrdencliente", referencedColumnName = "idTblOrdencliente")
    @ManyToOne(optional = false)
    private Tblordencliente tblOrdenclienteidTblOrdencliente;
    @JoinColumn(name = "tbl_orden_trabajo_idtbl_orden_trabajo", referencedColumnName = "idtbl_orden_trabajo")
    @ManyToOne(optional = false)
    private TblOrdenTrabajo tblOrdenTrabajoIdtblOrdenTrabajo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblPlanProducctionIdtblPlanProducction")
    private List<TblProduccionActividad> tblProduccionActividadList;

    public TblPlanProducction() {
    }

    public TblPlanProducction(Integer idtblPlanProducction) {
        this.idtblPlanProducction = idtblPlanProducction;
    }

    public Integer getIdtblPlanProducction() {
        return idtblPlanProducction;
    }

    public void setIdtblPlanProducction(Integer idtblPlanProducction) {
        this.idtblPlanProducction = idtblPlanProducction;
    }

    public Tblordencliente getTblOrdenclienteidTblOrdencliente() {
        return tblOrdenclienteidTblOrdencliente;
    }

    public void setTblOrdenclienteidTblOrdencliente(Tblordencliente tblOrdenclienteidTblOrdencliente) {
        this.tblOrdenclienteidTblOrdencliente = tblOrdenclienteidTblOrdencliente;
    }

    public TblOrdenTrabajo getTblOrdenTrabajoIdtblOrdenTrabajo() {
        return tblOrdenTrabajoIdtblOrdenTrabajo;
    }

    public void setTblOrdenTrabajoIdtblOrdenTrabajo(TblOrdenTrabajo tblOrdenTrabajoIdtblOrdenTrabajo) {
        this.tblOrdenTrabajoIdtblOrdenTrabajo = tblOrdenTrabajoIdtblOrdenTrabajo;
    }

    @XmlTransient
    public List<TblProduccionActividad> getTblProduccionActividadList() {
        return tblProduccionActividadList;
    }

    public void setTblProduccionActividadList(List<TblProduccionActividad> tblProduccionActividadList) {
        this.tblProduccionActividadList = tblProduccionActividadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtblPlanProducction != null ? idtblPlanProducction.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblPlanProducction)) {
            return false;
        }
        TblPlanProducction other = (TblPlanProducction) object;
        if ((this.idtblPlanProducction == null && other.idtblPlanProducction != null) || (this.idtblPlanProducction != null && !this.idtblPlanProducction.equals(other.idtblPlanProducction))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.mrp.models.TblPlanProducction[ idtblPlanProducction=" + idtblPlanProducction + " ]";
    }
    
}
