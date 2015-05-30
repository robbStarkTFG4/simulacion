/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.ejb;

import com.mim.mrp.models.TblAlmacen;
import com.mim.mrp.models.TblOrdencompra;
import com.mim.mrp.models.TblPlanProducction;
import com.mim.mrp.models.TblProduccionActividad;
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
        em.merge(res);
        em.flush();
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

    public List<Tblordencliente> findAll3(int i) {
        TypedQuery<Tblordencliente> query = em.createQuery("SELECT c FROM Tblordencliente c WHERE c.estatus = :id AND c.", Tblordencliente.class);
        query.setParameter("id", i);
        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean shoudDoIt(Integer idTblOrdencliente) {
        TypedQuery<TblOrdencompra> query = em.createQuery("SELECT c FROM TblOrdencompra c WHERE c.tblordencliente.idTblOrdencliente = :id ", TblOrdencompra.class);
        query.setParameter("id", idTblOrdencliente);

        List<TblOrdencompra> temp = query.getResultList();
        for (TblOrdencompra tp : temp) {
            if (tp.getEstatus() != 4) {
                return false;
            }
        }
        return true;
    }

    public boolean finishedProduction(Integer idTblOrdencliente) {
        TypedQuery<TblPlanProducction> query = em.createQuery("SELECT c FROM TblPlanProducction c WHERE c.tblOrdenclienteidTblOrdencliente.idTblOrdencliente = :id ", TblPlanProducction.class);
        query.setParameter("id", idTblOrdencliente);
        TblPlanProducction temp = query.getSingleResult();
        temp.getTblProduccionActividadList().size();

        TypedQuery<TblProduccionActividad> query2 = em.createQuery("SELECT c FROM TblProduccionActividad c WHERE c.tblPlanProducctionIdtblPlanProducction.idtblPlanProducction = :idd", TblProduccionActividad.class);
        query2.setParameter("idd", temp.getIdtblPlanProducction());
        int objetivo = temp.getTblOrdenclienteidTblOrdencliente().getCantidad();

        int comprobate = 0;

        for (TblProduccionActividad col : query2.getResultList()) {
            if (col.getCantidadReal() != null) {
                comprobate = comprobate + col.getCantidad();
            }
        }
        System.out.println("Sumas -> objetivo: " + objetivo + " real: " + comprobate);
        return objetivo == comprobate;
    }

    public Tblordencliente findClientOrder(int idtblordenCompra) {
        TypedQuery<TblOrdencompra> query = em.createQuery("SELECT c FROM TblOrdencompra c WHERE c.tblOrdencompraPK.idtblordenCompra = :id", TblOrdencompra.class);
        query.setParameter("id", idtblordenCompra);
        TblOrdencompra temp = query.getSingleResult();
        return temp.getTblordencliente();
    }

    public Tblordencliente findClientOrder2(int idOrdenCliente) {
        TypedQuery<Tblordencliente> query = em.createQuery("SELECT c FROM Tblordencliente c WHERE c.idTblOrdencliente = :id", Tblordencliente.class);
        query.setParameter("id", idOrdenCliente);
        Tblordencliente temp = query.getSingleResult();
        return temp;
    }

}
