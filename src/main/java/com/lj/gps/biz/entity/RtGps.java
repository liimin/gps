package com.lj.gps.biz.entity;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

public class RtGps implements Serializable {
    private String sn;

    private Integer we;

    private Integer longitude;

    private Integer ns;

    private Integer latitude;

    private Date gpstime;

    private Integer altitude;

    private Integer speed;

    private Integer direct;

    private Integer number;

    private Date createtime;

    private Date updatetime;

    @Transient
    private Double alat;

    @Transient
    private Double alng;

    private static final long serialVersionUID = 1L;

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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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

    public Double getAlng() {
        return alng;
    }

    public void setAlng(Double alng) {
        this.alng = alng;
    }

    public Double getAlat() {
        return alat;
    }

    public void setAlat(Double alat) {
        this.alat = alat;
    }
}