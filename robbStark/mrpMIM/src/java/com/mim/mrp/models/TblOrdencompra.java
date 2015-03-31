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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "tbl_ordencompra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblOrdencompra.findAll", query = "SELECT t FROM TblOrdencompra t"),
    @NamedQuery(name = "TblOrdencompra.findByIdtblordenCompra", query = "SELECT t FROM TblOrdencompra t WHERE t.tblOrdencompraPK.idtblordenCompra = :idtblordenCompra"),
    @NamedQuery(name = "TblOrdencompra.findByTblMaterialidTblMateria", query = "SELECT t FROM TblOrdencompra t WHERE t.tblOrdencompraPK.tblMaterialidTblMateria = :tblMaterialidTblMateria"),
    @NamedQuery(name = "TblOrdencompra.findByCantidad", query = "SELECT t FROM TblOrdencompra t WHERE t.cantidad = :cantidad"),
    @NamedQuery(name = "TblOrdencompra.findByMonto", query = "SELECT t FROM TblOrdencompra t WHERE t.monto = :monto"),
    @NamedQuery(name = "TblOrdencompra.findByIva", query = "SELECT t FROM TblOrdencompra t WHERE t.iva = :iva"),
    @NamedQuery(name = "TblOrdencompra.findByEstatus", query = "SELECT t FROM TblOrdencompra t WHERE t.estatus = :estatus"),
    @NamedQuery(name = "TblOrdencompra.findByTblOrdenclienteidTblOrdencliente", query = "SELECT t FROM TblOrdencompra t WHERE t.tblOrdencompraPK.tblOrdenclienteidTblOrdencliente = :tblOrdenclienteidTblOrdencliente"),
    @NamedQuery(name = "TblOrdencompra.findByFechaCompra", query = "SELECT t FROM TblOrdencompra t WHERE t.fechaCompra = :fechaCompra"),
    @NamedQuery(name = "TblOrdencompra.findByFechaEntrega", query = "SELECT t FROM TblOrdencompra t WHERE t.fechaEntrega = :fechaEntrega")})
public class TblOrdencompra implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TblOrdencompraPK tblOrdencompraPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "monto")
    private double monto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "iva")
    private double iva;
    @Column(name = "estatus")
    private Integer estatus;
    @Column(name = "fechaCompra")
    @Temporal(TemporalType.DATE)
    private Date fechaCompra;
    @Column(name = "fechaEntrega")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    @JoinColumn(name = "tbl_proveedores_idtbl_proveedores", referencedColumnName = "idtbl_proveedores")
    @ManyToOne(optional = false)
    private TblProveedores tblProveedoresIdtblProveedores;
    @JoinColumn(name = "TblUsuarios_idTblUsuarios", referencedColumnName = "idTblUsuarios")
    @ManyToOne(optional = false)
    private Tblusuarios tblUsuariosidTblUsuarios;
    @JoinColumn(name = "TblMaterial_idTblMateria", referencedColumnName = "idTblMateria", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tblmaterial tblmaterial;
    @JoinColumn(name = "TblOrdencliente_idTblOrdencliente", referencedColumnName = "idTblOrdencliente", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Tblordencliente tblordencliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblOrdencompra")
    private List<TblAlmacen> tblAlmacenList;

    public TblOrdencompra() {
    }

    public TblOrdencompra(TblOrdencompraPK tblOrdencompraPK) {
        this.tblOrdencompraPK = tblOrdencompraPK;
    }

    public TblOrdencompra(TblOrdencompraPK tblOrdencompraPK, int cantidad, double monto, double iva) {
        this.tblOrdencompraPK = tblOrdencompraPK;
        this.cantidad = cantidad;
        this.monto = monto;
        this.iva = iva;
    }

    public TblOrdencompra(int idtblordenCompra, int tblMaterialidTblMateria, int tblOrdenclienteidTblOrdencliente) {
        this.tblOrdencompraPK = new TblOrdencompraPK(idtblordenCompra, tblMaterialidTblMateria, tblOrdenclienteidTblOrdencliente);
    }

    public TblOrdencompraPK getTblOrdencompraPK() {
        return tblOrdencompraPK;
    }

    public void setTblOrdencompraPK(TblOrdencompraPK tblOrdencompraPK) {
        this.tblOrdencompraPK = tblOrdencompraPK;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public TblProveedores getTblProveedoresIdtblProveedores() {
        return tblProveedoresIdtblProveedores;
    }

    public void setTblProveedoresIdtblProveedores(TblProveedores tblProveedoresIdtblProveedores) {
        this.tblProveedoresIdtblProveedores = tblProveedoresIdtblProveedores;
    }

    public Tblusuarios getTblUsuariosidTblUsuarios() {
        return tblUsuariosidTblUsuarios;
    }

    public void setTblUsuariosidTblUsuarios(Tblusuarios tblUsuariosidTblUsuarios) {
        this.tblUsuariosidTblUsuarios = tblUsuariosidTblUsuarios;
    }

    public Tblmaterial getTblmaterial() {
        return tblmaterial;
    }

    public void setTblmaterial(Tblmaterial tblmaterial) {
        this.tblmaterial = tblmaterial;
    }

    public Tblordencliente getTblordencliente() {
        return tblordencliente;
    }

    public void setTblordencliente(Tblordencliente tblordencliente) {
        this.tblordencliente = tblordencliente;
    }

    @XmlTransient
    public List<TblAlmacen> getTblAlmacenList() {
        return tblAlmacenList;
    }

    public void setTblAlmacenList(List<TblAlmacen> tblAlmacenList) {
        this.tblAlmacenList = tblAlmacenList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tblOrdencompraPK != null ? tblOrdencompraPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblOrdencompra)) {
            return false;
        }
        TblOrdencompra other = (TblOrdencompra) object;
        if ((this.tblOrdencompraPK == null && other.tblOrdencompraPK != null) || (this.tblOrdencompraPK != null && !this.tblOrdencompraPK.equals(other.tblOrdencompraPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.mrp.models.TblOrdencompra[ tblOrdencompraPK=" + tblOrdencompraPK + " ]";
    }
    
}
