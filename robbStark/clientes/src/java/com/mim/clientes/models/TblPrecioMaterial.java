/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.clientes.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NORE
 */
@Entity
@Table(name = "tbl_precio_material")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblPrecioMaterial.findAll", query = "SELECT t FROM TblPrecioMaterial t"),
    @NamedQuery(name = "TblPrecioMaterial.findByIdtblPrecioMaterial", query = "SELECT t FROM TblPrecioMaterial t WHERE t.idtblPrecioMaterial = :idtblPrecioMaterial"),
    @NamedQuery(name = "TblPrecioMaterial.findByPrecio", query = "SELECT t FROM TblPrecioMaterial t WHERE t.precio = :precio"),
    @NamedQuery(name = "TblPrecioMaterial.findByTiempoEntrega", query = "SELECT t FROM TblPrecioMaterial t WHERE t.tiempoEntrega = :tiempoEntrega")})
public class TblPrecioMaterial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtbl_precio_material")
    private Integer idtblPrecioMaterial;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private Double precio;
    @Column(name = "tiempo_entrega")
    private Integer tiempoEntrega;
    @JoinColumn(name = "TblMaterial_idTblMateria", referencedColumnName = "idTblMateria")
    @ManyToOne(optional = false)
    private Tblmaterial tblMaterialidTblMateria;
    @JoinColumn(name = "tbl_proveedores_idtbl_proveedores", referencedColumnName = "idtbl_proveedores")
    @ManyToOne(optional = false)
    private TblProveedores tblProveedoresIdtblProveedores;

    public TblPrecioMaterial() {
    }

    public TblPrecioMaterial(Integer idtblPrecioMaterial) {
        this.idtblPrecioMaterial = idtblPrecioMaterial;
    }

    public Integer getIdtblPrecioMaterial() {
        return idtblPrecioMaterial;
    }

    public void setIdtblPrecioMaterial(Integer idtblPrecioMaterial) {
        this.idtblPrecioMaterial = idtblPrecioMaterial;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getTiempoEntrega() {
        return tiempoEntrega;
    }

    public void setTiempoEntrega(Integer tiempoEntrega) {
        this.tiempoEntrega = tiempoEntrega;
    }

    public Tblmaterial getTblMaterialidTblMateria() {
        return tblMaterialidTblMateria;
    }

    public void setTblMaterialidTblMateria(Tblmaterial tblMaterialidTblMateria) {
        this.tblMaterialidTblMateria = tblMaterialidTblMateria;
    }

    public TblProveedores getTblProveedoresIdtblProveedores() {
        return tblProveedoresIdtblProveedores;
    }

    public void setTblProveedoresIdtblProveedores(TblProveedores tblProveedoresIdtblProveedores) {
        this.tblProveedoresIdtblProveedores = tblProveedoresIdtblProveedores;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtblPrecioMaterial != null ? idtblPrecioMaterial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblPrecioMaterial)) {
            return false;
        }
        TblPrecioMaterial other = (TblPrecioMaterial) object;
        if ((this.idtblPrecioMaterial == null && other.idtblPrecioMaterial != null) || (this.idtblPrecioMaterial != null && !this.idtblPrecioMaterial.equals(other.idtblPrecioMaterial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.clientes.models.TblPrecioMaterial[ idtblPrecioMaterial=" + idtblPrecioMaterial + " ]";
    }
    
}
