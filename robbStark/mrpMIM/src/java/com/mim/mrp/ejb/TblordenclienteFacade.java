/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.ejb;

import com.mim.mrp.models.TblOrdencompra;
import com.mim.mrp.models.Tblordencliente;
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

    @PersistenceContext(unitName = "mrpMIMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblordenclienteFacade() {
        super(Tblordencliente.class);
    }

    public List<Tblordencliente> findAll(int i) {
        TypedQuery<Tblordencliente> query = em.createQuery("SELECT c FROM Tblordencliente c WHERE c.estatus = :id AND SIZE(c.tblPlanProducctionList) = 0", Tblordencliente.class);
        query.setParameter("id", i);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }

    }

    public List<Tblordencliente> findAll2(int i) {
        TypedQuery<Tblordencliente> query = em.createQuery("SELECT c FROM Tblordencliente c WHERE c.estatus = :id AND SIZE(c.tblPlanProducctionList) > 0", Tblordencliente.class);
        query.setParameter("id", i);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }

    }

    public void changeStatus(Integer idTblOrdencliente, int i) {
        Tblordencliente res = this.find(idTblOrdencliente);
        res.setEstatus(i);
    }

    public boolean checkOrders(Integer idTblOrdencliente) {
        Tblordencliente res = this.find(idTblOrdencliente);
        for (TblOrdencompra col : res.getTblOrdencompraList()) {
            if (col.getEstatus() != 3) {
                return false;
            }
        }
        return true;
    }

}
