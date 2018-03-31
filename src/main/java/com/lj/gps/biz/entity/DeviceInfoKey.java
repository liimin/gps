package com.lj.gps.biz.entity;

import java.io.Serializable;

public class DeviceInfoKey implements Serializable {
    private Integer id;

    private String sn;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }
}