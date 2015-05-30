/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.mrp.ejb;

import com.mim.mrp.models.TblAlmacen;
import com.mim.mrp.models.TblOrdencompra;
import com.mim.mrp.models.TblOrdencompraPK;
import com.mim.mrp.models.Tblordencliente;
import com.mim.mrp.util.almacen.AlmacenDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author NORE
 */
@Stateless
public class TblAlmacenFacade extends AbstractFacade<TblAlmacen> {

    @EJB
    TblAlmacenActividadFacade activityFacade;

    @EJB
    TblLocasionFacade locationFacade;

    @EJB
    TblordenclienteFacade clienteFacade;

    @EJB
    TblOrdencompraFacade compraFacade;

    @PersistenceContext(unitName = "mrpMIMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblAlmacenFacade() {
        super(TblAlmacen.class);
    }

    public List<AlmacenDTO> findOrdersInspection() {
        TypedQuery<TblAlmacen> query = em.createQuery("SELECT c FROM TblAlmacen c WHERE c.tblLocasionIdtblLocasion.idtblLocasion = 5", TblAlmacen.class);
        List<TblAlmacen> almList = query.getResultList();

        List<AlmacenDTO> results = new ArrayList<>();

        for (TblAlmacen alm : almList) {
            List<TblOrdencompra> compraList = alm.getTblOrdenclienteidTblOrdencliente().getTblOrdencompraList();
            for (TblOrdencompra cp : compraList) {
                if (Objects.equals(cp.getTblmaterial().getIdTblMateria(), alm.getTblMaterialidTblMateria().getIdTblMateria())) {
                    results.add(new AlmacenDTO(cp, alm));
                }
            }
        }
        return results;
    }

    public void updateLocation(Integer idtblAlmacen, String descripcion) {
        TypedQuery<TblAlmacen> query = em.createQuery("SELECT c FROM TblAlmacen c WHERE c.idtblAlmacen = :id", TblAlmacen.class);
        query.setParameter("id", idtblAlmacen);
        TblAlmacen temp = query.getSingleResult();
        temp.setTblAlmacenActividadIdtblAlmacenActividad(activityFacade.find(4));
        temp.setTblLocasionIdtblLocasion(locationFacade.findLocation(descripcion));
    }

    public void create2(TblAlmacen alm, TblOrdencompraPK tblOrdencompraPK) {
        TblOrdencompra temp = compraFacade.find(tblOrdencompraPK);
        //Tblordencliente sup = clienteFacade.findClientOrder(temp.getTblOrdencompraPK().getIdtblordenCompra());
        //sup.getMonto();
        try {
            System.out.println("WTFF??????????? : " + temp.getTblordencliente().getIdTblOrdencliente());
            alm.setTblOrdenclienteidTblOrdencliente(new Tblordencliente(temp.getTblordencliente().getIdTblOrdencliente()));
            em.persist(alm);
            em.flush();
        } catch (Exception e) {

            try {
                temp = compraFacade.find(tblOrdencompraPK);
                System.out.println("WTFF??????????? : " + temp.getTblordencliente().getIdTblOrdencliente());
                alm.setTblOrdenclienteidTblOrdencliente(new Tblordencliente(temp.getTblordencliente().getIdTblOrdencliente()));
                em.persist(alm);
                em.flush();
            } catch (Exception ef) {
                temp = compraFacade.find(tblOrdencompraPK);
           
                alm.setTblOrdenclienteidTblOrdencliente(new Tblordencliente(temp.getTblordencliente().getIdTblOrdencliente()));
                em.persist(alm);
                em.flush();
            }
        }
    }

}
