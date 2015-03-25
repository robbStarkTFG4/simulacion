/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mim.clientes.controllers;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author NORE
 */
@Named("layout")
@SessionScoped
public class LayoutCtrl implements Serializable {

    private String unblockPassword;
    private String usuario;
    private String password;

    public void gotoLockScreen() {
        System.out.println("lockScreen");
    }

    public String unblockApp() {
        System.out.println("unblock app2");
        if (unblockPassword == null || unblockPassword.trim().equals("")) {
            System.out.println(unblockPassword);
            return null;
        }
        return "testTemplate.xhtml?faces-redirect=true";
    }

    public String logOut() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.xhtml?faces-redirect=true";
    }

    public String doLogin() {

        if (usuario == null || usuario.trim().equals("")) {
            System.out.println(usuario);
            System.out.println("usuario hello");
            return null;
        }
        if (password == null || password.trim().equals("")) {
            System.out.println(password);
            System.out.println("password hello");
            return null;
        }
        return "testTemplate.xhtml?faces-redirect=true";
    }

    //Getters/Setters
    public String getUnblockPassword() {
        return unblockPassword;
    }

    public void setUnblockPassword(String unblockPassword) {
        this.unblockPassword = unblockPassword;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
