package com.lj.gps.biz.entity;

import java.io.Serializable;
import java.util.Date;

public class HisGps implements Serializable {
    private Integer id;

    private String sn;

    private Integer we;

    private Integer longitude;

    private Integer ns;

    private Integer latitude;

    private Date gpstime;

    private Integer altitude;

    private Integer speed;

    private Integer direct;

    private Date createtime;

    private Date updatetime;

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

    public Integer getWe() {
        return we;
    }

    public void setWe(Integer we) {
        this.we = we;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public Integer getNs() {
        return ns;
    }

    public void setNs(Integer ns) {
        this.ns = ns;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Date getGpstime() {
        return gpstime;
    }

    public void setGpstime(Date gpstime) {
        this.gpstime = gpstime;
    }

    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getDirect() {
        return direct;
    }

    public void setDirect(Integer direct) {
        this.direct = direct;
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