/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.clientes.models;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author NORE
 */
@Entity
@Table(name = "tblordencliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblordencliente.findAll", query = "SELECT t FROM Tblordencliente t"),
    @NamedQuery(name = "Tblordencliente.findByIdTblOrdencliente", query = "SELECT t FROM Tblordencliente t WHERE t.idTblOrdencliente = :idTblOrdencliente"),
    @NamedQuery(name = "Tblordencliente.findByCantidad", query = "SELECT t FROM Tblordencliente t WHERE t.cantidad = :cantidad"),
    @NamedQuery(name = "Tblordencliente.findByFechadeentrega", query = "SELECT t FROM Tblordencliente t WHERE t.fechadeentrega = :fechadeentrega"),
    @NamedQuery(name = "Tblordencliente.findByFechacaptura", query = "SELECT t FROM Tblordencliente t WHERE t.fechacaptura = :fechacaptura"),
    @NamedQuery(name = "Tblordencliente.findByEstatus", query = "SELECT t FROM Tblordencliente t WHERE t.estatus = :estatus")})
public class Tblordencliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTblOrdencliente")
    private Integer idTblOrdencliente;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha de entrega")
    @Temporal(TemporalType.DATE)
    private Date fechadeentrega;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha captura")
    @Temporal(TemporalType.DATE)
    private Date fechacaptura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estatus")
    private int estatus;
    @JoinColumn(name = "idTblClientes", referencedColumnName = "idTblClientes")
    @ManyToOne(optional = false)
    private Tblclientes idTblClientes;
    @JoinColumn(name = "TblProducto_idTblProducto", referencedColumnName = "idTblProducto")
    @ManyToOne(optional = false)
    private Tblproducto tblProductoidTblProducto;

    public Tblordencliente() {
    }

    public Tblordencliente(Integer idTblOrdencliente) {
        this.idTblOrdencliente = idTblOrdencliente;
    }

    public Tblordencliente(Integer idTblOrdencliente, int cantidad, Date fechadeentrega, Date fechacaptura, int estatus) {
        this.idTblOrdencliente = idTblOrdencliente;
        this.cantidad = cantidad;
        this.fechadeentrega = fechadeentrega;
        this.fechacaptura = fechacaptura;
        this.estatus = estatus;
    }

    public Integer getIdTblOrdencliente() {
        return idTblOrdencliente;
    }

    public void setIdTblOrdencliente(Integer idTblOrdencliente) {
        this.idTblOrdencliente = idTblOrdencliente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechadeentrega() {
        return fechadeentrega;
    }

    public void setFechadeentrega(Date fechadeentrega) {
        this.fechadeentrega = fechadeentrega;
    }

    public Date getFechacaptura() {
        return fechacaptura;
    }

    public void setFechacaptura(Date fechacaptura) {
        this.fechacaptura = fechacaptura;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public Tblclientes getIdTblClientes() {
        return idTblClientes;
    }

    public void setIdTblClientes(Tblclientes idTblClientes) {
        this.idTblClientes = idTblClientes;
    }

    public Tblproducto getTblProductoidTblProducto() {
        return tblProductoidTblProducto;
    }

    public void setTblProductoidTblProducto(Tblproducto tblProductoidTblProducto) {
        this.tblProductoidTblProducto = tblProductoidTblProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTblOrdencliente != null ? idTblOrdencliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblordencliente)) {
            return false;
        }
        Tblordencliente other = (Tblordencliente) object;
        if ((this.idTblOrdencliente == null && other.idTblOrdencliente != null) || (this.idTblOrdencliente != null && !this.idTblOrdencliente.equals(other.idTblOrdencliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.clientes.models.Tblordencliente[ idTblOrdencliente=" + idTblOrdencliente + " ]";
    }
    
}
