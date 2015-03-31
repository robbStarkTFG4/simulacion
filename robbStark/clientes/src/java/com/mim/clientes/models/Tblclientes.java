/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.clientes.models;

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
@Table(name = "tblclientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tblclientes.findAll", query = "SELECT t FROM Tblclientes t"),
    @NamedQuery(name = "Tblclientes.findByIdTblClientes", query = "SELECT t FROM Tblclientes t WHERE t.idTblClientes = :idTblClientes"),
    @NamedQuery(name = "Tblclientes.findByNombre", query = "SELECT t FROM Tblclientes t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tblclientes.findByNumerocliente", query = "SELECT t FROM Tblclientes t WHERE t.numerocliente = :numerocliente"),
    @NamedQuery(name = "Tblclientes.findByCorreo", query = "SELECT t FROM Tblclientes t WHERE t.correo = :correo"),
    @NamedQuery(name = "Tblclientes.findByTelefono", query = "SELECT t FROM Tblclientes t WHERE t.telefono = :telefono"),
    @NamedQuery(name = "Tblclientes.findByApelidoPaterno", query = "SELECT t FROM Tblclientes t WHERE t.apelidoPaterno = :apelidoPaterno"),
    @NamedQuery(name = "Tblclientes.findByApellidoMaterno", query = "SELECT t FROM Tblclientes t WHERE t.apellidoMaterno = :apellidoMaterno")})
public class Tblclientes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTblClientes")
    private Integer idTblClientes;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Numero cliente")
    private String numerocliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Correo")
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "apelidoPaterno")
    private String apelidoPaterno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "apellidoMaterno")
    private String apellidoMaterno;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTblClientes")
    private List<Tblordencliente> tblordenclienteList;

    public Tblclientes() {
    }

    public Tblclientes(Integer idTblClientes) {
        this.idTblClientes = idTblClientes;
    }

    public Tblclientes(Integer idTblClientes, String nombre, String numerocliente, String correo, String telefono, String apelidoPaterno, String apellidoMaterno) {
        this.idTblClientes = idTblClientes;
        this.nombre = nombre;
        this.numerocliente = numerocliente;
        this.correo = correo;
        this.telefono = telefono;
        this.apelidoPaterno = apelidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    public Integer getIdTblClientes() {
        return idTblClientes;
    }

    public void setIdTblClientes(Integer idTblClientes) {
        this.idTblClientes = idTblClientes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumerocliente() {
        return numerocliente;
    }

    public void setNumerocliente(String numerocliente) {
        this.numerocliente = numerocliente;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getApelidoPaterno() {
        return apelidoPaterno;
    }

    public void setApelidoPaterno(String apelidoPaterno) {
        this.apelidoPaterno = apelidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    @XmlTransient
    public List<Tblordencliente> getTblordenclienteList() {
        return tblordenclienteList;
    }

    public void setTblordenclienteList(List<Tblordencliente> tblordenclienteList) {
        this.tblordenclienteList = tblordenclienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTblClientes != null ? idTblClientes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblclientes)) {
            return false;
        }
        Tblclientes other = (Tblclientes) object;
        if ((this.idTblClientes == null && other.idTblClientes != null) || (this.idTblClientes != null && !this.idTblClientes.equals(other.idTblClientes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.clientes.models.Tblclientes[ idTblClientes=" + idTblClientes + " ]";
    }
    
}
