package com.lj.gps.frame.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("ggsadmin")
public class GgsadminProperty {

    private String appid;

    private String accesskey;

    /**
     * 接口调用URL
     */
    private String otsmgnUrl;

    /**
     * 批量导入启用停用数据最大值
     */
    private String opAllowNum;

    /**
     * 上传路径
     */
    private String uploadPath;

    /**
     * 下载路径
     */
    private String downloadPath;

    /**
     * 文件服务器域名
     */
    private String domain;

    /**
     * 出库导入最大值
     */
    private String stockAllowNum;

    private String imageUrl;

    /**
     * 微信推送消息
     */
    private String wechatUrl;

    private String wechatAppkey;

    private String wechatSign;

    /**
     * 实名认证审核结果通知服务侧URL
     */
    private String authNoticeUrl;

    /**
     * AppKey
     */
    private String authAppKey;

    /**
     * sign
     */
    private String authSign;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAccesskey() {
        return accesskey;
    }

    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }

    public String getOtsmgnUrl() {
        return otsmgnUrl;
    }

    public void setOtsmgnUrl(String otsmgnUrl) {
        this.otsmgnUrl = otsmgnUrl;
    }

    public String getOpAllowNum() {
        return opAllowNum;
    }

    public void setOpAllowNum(String opAllowNum) {
        this.opAllowNum = opAllowNum;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getDownloadPath() {
        return downloadPath;
    }

    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStockAllowNum() {
        return stockAllowNum;
    }

    public void setStockAllowNum(String stockAllowNum) {
        this.stockAllowNum = stockAllowNum;
    }

    public String getWechatUrl() {
        return wechatUrl;
    }

    public void setWechatUrl(String wechatUrl) {
        this.wechatUrl = wechatUrl;
    }

    public String getWechatAppkey() {
        return wechatAppkey;
    }

    public void setWechatAppkey(String wechatAppkey) {
        this.wechatAppkey = wechatAppkey;
    }

    public String getWechatSign() {
        return wechatSign;
    }

    public void setWechatSign(String wechatSign) {
        this.wechatSign = wechatSign;
    }

    public String getAuthNoticeUrl() {
        return authNoticeUrl;
    }

    public void setAuthNoticeUrl(String authNoticeUrl) {
        this.authNoticeUrl = authNoticeUrl;
    }

    public String getAuthAppKey() {
        return authAppKey;
    }

    public void setAuthAppKey(String authAppKey) {
        this.authAppKey = authAppKey;
    }

    public String getAuthSign() {
        return authSign;
    }

    public void setAuthSign(String authSign) {
        this.authSign = authSign;
    }
}
