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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author NORE
 */
@Entity
@Table(name = "tbl_proveedores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblProveedores.findAll", query = "SELECT t FROM TblProveedores t"),
    @NamedQuery(name = "TblProveedores.findByIdtblProveedores", query = "SELECT t FROM TblProveedores t WHERE t.idtblProveedores = :idtblProveedores"),
    @NamedQuery(name = "TblProveedores.findByEmpresa", query = "SELECT t FROM TblProveedores t WHERE t.empresa = :empresa"),
    @NamedQuery(name = "TblProveedores.findByRepresentante", query = "SELECT t FROM TblProveedores t WHERE t.representante = :representante"),
    @NamedQuery(name = "TblProveedores.findByRazonSocial", query = "SELECT t FROM TblProveedores t WHERE t.razonSocial = :razonSocial"),
    @NamedQuery(name = "TblProveedores.findByRfc", query = "SELECT t FROM TblProveedores t WHERE t.rfc = :rfc")})
public class TblProveedores implements Serializable {
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "indice")
    private Double indice;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtbl_proveedores")
    private Integer idtblProveedores;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "empresa")
    private String empresa;
    @Size(max = 45)
    @Column(name = "representante")
    private String representante;
    @Size(max = 45)
    @Column(name = "razon_social")
    private String razonSocial;
    @Size(max = 45)
    @Column(name = "rfc")
    private String rfc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblProveedoresIdtblProveedores")
    private List<TblPrecioMaterial> tblPrecioMaterialList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblProveedoresIdtblProveedores")
    private List<TblOrdencompra> tblOrdencompraList;

    public TblProveedores() {
    }

    public TblProveedores(Integer idtblProveedores) {
        this.idtblProveedores = idtblProveedores;
    }

    public TblProveedores(Integer idtblProveedores, String empresa) {
        this.idtblProveedores = idtblProveedores;
        this.empresa = empresa;
    }

    public Integer getIdtblProveedores() {
        return idtblProveedores;
    }

    public void setIdtblProveedores(Integer idtblProveedores) {
        this.idtblProveedores = idtblProveedores;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getRepresentante() {
        return representante;
    }

    public void setRepresentante(String representante) {
        this.representante = representante;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    @XmlTransient
    public List<TblPrecioMaterial> getTblPrecioMaterialList() {
        return tblPrecioMaterialList;
    }

    public void setTblPrecioMaterialList(List<TblPrecioMaterial> tblPrecioMaterialList) {
        this.tblPrecioMaterialList = tblPrecioMaterialList;
    }

    @XmlTransient
    public List<TblOrdencompra> getTblOrdencompraList() {
        return tblOrdencompraList;
    }

    public void setTblOrdencompraList(List<TblOrdencompra> tblOrdencompraList) {
        this.tblOrdencompraList = tblOrdencompraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtblProveedores != null ? idtblProveedores.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblProveedores)) {
            return false;
        }
        TblProveedores other = (TblProveedores) object;
        if ((this.idtblProveedores == null && other.idtblProveedores != null) || (this.idtblProveedores != null && !this.idtblProveedores.equals(other.idtblProveedores))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.mrp.models.TblProveedores[ idtblProveedores=" + idtblProveedores + " ]";
    }

    public Double getIndice() {
        return indice;
    }

    public void setIndice(Double indice) {
        this.indice = indice;
    }
    
}
