/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jcode.myapp.utilities;

import java.util.List;

/**
 *
 * @author JCode
 */
public class BEAN_PAGINATION {

    private Integer COUNT_FILTER;
    private List<?> LIST;

    public Integer getCOUNT_FILTER() {
        return COUNT_FILTER;
    }

    public void setCOUNT_FILTER(Integer COUNT_FILTER) {
        this.COUNT_FILTER = COUNT_FILTER;
    }

    public List<?> getLIST() {
        return LIST;
    }

    public void setLIST(List<?> LIST) {
        this.LIST = LIST;
    }
    
    
}
