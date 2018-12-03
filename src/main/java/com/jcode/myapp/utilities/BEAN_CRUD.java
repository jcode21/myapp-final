/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcode.myapp.utilities;

/**
 *
 * @author JCode
 */
public class BEAN_CRUD {

    private String MESSAGE_SERVER;
    private BEAN_PAGINATION BEAN_PAGINATION;

    public BEAN_CRUD() {
    }

    public BEAN_CRUD(BEAN_PAGINATION BEAN_PAGINATION) {
        this.BEAN_PAGINATION = BEAN_PAGINATION;
    }

    public String getMESSAGE_SERVER() {
        return MESSAGE_SERVER;
    }

    public void setMESSAGE_SERVER(String MESSAGE_SERVER) {
        this.MESSAGE_SERVER = MESSAGE_SERVER;
    }

    public BEAN_PAGINATION getBEAN_PAGINATION() {
        return BEAN_PAGINATION;
    }

    public void setBEAN_PAGINATION(BEAN_PAGINATION BEAN_PAGINATION) {
        this.BEAN_PAGINATION = BEAN_PAGINATION;
    }
    
}
