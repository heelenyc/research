package com.heelenyc.research.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

/**
 * Http client util class
 * 
 */
public final class HttpClientUtil {
    private static Log logger = LogFactory.getLog(HttpClientUtil.class);
    private static volatile HttpClient httpClient = null;

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            System.out.println(HttpClientUtil.getUrlStatusCode("http://220.181.111.86/"));
            Thread.sleep(2000);
            // httpClient.getConnectionManager().closeIdleConnections(2,
            // TimeUnit.SECONDS);
            // httpClient.getConnectionManager().closeExpiredConnections();
        }
    }

    /**
     * Constructor
     */
    private HttpClientUtil() {
    }

    /**
     * Download data
     * 
     * @param url
     *            url
     * @return bytes data
     */
    public static byte[] downloadData(String url) {
        HttpGet httpGet = null;
        try {
            httpGet = new HttpGet(url);
            HttpResponse response = getHttpClient().execute(httpGet);
            return responseToByte(response);
        } catch (Exception e) {
            logger.error("Send Get request to url faild, url: " + url, e);
        } finally {
            if (httpGet != null) {
                httpGet.abort();
            }
        }
        return null;
    }

    /**
     * Send get to URL.
     * 
     * @param url
     *            url
     * @return result content
     */
    public static String sendGet(String url) {
        HttpGet httpGet = null;
        try {
            httpGet = new HttpGet(url);
            HttpResponse response = getHttpClient().execute(httpGet);
            return responseToString(response);
        } catch (Exception e) {
            logger.error("Send Get request to url faild, url: " + url, e);
        } finally {
            if (httpGet != null) {
                httpGet.abort();
            }
        }
        return null;
    }

    public static int getUrlStatusCode(String url) {
        HttpGet httpGet = null;
        try {
            httpGet = new HttpGet(url);
            HttpResponse response = getHttpClient().execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            return statusCode;
        } catch (Exception e) {
            logger.error("Send Get request to url faild, url: " + url, e);
            return -1;
        } finally {
            if (httpGet != null) {
                httpGet.abort();
            }
        }
    }

    /**
     * Send delete to URL.
     * 
     * @param url
     *            url
     * @return result content
     */
    public static String sendDelete(String url) {
        try {
            HttpDelete httpDelete = new HttpDelete(url);
            HttpResponse response = getHttpClient().execute(httpDelete);
            return responseToString(response);
        } catch (Exception e) {
            logger.error("Send Get request to url faild, url: " + url, e);
        }
        return null;
    }

    /**
     * 带有用户名和密码的GET
     * 
     * @param url
     *            访问的地址
     * @param userName
     *            校验用户名
     * @param password
     *            校验密码
     * @return String
     */
    public static String sendGetWithAuthor(String url, String userName, String password) {
        HttpGet httpGet = new HttpGet(url);
        try {
            DefaultHttpClient defaultHttpClient = (DefaultHttpClient) getHttpClient();
            defaultHttpClient.getCredentialsProvider().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));
            HttpResponse response = defaultHttpClient.execute(httpGet);
            return responseToString(response);
        } catch (Exception e) {
            logger.error("Send Get request to url faild, url: " + url, e);
        } finally {
            if (httpGet != null) {
                httpGet.abort();
            }
        }
        return null;
    }

    /**
     * Send post to URL with parameters by given encoding.
     * 
     * @param url
     *            url
     * @param parameterMap
     *            parameterMap
     * @return result content
     * @throws Exception
     *             Exception
     */
    public static String sendPost(String url, Map<String, String> parameterMap) throws Exception {
        return sendPost(url, parameterMap, null, "UTF-8");
    }

    /**
     * Send post to URL with parameters by given encoding.
     * 
     * @param url
     *            url
     * @param parameterMap
     *            parameterMap
     * @param encoding
     *            encoding
     * @return result content
     * @throws Exception
     *             Exception
     */
    public static String sendPost(String url, Map<String, String> parameterMap, String encoding) throws Exception {
        return sendPost(url, parameterMap, null, encoding);
    }

    /**
     * Send post to URL with parameters by given encoding.
     * 
     * @param url
     *            url
     * @param parameterMap
     *            parameterMap
     * @param headerMap
     *            headerMap
     * @param encoding
     *            encoding
     * @return result content
     * @throws Exception
     *             Exception
     */
    public static String sendPost(String url, Map<String, String> parameterMap, Map<String, String> headerMap, String encoding) throws Exception {
        StringEntity entity = null;

        if (parameterMap != null && !parameterMap.isEmpty()) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            for (Entry<String, String> entry : parameterMap.entrySet()) {
                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }

            try {
                // entity = new UrlEncodedFormEntity(params, encoding);
                entity = new StringEntity(URLEncodedUtils.format(params, encoding));
                entity.setContentType(URLEncodedUtils.CONTENT_TYPE);
            } catch (UnsupportedEncodingException e) {
                logger.error("Encode the parameter failed!", e);
            }
        }

        return sendPostWithEntity(url, entity, headerMap);
    }

    /**
     * Send post to URL with parameters by given encoding.
     * 
     * @param url
     *            url
     * @param parameterMap
     *            parameterMap
     * @return result content
     * @throws Exception
     *             Exception
     */
    public static String sendPostWithMultipart(String url, Map<String, ContentBody> parameterMap) throws Exception {
        MultipartEntity entity = null;
        if (parameterMap != null && !parameterMap.isEmpty()) {
            entity = new MultipartEntity();
            for (Entry<String, ContentBody> entry : parameterMap.entrySet()) {
                entity.addPart(entry.getKey(), entry.getValue());
            }
        }
        return sendPostWithEntity(url, entity, null);
    }

    private static String sendPostWithEntity(String url, HttpEntity entity, Map<String, String> headerMap) throws Exception {
        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(url);
            if (entity != null) {
                httpPost.setEntity(entity);
            }
            if (headerMap != null && !headerMap.isEmpty()) {
                for (Entry<String, String> entry : headerMap.entrySet()) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }
            HttpResponse response = getHttpClient().execute(httpPost);
            return responseToString(response);
        } catch (Exception e) {
            return null;
        } finally {
            if (httpPost != null) {
                httpPost.abort();
            }
        }
    }

    private static String responseToString(HttpResponse response) throws Exception {
        HttpEntity entity = getHttpEntity(response);
        if (entity == null) {
            return null;
        }
        return EntityUtils.toString(entity, "UTF-8");
    }

    private static byte[] responseToByte(HttpResponse response) throws Exception {
        HttpEntity entity = getHttpEntity(response);
        if (entity == null) {
            return null;
        }
        return EntityUtils.toByteArray(entity);
    }

    private static HttpEntity getHttpEntity(HttpResponse response) throws Exception {
        StatusLine statusLine = response.getStatusLine();
        if (statusLine.getStatusCode() >= 300) {
            logger.error(response);
            EntityUtils.consume(response.getEntity());
            throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
        } else {
            /*
             * if (logger.isDebugEnabled()) { logger.debug(response); }
             */
            return response.getEntity();
        }
    }

    /**
     * When HttpClient instance is no longer needed, shut down the connection
     * manager to ensure immediate deallocation of all system resources
     */
    public static void shutdown() {
        if (httpClient != null) {
            httpClient.getConnectionManager().shutdown();
            httpClient = null;
        }
    }

    /**
     * Closes all expired connections in the pool. Open connections in the pool
     * that have not been used for the timespan defined when the connection was
     * released will be closed. Currently allocated connections are not subject
     * to this method. Times will be checked with milliseconds precision.
     */
    public static void closeExpiredConnections() {
        if (httpClient != null) {
            httpClient.getConnectionManager().closeExpiredConnections();
        }
    }

    /**
     * Create an HttpClient with the ThreadSafeClientConnManager.
     * 
     * @return
     */
    private static HttpClient getHttpClient() {
        if (httpClient == null) {
            ThreadSafeClientConnManager connectionManager = new ThreadSafeClientConnManager();
            connectionManager.setDefaultMaxPerRoute(50); // 每个host最多50个连接
            connectionManager.setMaxTotal(500); // 一共500个连接
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient(connectionManager);
            HttpConnectionParams.setConnectionTimeout(defaultHttpClient.getParams(), 5000); // 5秒超时
            HttpConnectionParams.setSoTimeout(defaultHttpClient.getParams(), 5000);
            HttpConnectionParams.setTcpNoDelay(defaultHttpClient.getParams(), true);

            // 增加gzip支持
            defaultHttpClient.addRequestInterceptor(new AcceptEncodingRequestInterceptor());
            defaultHttpClient.addResponseInterceptor(new ContentEncodingResponseInterceptor());

            httpClient = defaultHttpClient;
        }
        return httpClient;
    }

    private static class AcceptEncodingRequestInterceptor implements HttpRequestInterceptor {
        @Override
        public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
            if (!request.containsHeader("Accept-Encoding")) {
                request.addHeader("Accept-Encoding", "gzip");
            }
        }
    }

    private static class ContentEncodingResponseInterceptor implements HttpResponseInterceptor {
        public void process(final HttpResponse response, final HttpContext context) throws HttpException {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                Header ceheader = entity.getContentEncoding();
                if (ceheader != null) {
                    HeaderElement[] codecs = ceheader.getElements();
                    for (int i = 0; i < codecs.length; i++) {
                        if ("gzip".equalsIgnoreCase(codecs[i].getName())) {
                            response.setEntity(new GzipDecompressingEntity(response.getEntity()));
                            return;
                        }
                    }
                }
            }
        }
    }

    private static class GzipDecompressingEntity extends HttpEntityWrapper {
        public GzipDecompressingEntity(final HttpEntity entity) {
            super(entity);
        }

        @Override
        public InputStream getContent() throws IOException {
            InputStream wrappedin = wrappedEntity.getContent();
            return new GZIPInputStream(wrappedin);
        }

        @Override
        public long getContentLength() {
            return -1;
        }
    }

    public static Map<String, String> getUrlParameterMap(String url) {
        Map<String, String> urlParameterMap = new HashMap<String, String>();
        String urlParameters = getUrlParameters(url);
        if (urlParameters == null)
            return urlParameterMap;
        String[] arrSplit = null;
        arrSplit = urlParameters.split("\\&");
        for (String strSplit : arrSplit) {
            String[] arrSplitEqual = null;
            arrSplitEqual = strSplit.split("\\=");
            if (arrSplitEqual.length > 1)
                urlParameterMap.put(arrSplitEqual[0], arrSplitEqual[1]);
            else {
                if (arrSplitEqual[0] != "")
                    urlParameterMap.put(arrSplitEqual[0], "");
            }
        }
        return urlParameterMap;
    }

    public static String getBaseUrl(String url) {
        String baseUrl = null;
        if (url.length() > 0)
            baseUrl = url.trim().split("\\?").length > 0 ? url.trim().split("\\?")[0] : "";
        return baseUrl;
    }

    private static String getUrlParameters(String url) {
        String urlParameters = null;
        if (url.length() > 0)
            urlParameters = url.trim().split("\\?").length > 1 ? url.trim().split("\\?")[1] : "";
        return urlParameters;
    }
}
