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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author NORE
 */
@Entity
@Table(name = "tblmaterial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblmaterial.findAll", query = "SELECT t FROM Tblmaterial t"),
    @NamedQuery(name = "Tblmaterial.findByIdTblMateria", query = "SELECT t FROM Tblmaterial t WHERE t.idTblMateria = :idTblMateria"),
    @NamedQuery(name = "Tblmaterial.findByNombre", query = "SELECT t FROM Tblmaterial t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tblmaterial.findByDescripcion", query = "SELECT t FROM Tblmaterial t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "Tblmaterial.findByImagen", query = "SELECT t FROM Tblmaterial t WHERE t.imagen = :imagen")})
public class Tblmaterial implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTblMateria")
    private Integer idTblMateria;
    @Size(max = 45)
    @Column(name = "Nombre")
    private String nombre;
    @Size(max = 150)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 80)
    @Column(name = "imagen")
    private String imagen;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblMaterialidTblMateria")
    private List<Tbldetallereceta> tbldetallerecetaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblMaterialidTblMateria")
    private List<TblPrecioMaterial> tblPrecioMaterialList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblmaterial")
    private List<TblOrdencompra> tblOrdencompraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tblMaterialidTblMateria")
    private List<TblAlmacen> tblAlmacenList;

    public Tblmaterial() {
    }

    public Tblmaterial(Integer idTblMateria) {
        this.idTblMateria = idTblMateria;
    }

    public Integer getIdTblMateria() {
        return idTblMateria;
    }

    public void setIdTblMateria(Integer idTblMateria) {
        this.idTblMateria = idTblMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @XmlTransient
    public List<Tbldetallereceta> getTbldetallerecetaList() {
        return tbldetallerecetaList;
    }

    public void setTbldetallerecetaList(List<Tbldetallereceta> tbldetallerecetaList) {
        this.tbldetallerecetaList = tbldetallerecetaList;
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
        hash += (idTblMateria != null ? idTblMateria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblmaterial)) {
            return false;
        }
        Tblmaterial other = (Tblmaterial) object;
        if ((this.idTblMateria == null && other.idTblMateria != null) || (this.idTblMateria != null && !this.idTblMateria.equals(other.idTblMateria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.mrp.models.Tblmaterial[ idTblMateria=" + idTblMateria + " ]";
    }
    
}
