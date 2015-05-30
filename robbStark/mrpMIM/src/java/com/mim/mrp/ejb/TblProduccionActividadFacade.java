/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.ejb;

import com.mim.mrp.models.TblProduccionActividad;
import com.mim.mrp.models.Tblclientes;
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
public class TblProduccionActividadFacade extends AbstractFacade<TblProduccionActividad> {

    @PersistenceContext(unitName = "mrpMIMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblProduccionActividadFacade() {
        super(TblProduccionActividad.class);
    }

    public List<TblProduccionActividad> findList(Integer idTblOrdencliente) {
        TypedQuery<TblProduccionActividad> query = em.createQuery("SELECT c FROM TblProduccionActividad c WHERE c.tblPlanProducctionIdtblPlanProducction.tblOrdenclienteidTblOrdencliente.idTblOrdencliente = :id AND c.estatus IS NULL", TblProduccionActividad.class);
        query.setParameter("id", idTblOrdencliente);
        return query.getResultList();
    }

    public void changeStatus(Integer idtblProduccionActividad, int i) {
        TypedQuery<TblProduccionActividad> query = em.createQuery("SELECT c FROM TblProduccionActividad c WHERE c.idtblProduccionActividad = :id", TblProduccionActividad.class);
        query.setParameter("id", idtblProduccionActividad);
        TblProduccionActividad temp = query.getSingleResult();
        temp.setEstatus(i);
    }

    public List<TblProduccionActividad> findByStatus(Integer idTblOrdencliente, int i) {
        TypedQuery<TblProduccionActividad> query = em.createQuery("SELECT c FROM TblProduccionActividad c WHERE c.tblPlanProducctionIdtblPlanProducction.tblOrdenclienteidTblOrdencliente.idTblOrdencliente = :id AND c.estatus = :status", TblProduccionActividad.class);
        query.setParameter("id", idTblOrdencliente);
        query.setParameter("status", i);
        return query.getResultList();
    }

    public void pushActivityReport(Integer idtblProduccionActividad, int cantidad, String comentario) {
        TypedQuery<TblProduccionActividad> query = em.createQuery("SELECT c FROM TblProduccionActividad c WHERE c.idtblProduccionActividad = :id", TblProduccionActividad.class);
        query.setParameter("id", idtblProduccionActividad);
        TblProduccionActividad temp = query.getSingleResult();
        temp.setEstatus(2);
        temp.setCantidadReal(cantidad);
        temp.setComentario(comentario);
        em.merge(temp);
        em.flush();
    }

}
