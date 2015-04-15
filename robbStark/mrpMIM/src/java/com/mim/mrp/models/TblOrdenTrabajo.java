/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.models;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author NORE
 */
@Entity
@Table(name = "tbl_orden_trabajo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblOrdenTrabajo.findAll", query = "SELECT t FROM TblOrdenTrabajo t"),
    @NamedQuery(name = "TblOrdenTrabajo.findByIdtblOrdenTrabajo", query = "SELECT t FROM TblOrdenTrabajo t WHERE t.idtblOrdenTrabajo = :idtblOrdenTrabajo"),
    @NamedQuery(name = "TblOrdenTrabajo.findByFechaCaptura", query = "SELECT t FROM TblOrdenTrabajo t WHERE t.fechaCaptura = :fechaCaptura"),
    @NamedQuery(name = "TblOrdenTrabajo.findByFechaInicio", query = "SELECT t FROM TblOrdenTrabajo t WHERE t.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "TblOrdenTrabajo.findByEstatus", query = "SELECT t FROM TblOrdenTrabajo t WHERE t.estatus = :estatus")})
public class TblOrdenTrabajo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtbl_orden_trabajo")
    private Integer idtblOrdenTrabajo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaCaptura")
    @Temporal(TemporalType.DATE)
    private Date fechaCaptura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "estatus")
    private Integer estatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblOrdenTrabajoIdtblOrdenTrabajo")
    private List<TblPlanProducction> tblPlanProducctionList;
    @JoinColumn(name = "TblUsuarios_idTblUsuarios", referencedColumnName = "idTblUsuarios")
    @ManyToOne(optional = false)
    private Tblusuarios tblUsuariosidTblUsuarios;
    @JoinColumn(name = "TblProducto_idTblProducto", referencedColumnName = "idTblProducto")
    @ManyToOne(optional = false)
    private Tblproducto tblProductoidTblProducto;
    @JoinColumn(name = "TblOrdencliente_idTblOrdencliente", referencedColumnName = "idTblOrdencliente")
    @ManyToOne(optional = false)
    private Tblordencliente tblOrdenclienteidTblOrdencliente;

    public TblOrdenTrabajo() {
    }

    public TblOrdenTrabajo(Integer idtblOrdenTrabajo) {
        this.idtblOrdenTrabajo = idtblOrdenTrabajo;
    }

    public TblOrdenTrabajo(Integer idtblOrdenTrabajo, Date fechaCaptura, Date fechaInicio) {
        this.idtblOrdenTrabajo = idtblOrdenTrabajo;
        this.fechaCaptura = fechaCaptura;
        this.fechaInicio = fechaInicio;
    }

    public Integer getIdtblOrdenTrabajo() {
        return idtblOrdenTrabajo;
    }

    public void setIdtblOrdenTrabajo(Integer idtblOrdenTrabajo) {
        this.idtblOrdenTrabajo = idtblOrdenTrabajo;
    }

    public Date getFechaCaptura() {
        return fechaCaptura;
    }

    public void setFechaCaptura(Date fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    @XmlTransient
    public List<TblPlanProducction> getTblPlanProducctionList() {
        return tblPlanProducctionList;
    }

    public void setTblPlanProducctionList(List<TblPlanProducction> tblPlanProducctionList) {
        this.tblPlanProducctionList = tblPlanProducctionList;
    }

    public Tblusuarios getTblUsuariosidTblUsuarios() {
        return tblUsuariosidTblUsuarios;
    }

    public void setTblUsuariosidTblUsuarios(Tblusuarios tblUsuariosidTblUsuarios) {
        this.tblUsuariosidTblUsuarios = tblUsuariosidTblUsuarios;
    }

    public Tblproducto getTblProductoidTblProducto() {
        return tblProductoidTblProducto;
    }

    public void setTblProductoidTblProducto(Tblproducto tblProductoidTblProducto) {
        this.tblProductoidTblProducto = tblProductoidTblProducto;
    }

    public Tblordencliente getTblOrdenclienteidTblOrdencliente() {
        return tblOrdenclienteidTblOrdencliente;
    }

    public void setTblOrdenclienteidTblOrdencliente(Tblordencliente tblOrdenclienteidTblOrdencliente) {
        this.tblOrdenclienteidTblOrdencliente = tblOrdenclienteidTblOrdencliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtblOrdenTrabajo != null ? idtblOrdenTrabajo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblOrdenTrabajo)) {
            return false;
        }
        TblOrdenTrabajo other = (TblOrdenTrabajo) object;
        if ((this.idtblOrdenTrabajo == null && other.idtblOrdenTrabajo != null) || (this.idtblOrdenTrabajo != null && !this.idtblOrdenTrabajo.equals(other.idtblOrdenTrabajo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.mrp.models.TblOrdenTrabajo[ idtblOrdenTrabajo=" + idtblOrdenTrabajo + " ]";
    }
    
}
