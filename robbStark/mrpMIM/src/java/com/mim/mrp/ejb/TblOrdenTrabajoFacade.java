/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.ejb;

import com.mim.mrp.models.TblOrdenTrabajo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author NORE
 */
@Stateless
public class TblOrdenTrabajoFacade extends AbstractFacade<TblOrdenTrabajo> {

    @PersistenceContext(unitName = "mrpMIMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblOrdenTrabajoFacade() {
        super(TblOrdenTrabajo.class);
    }

    public int createOrder(TblOrdenTrabajo trabajo) {
        em.persist(trabajo);
        em.flush();
        return trabajo.getIdtblOrdenTrabajo();
    }

}
