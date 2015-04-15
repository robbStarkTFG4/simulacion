/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.clientes.ejb;

import com.mim.clientes.models.Tblordencliente;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author NORE
 */
@Stateless
public class TblordenclienteFacade extends AbstractFacade<Tblordencliente> {

    @PersistenceContext(unitName = "clientesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblordenclienteFacade() {
        super(Tblordencliente.class);
    }

    public List<Tblordencliente> findAll(String usuario) {
        TypedQuery<Tblordencliente> query = em.createQuery("SELECT c FROM Tblordencliente c WHERE c.idTblClientes.usuario =:usr", Tblordencliente.class);
        query.setParameter("usr", usuario);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }

    }

}
