/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.ejb;

import com.mim.mrp.models.TblOrdencompra;
import com.mim.mrp.models.TblOrdencompraPK;
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
public class TblOrdencompraFacade extends AbstractFacade<TblOrdencompra> {

    @PersistenceContext(unitName = "mrpMIMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblOrdencompraFacade() {
        super(TblOrdencompra.class);
    }

    public List<TblOrdencompra> findAll(Integer idTblOrdencliente) {
        TypedQuery<TblOrdencompra> query = em.createQuery("SELECT c FROM TblOrdencompra c WHERE c.tblOrdencompraPK.tblOrdenclienteidTblOrdencliente = :id", TblOrdencompra.class);
        query.setParameter("id", idTblOrdencliente);
        return query.getResultList();
    }

    public void updateClass(TblOrdencompraPK tblOrdencompraPK, String clase) {
        TblOrdencompra cp = find(tblOrdencompraPK);
        cp.setClase(clase);
    }

    public List<TblOrdencompra> findAvailable() {  
        //TypedQuery<TblOrdencompra> query = em.createQuery("SELECT c FROM TblOrdencompra c WHERE c.clase IS NOT NULL AND c.estatus != 2 AND c.estatus != 3" , TblOrdencompra.class);
        TypedQuery<TblOrdencompra> query = em.createQuery("SELECT c FROM TblOrdencompra c WHERE c.clase IS NOT NULL AND c.estatus != 2 AND c.estatus != 3 AND c.estatus != 4", TblOrdencompra.class);
        return query.getResultList();
    }

    public void updateStatus(TblOrdencompraPK tblOrdencompraPK, int i) {
        TblOrdencompra orden = find(tblOrdencompraPK);
        orden.setEstatus(i);
    }

    public List<TblOrdencompra> findAvailable2() {
        TypedQuery<TblOrdencompra> query = em.createQuery("SELECT c FROM TblOrdencompra c WHERE c.clase IS NOT NULL AND c.estatus = 2", TblOrdencompra.class);
        return query.getResultList();
    }

}
