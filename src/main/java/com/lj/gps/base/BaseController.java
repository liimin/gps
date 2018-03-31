package com.lj.gps.base;

import com.lj.gps.biz.entity.User;
import com.lj.gps.biz.service.UserService;
import com.lj.gps.frame.common.Constants;
import com.lj.gps.frame.exception.BusinessException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: liuyf<br/>
 * Date: 2017/9/18<br/>
 * Time: 15:31<br/>
 */
@Controller
public class BaseController {

    @Autowired
    protected HttpSession session;

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @Autowired
    protected UserService userService;

    /**
     * 获取当前登录用户的车辆权限role
     *
     * @return
     */
    protected Map<Integer, String> getVehicleRoleMap() {
        //return userService.getUserAndVehicleRole();
        return null;
    }

    /**
     * 获取当前登录用户id
     *
     * @return
     */
    public Integer getLoginUserId() {
        Object obj = session.getAttribute(Constants.SESSION_LOGIN_USER);
        if (obj != null) {
            return ((User) obj).getUserid();
        }
        return null;
    }

    /**
     * 获取当前登录用户
     *
     * @return
     */
    public User getCurrentLoginUser() {
        Object obj = session.getAttribute(Constants.SESSION_LOGIN_USER);
        if (obj != null) {
            return (User) obj;
        }
        return null;
    }

    private enum MimeType {
        STREAM("application/octet-stream", "jpg"),

        PDF("application/pdf", "pdf"),
        ZIP("application/zip", "zip"),
        RAR("application/zip", "rar"),
        EXCEL2003("application/vnd.ms-excel", "xls"),
        EXCEL2007("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "xlsx"),
        EXE("application/octet-stream", "exe"),

        TXT("text/plain", "txt"),
        JAVA("text/plain", "java"),
        PYTHON("text/plain", "py"),
        JAVASCRIPT("text/plain", "js"),
        CSS("text/plain", "css"),

        JPG("application/x-jpg", "jpg"),
        JPEG("image/jpeg", "jpg"),
        GIF("image/gif", "gif"),
        PNG("application/x-png", "png");
        private String contentType;
        private final static String CHARSET = "UTF-8";
        private String suffix;

        public String getContentType() {
            return contentType + ";charset=" + CHARSET;
        }

        private MimeType(String contentType, String suffix) {
            this.contentType = contentType;
            this.suffix = suffix;
        }

        public String getSuffix() {
            return suffix;
        }

        /**
         * &#x4f5c;&#x8005;:sanri <br/>
         * &#x65f6;&#x95f4;:2017-10-31&#x4e0b;&#x5348;4:36:17<br/>
         * &#x529f;&#x80fd;:&#x89e3;&#x6790;&#x51fa; mime &#x7c7b;&#x578b; <br/>
         *
         * @param fileType
         * @return
         */
        public static MimeType parseMIME(String fileType) {
            if (StringUtils.isBlank(fileType)) {
                throw new BusinessException("1", "不支持的 MIME类型");
            }
            MimeType[] values = MimeType.values();
            for (MimeType mimeType : values) {
                if (mimeType.getSuffix().equalsIgnoreCase(fileType)) {
                    return mimeType;
                }
            }
            throw new BusinessException("1", "不支持的 MIME类型");
        }
    }


    /**
     * 作者:sanri <br/>
     * 时间:2017-10-31下午4:18:54<br/>
     * 功能:下载 <br/>
     *
     * @param input
     * @param mime
     * @param fileName
     * @param response
     * @throws IOException
     */
    protected void download(InputStream input, MimeType mime, String fileName, HttpServletResponse response) throws IOException {
        if (input == null) {
            return;
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        response.setContentType(mime.getContentType());
        response.setHeader("Set-Cookie", "fileDownload=true; path=/");
        String encodeFileName = encodeFilename(fileName, request) + "." + mime.getSuffix();
//    	String fileName = encodeFilename+"."+mime.getSuffix();
        response.setHeader("Content-Disposition", "attachment;filename=\"" + encodeFileName + "\"");
        long length = input.available();
        if (length != -1) {
            response.setContentLength((int) length);
        }
        ServletOutputStream output = response.getOutputStream();
        IOUtils.copy(input, output);
        output.flush();
    }

    private static String encodeFilename(String filename, HttpServletRequest request) {
        /**
         * 获取客户端浏览器和操作系统信息
         * 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)
         * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6
         */
        String agent = request.getHeader("USER-AGENT");
        try {
            if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {
                String newFileName = URLEncoder.encode(filename, "UTF-8");
                newFileName = StringUtils.replace(newFileName, "+", "%20");
                if (newFileName.length() > 150) {
                    newFileName = new String(filename.getBytes("GB2312"), "ISO8859-1");
                    newFileName = StringUtils.replace(newFileName, " ", "%20");
                }
                return newFileName;
            }
            if ((agent != null) && (-1 != agent.indexOf("Mozilla"))) {
                return new String(filename.getBytes("UTF-8"), "ISO8859-1");
            }
            return filename;
        } catch (Exception ex) {
            return filename;
        }
    }

}
