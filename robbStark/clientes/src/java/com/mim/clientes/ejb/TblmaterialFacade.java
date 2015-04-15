/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.clientes.ejb;

import com.mim.clientes.models.Tblmaterial;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author NORE
 */
@Stateless
public class TblmaterialFacade extends AbstractFacade<Tblmaterial> {
    @PersistenceContext(unitName = "clientesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TblmaterialFacade() {
        super(Tblmaterial.class);
    }
    
}
