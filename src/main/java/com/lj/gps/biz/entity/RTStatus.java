package com.lj.gps.biz.entity;

import java.io.Serializable;
import java.util.Date;

public class RTStatus implements Serializable {
    private String sn;

    private Integer status;

    private Date createtime;

    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}