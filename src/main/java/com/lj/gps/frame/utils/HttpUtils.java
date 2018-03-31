package com.lj.gps.frame.utils;

import com.lj.gps.frame.exception.SystemErrorException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class HttpUtils {

    protected static Log logger = LogFactory.getLog(HttpUtils.class);

    private static PoolingHttpClientConnectionManager cm;
    private static String EMPTY_STR = "";

    public static String UTF_8 = "UTF-8";
    public static String GB2312 = "GB2312";
    public static String GBK = "GBK";

    private static void init() {
        if (cm == null) {
            cm = new PoolingHttpClientConnectionManager();
            cm.setMaxTotal(50);// 整个连接池最大连接数
            cm.setDefaultMaxPerRoute(5);// 每路由最大连接数，默认值是2
        }
    }

    /**
     * 通过连接池获取HttpClient
     *
     * @return
     */
    public static CloseableHttpClient getHttpClient() {
        init();
        return HttpClients.custom().setConnectionManager(cm).build();
    }

    public static String get(String url) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        return getDefaultConfigResult(httpGet);
    }

    public static HttpResponse getGetResponse(String url) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        return getDefaultConfigHttpResponse(httpGet);
    }

    public static String get(String url, String charset) throws Exception {
        HttpGet httpGet = new HttpGet(url);
        //设置请求的报文头部的编码
        httpGet.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=" + charset));
        //设置期望服务端返回的编码
        httpGet.setHeader(new BasicHeader("Accept", "text/plain;charset=" + charset));
        return getDefaultConfigResult(httpGet);
    }

    public static String get(String url, Map<String, String> params) throws Exception {
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);

        HttpGet httpGet = new HttpGet(ub.build());

        return getDefaultConfigResult(httpGet);
    }

    /**
     * Get请求
     *
     * @param url
     * @param params
     * @param timeoutSecond
     * @param charset
     * @return
     * @throws Exception
     */
    public static String get(String url, Map<String, String> params, int timeoutSecond, String charset) throws Exception {
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);

        HttpGet httpGet = new HttpGet(ub.build());
        //设置请求的报文头部的编码
        httpGet.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=" + charset));
        //设置期望服务端返回的编码
        httpGet.setHeader(new BasicHeader("Accept", "text/plain;charset=" + charset));

        int timeoutMillis = timeoutSecond * 1000;
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(timeoutMillis)
                .setConnectionRequestTimeout(timeoutMillis)
                .setSocketTimeout(timeoutMillis)
                .setExpectContinueEnabled(false)
                .build();
        // 设置请求和传输超时时间
        httpGet.setConfig(requestConfig);

        return getDefaultConfigResult(httpGet);
    }

    public static String get(String url, Map<String, String> headers, Map<String, String> params) throws Exception {
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);

        HttpGet httpGet = new HttpGet(ub.build());
        for (Map.Entry<String, String> param : headers.entrySet()) {
            httpGet.addHeader(param.getKey(), param.getValue());
        }
        return getDefaultConfigResult(httpGet);
    }

    public static String post(String url) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        return getDefaultConfigResult(httpPost);
    }

    public static String post(String url, Map<String, String> params) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
        return getDefaultConfigResult(httpPost);
    }

    /**
     * POST请求
     *
     * @param url
     * @param params
     * @param timeoutSecond 单位秒
     * @param charset
     * @return
     * @throws Exception
     */
    public static String post(String url, Map<String, String> params, int timeoutSecond, String charset) throws Exception {
        HttpPost httpPost = new HttpPost(url);
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, charset));

        int timeoutMillis = timeoutSecond * 1000;
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(timeoutMillis)
                .setConnectionRequestTimeout(timeoutMillis)
                .setSocketTimeout(timeoutMillis)
                .setExpectContinueEnabled(false)
                .build();
        // 设置请求和传输超时时间
        httpPost.setConfig(requestConfig);

        return getDefaultConfigResult(httpPost);
    }

    public static String post(String url, Map<String, String> headers, Map<String, String> params)
            throws Exception {
        HttpPost httpPost = new HttpPost(url);

        for (Map.Entry<String, String> param : headers.entrySet()) {
            httpPost.addHeader(param.getKey(), param.getValue());
        }

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));

        return getDefaultConfigResult(httpPost);
    }

    public static String post(String url, Map<String, Object> headers, String strBody) throws Exception {
        HttpPost httpPost = new HttpPost(url);

        for (Map.Entry<String, Object> param : headers.entrySet()) {
            httpPost.addHeader(param.getKey(), String.valueOf(param.getValue()));
        }
        httpPost.setEntity(new StringEntity(strBody, UTF_8));
        return getDefaultConfigResult(httpPost);
    }

    private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, String> params) {
        ArrayList<NameValuePair> pairs = new ArrayList<>();
        for (Map.Entry<String, String> param : params.entrySet()) {
            pairs.add(new BasicNameValuePair(param.getKey(), param.getValue()));
        }
        return pairs;
    }

//    /**
//     * 作者:sanri <br/>
//     * 时间:2017-7-26下午5:59:57<br/>
//     * 功能:向路径提交 xml 信息 <br/>
//     *
//     * @param url
//     * @param xml
//     * @return
//     * @throws IOException
//     * @throws IllegalArgumentException
//     */
//    public static String postXml(String url, String xml) throws IOException, SystemErrorException {
//        HttpPost httpPost = new HttpPost(url);
//        httpPost.addHeader("Content-Type", "text/xml; charset=utf-8");
//        httpPost.addHeader("User-Agent", "sanri-user-agent");
//        HttpEntity xmlEntity = new StringEntity(xml, ContentType.create("text/xml", Consts.UTF_8));
//        httpPost.setEntity(xmlEntity);
//        return getDefaultConfigResult(httpPost);
//    }

    /**
     * 作者:sanri <br/>
     * 时间:2017-7-26下午5:59:57<br/>
     * 功能:向路径提交 xml 信息 <br/>
     *
     * @param url
     * @param xml
     * @return
     * @throws IOException
     * @throws IllegalArgumentException
     */
    public static String postXml(String url, String xml, String encoding, int timeout) throws IOException, SystemErrorException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "text/xml; charset="+encoding);
        httpPost.addHeader("User-Agent", "sanri-user-agent");
        HttpEntity xmlEntity = new StringEntity(xml, ContentType.create("text/xml", encoding));
        httpPost.setEntity(xmlEntity);

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(60000)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(timeout)
                .setExpectContinueEnabled(false)
                .build();
        httpPost.setConfig(requestConfig);// 设置请求和传输超时时间

        return getCustomConfigResult(httpPost);
    }

    public static String postXml(String url, String xml) throws IOException, SystemErrorException {
       return  postXml(url,xml,Consts.UTF_8.toString(),60000);
    }
    /**
     * 处理Http请求,默认配置
     * <p>
     * setConnectTimeout：设置连接超时时间，单位毫秒。
     * setConnectionRequestTimeout：设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
     * setSocketTimeout：请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
     *
     * @param request
     * @return
     */
    public static String getDefaultConfigResult(HttpRequestBase request) throws SystemErrorException {

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(10000)
                .setExpectContinueEnabled(false)
                .build();
        request.setConfig(requestConfig);// 设置请求和传输超时时间

        // CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpClient httpClient = getHttpClient();
        try {
            CloseableHttpResponse response = httpClient.execute(request); //执行请求
            // response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // long len = entity.getContentLength();// -1 表示长度未知
                String result = EntityUtils.toString(entity);
                response.close();
                // httpClient.close();
                return result;
            }
        } catch (ClientProtocolException e) {
            logger.error("[maperror] HttpClientUtil ClientProtocolException : " + e.getMessage());
            throw new SystemErrorException("HttpClientUtil ClientProtocolException ：" + e.getMessage(),e);
        } catch (IOException e) {
            logger.error("[maperror] HttpClientUtil IOException : " + e.getMessage());
            throw new SystemErrorException("HttpClientUtil IOException ：" + e.getMessage(),e);
        } finally {

        }
        return EMPTY_STR;
    }

    /**
     * 处理Http请求,默认配置
     * <p>
     * setConnectTimeout：设置连接超时时间，单位毫秒。
     * setConnectionRequestTimeout：设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
     * setSocketTimeout：请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
     *
     * @param request
     * @return
     */
    private static HttpResponse getDefaultConfigHttpResponse(HttpRequestBase request) throws Exception {

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(10000)
                .setExpectContinueEnabled(false)
                .build();
        request.setConfig(requestConfig);// 设置请求和传输超时时间

        // CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpClient httpClient = getHttpClient();
        CloseableHttpResponse response = httpClient.execute(request); //执行请求
        return response;
    }

    /**
     * 处理Http请求，自定义配置
     * <p>
     * setConnectTimeout：设置连接超时时间，单位毫秒。
     * setConnectionRequestTimeout：设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。
     * setSocketTimeout：请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
     *
     * @param request
     * @return
     */
    private static String getCustomConfigResult(HttpRequestBase request) throws SystemErrorException {

//        RequestConfig requestConfig = RequestConfig.custom()
//                .setConnectTimeout(60000)
//                .setConnectionRequestTimeout(5000)
//                .setSocketTimeout(60000)
//                .setExpectContinueEnabled(false)
//                .build();
//        request.setConfig(requestConfig);// 设置请求和传输超时时间

        // CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpClient httpClient = getHttpClient();
        try {
            CloseableHttpResponse response = httpClient.execute(request); //执行请求
            // response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // long len = entity.getContentLength();// -1 表示长度未知
                String result = EntityUtils.toString(entity);
                response.close();
                // httpClient.close();
                return result;
            }
        } catch (ClientProtocolException e) {
            logger.error("[maperror] HttpClientUtil ClientProtocolException : " + e.getMessage());
            throw new SystemErrorException("HttpClientUtil ClientProtocolException ：" + e.getMessage(),e);
        } catch (IOException e) {
            logger.error("[maperror] HttpClientUtil IOException : " + e.getMessage());
            throw new SystemErrorException("HttpClientUtil IOException ：" + e.getMessage(),e);
        } finally {

        }
        return EMPTY_STR;
    }

    /**
     * 获取网络资源文件流
     *
     * @param resourceUrl
     * @return
     */
    public static InputStream getURLResourceInputStream(String resourceUrl) {
        URL url = null;
        HttpURLConnection conn = null;
        BufferedInputStream bin = null;
        try {
            /*将网络资源地址传给,即赋值给url*/
            url = new URL(resourceUrl);
            /*此为联系获得网络资源的固定格式用法，以便后面的in变量获得url截取网络资源的输入流*/
            conn = (HttpURLConnection) url.openConnection();
            //模拟浏览器访问
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //文件缓存流
            bin = new BufferedInputStream(conn.getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bin;
    }

}