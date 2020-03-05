//package com.hewking.proxy;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.lang.com.hewking.reflect.Array;
//import java.net.InetSocketAddress;
//import java.net.Proxy;
//import java.net.URLEncoder;
//import java.nio.charset.Charset;
//import java.security.KeyStore;
//import java.security.KeyStoreException;
//import java.security.NoSuchAlgorithmException;
//import java.security.SecureRandom;
//import java.security.UnrecoverableKeyException;
//import java.security.cert.CertificateException;
//import java.security.cert.X509Certificate;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//
//import javax.net.ssl.HostnameVerifier;
//import javax.net.ssl.KeyManager;
//import javax.net.ssl.KeyManagerFactory;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.SSLSession;
//import javax.net.ssl.SSLSocketFactory;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.TrustManagerFactory;
//import javax.net.ssl.X509TrustManager;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.opechain.opewallet.common.Constants;
//import com.opechain.opewallet.common.com.hewking.utils.StringUtils;
//import com.opechain.opewallet.common.com.hewking.utils.http.Callback;
//import com.opechain.opewallet.common.com.hewking.utils.http.HttpRequest;
//import com.opechain.opewallet.common.com.hewking.utils.http.HttpResponse;
//import com.squareup.okhttp.Call;
//import com.squareup.okhttp.MediaType;
//import com.squareup.okhttp.OkHttpClient;
//import com.squareup.okhttp.Request;
//import com.squareup.okhttp.RequestBody;
//import com.squareup.okhttp.Response;
//
//public class HttpClientUtils {
//    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
//
//    /**
//     * 默认字符编码
//     */
//    public static final String DEFAULT_CHARSET = "utf-8";
//
//    /**
//     * http method
//     */
//    public static final String HTTP_METHOD_POST = "POST";
//    public static final String HTTP_METHOD_GET = "GET";
//
//    /**
//     * 默认超时设置
//     */
//    public static final int DEFAULT_READ_TIMEOUT = 20000;
//    public static final int DEFAULT_CONNECT_TIMEOUT = 10000;
//
//    /**
//     * http prefix
//     */
//    public static final String HTTP_PREFIX = "http://";
//    public static final String HTTPS_PREFIX = "https://";
//
//    public static final String ERROR_INVALID_CHARSET = "invalid charset ";
//    public static final String ERROR_INVALID_URL = "invalid url ";
//
//    public static final String proxyHost = "192.168.0.111";
//    public static final int proxyPort = 1080;
//    public static final String username = "user";
//    public static final String password = "123456";
//
//    static {
//        System.setProperty("sun.net.inetaddr.ttl", "3600");
//
//
//         /*System.setProperty("http.proxySet", "true");
//         System.setProperty("http.proxyHost", proxyHost);
//         System.setProperty("http.proxyPort", "" + proxyPort);
//
//         // 针对https也开启代理
//         System.setProperty("https.proxyHost", proxyHost);
//         System.setProperty("https.proxyPort", "" + proxyPort);*/
//
//    }
//
//    /**
//     * 执行post请求
//     *
//     * @param url
//     * @param params
//     * @return
//     */
//    public static String httpPost(String url, Map<String, String> params) {
//        return httpRequest(url, params, HTTP_METHOD_POST, DEFAULT_CHARSET, null, null);
//    }
//
//    /**
//     * 执行get请求
//     *
//     * @param url
//     * @param params
//     * @return
//     */
//    public static String httpGet(String url, Map<String, String> params) {
//        return httpRequest(url, params, HTTP_METHOD_GET, DEFAULT_CHARSET, null, null);
//    }
//
//    /**
//     * post json
//     *
//     * @param url
//     * @param json
//     * @return
//     */
//    public static String httpPostJson(String url, String json) {
//        Map<String, String> headers = new HashMap<>();
//        headers.put(HttpRequest.CONTENT_TYPE, HttpRequest.CONTENT_TYPE_JSON);
//        return httpRequest(url, null, HTTP_METHOD_POST, DEFAULT_CHARSET, headers, json);
//    }
//
//    /**
//     * 异步发送json请求
//     *
//     * @param url
//     * @param json
//     * @param tag
//     * @param callback
//     */
//    public static void asyncHttpPostJson(String url, String json, Object tag, Callback callback) {
//        HttpRequest request = new HttpRequest(url);
//        request.setTag(tag);
//        request.setPostData(json);
//
//        Map<String, String> headers = new HashMap<>();
//        headers.put(HttpRequest.CONTENT_TYPE, HttpRequest.CONTENT_TYPE_JSON);
//        request.setHeaders(headers);
//        request.setCharset(DEFAULT_CHARSET);
//        request.setMethod(HTTP_METHOD_POST);
//
//        asyncHttpRequest(request, callback);
//    }
//
//    /**
//     * post xml
//     *
//     * @param url
//     * @param xml
//     * @return
//     */
//    public static String httpPostXml(String url, String xml) {
//        Map<String, String> headers = new HashMap<>();
//        headers.put(HttpRequest.CONTENT_TYPE, HttpRequest.CONTENT_TYPE_XML);
//        return httpRequest(url, null, HTTP_METHOD_POST, DEFAULT_CHARSET, headers, xml);
//    }
//
//    /**
//     * 发送微信双向证书请求
//     *
//     * @param url
//     * @param xml
//     * @param clientKeyStorePath
//     *            客户端证书apiclient_cert.p12在文件系统的全路径名
//     * @param clientKeyStorePass
//     *            客户端证书密码
//     * @return
//     */
//    public static String httpPostWxXml(String url, String xml, String clientKeyStorePath, String clientKeyStorePass) {
//        Map<String, String> headers = new HashMap<>();
//        headers.put(HttpRequest.CONTENT_TYPE, HttpRequest.CONTENT_TYPE_XML);
//        HttpRequest request = new HttpRequest(url);
//        request.setHeaders(headers);
//        request.setPostData(xml);
//
//        request.setClientKeyStorePath(clientKeyStorePath);
//        request.setClientKeyStorePass(clientKeyStorePass);
//        request.setPrivateKeyPass(clientKeyStorePass);
//        request.setHostnameVerify(false);
//
//        request.setSslProtocol("TLSv1");
//        request.setKeyStoreType("PKCS12");
//
//        request.setMethod(HTTP_METHOD_POST);
//        HttpResponse response = httpRequest(request);
//        if (response == null || !response.isSuccess()) {
//            return null;
//        } else {
//            return response.getContent();
//        }
//    }
//
//    /**
//     * 执行http请求
//     *
//     * @param url
//     * @param params
//     * @param method
//     * @param charSet
//     * @param headers
//     * @param postData
//     * @return
//     */
//    public static String httpRequest(String url, Map<String, String> params, String method, String charSet,
//            Map<String, String> headers, String postData) {
//        HttpRequest request = new HttpRequest(url);
//        if (null != params) {
//            request.setParameters(params);
//        }
//        if (null != headers) {
//            request.setHeaders(headers);
//        }
//
//        if (null != postData) {
//            request.setPostData(postData);
//        }
//        request.setCharset(charSet);
//        request.setMethod(method);
//        HttpResponse response = httpRequest(request);
//        if (response == null || !response.isSuccess()) {
//            return null;
//        } else {
//            return response.getContent();
//        }
//    }
//
//    /**
//     * 执行http请求
//     *
//     * @param request
//     * @return
//     */
//    public static HttpResponse httpRequest(HttpRequest request) {
//        Call call = buildOkHttpCall(request);
//
//        HttpResponse response = null;
//        try {
//
//            Response okHttpResponse = call.execute();
//
//            response = parseOkHttpResponse(okHttpResponse);
//            if (null != response) {
//                response.setRequest(request);
//            }
//        } catch (IOException e) {
//            logger.error("http call failed : ", e);
//        }
//
//        return response;
//    }
//
//    /**
//     * 异步调用http请求
//     *
//     * @param httpRequest
//     * @param callback
//     */
//    public static void asyncHttpRequest(final HttpRequest httpRequest, Callback callback) {
//        assert null != callback;
//        Call call = buildOkHttpCall(httpRequest);
//
//        if (null == call) {
//            callback.onFailure(httpRequest, null);
//            return;
//        }
//
//        call.enqueue(new com.squareup.okhttp.Callback() {
//
//            @Override
//            public void onFailure(Request request, IOException e) {
//                logger.error("http call failed : ", e);
//                callback.onFailure(httpRequest, e);
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//                HttpResponse httpResponse = parseOkHttpResponse(response);
//                httpResponse = httpResponse == null ? new HttpResponse(0) : httpResponse;
//                httpResponse.setRequest(httpRequest);
//                callback.onResponse(httpResponse);
//            }
//
//        });
//    }
//
//    /**
//     * 解析okhttp响应
//     *
//     * @param okHttpResponse
//     * @return
//     */
//    private static HttpResponse parseOkHttpResponse(Response okHttpResponse) {
//        if (null == okHttpResponse) {
//            return null;
//        }
//
//        HttpResponse response = new HttpResponse(okHttpResponse.code());
//        if (okHttpResponse.isSuccessful()) {
//            try {
//                response.setContent(okHttpResponse.body().com.hewking.string());
//            } catch (IOException e) {
//                logger.error("parse response failed", e);
//            }
//        } else {
//            response.setErrorMessage(okHttpResponse.message());
//        }
//        if (null != okHttpResponse.headers()) {
//            response.setHeaders(okHttpResponse.headers().toMultimap());
//        }
//
//        return response;
//    }
//
//    /**
//     * 构建okhttp call
//     *
//     * @param request
//     * @return
//     */
//    private static Call buildOkHttpCall(HttpRequest request) {
//        String url = request.getUrl();
//        Map<String, Object> parameters = request.getParameters();
//
//        String charset = request.getCharset();
//        Map<String, Object> headers = request.getHeaders();
//
//        if (url == null || url.trim().length() == 0) {
//            throw new IllegalArgumentException(ERROR_INVALID_URL + url);
//        }
//        Charset.forName(charset);
//
//        String baseUrl = retrieveUrlFromRequest(request);
//
//        String method = retrieveMethodFromRequest(request);
//
//        int index = baseUrl.indexOf('?');
//
//        String queryString = mapToQueryString(parameters, charset);
//        String targetUrl;
//        if (method.equals(HTTP_METHOD_POST)) {
//            targetUrl = baseUrl;
//        } else {
//            if (index > 0) {
//                targetUrl = baseUrl + "&" + queryString;
//            } else {
//                targetUrl = baseUrl + "?" + queryString;
//            }
//        }
//
//        Call call = null;
//
//        try {
//            Request.Builder builder = new Request.Builder().url(targetUrl);
//
//            String contentType = setOkHttpHeader(builder, headers, charset);
//
//            if (method.equals(HTTP_METHOD_POST)) {
//                String postData = queryString.length() == 0 ? request.getPostData() : queryString;
//                MediaType mediaType = MediaType.parse(contentType);
//                if (postData == null) {
//                    postData = "";
//                }
//                RequestBody body = RequestBody.create(mediaType, postData);
//
//                builder.post(body);
//            }
//
//            Request okHttpRequest = builder.build();
//
//            OkHttpClient httpClient = buildOkHttpClient(request);
//
//            call = httpClient.newCall(okHttpRequest);
//
//        } catch (Exception e) {
//            logger.warn("build okhttp call failed", e);
//
//        }
//
//        return call;
//    }
//
//    /**
//     * 从请求提取httpmethod
//     *
//     * @param request
//     * @return
//     */
//    private static String retrieveMethodFromRequest(HttpRequest request) {
//        String strMethod = request.getMethod();
//
//        String method = null;
//        if (strMethod != null) {
//            method = strMethod.toUpperCase();
//        }
//        if (method == null || !(method.equals(HTTP_METHOD_POST) || method.equals(HTTP_METHOD_GET))) {
//            throw new IllegalArgumentException("Invalid http method : " + method);
//        }
//        return method;
//    }
//
//    /**
//     * 从请求提取url
//     *
//     * @param request
//     * @return
//     */
//    private static String retrieveUrlFromRequest(HttpRequest request) {
//        String url = request.getUrl();
//        String charset = request.getCharset();
//
//        if (url == null || url.trim().length() == 0) {
//            throw new IllegalArgumentException(ERROR_INVALID_URL + url);
//        }
//
//        String baseUrl = url.trim();
//        if (!baseUrl.toLowerCase().startsWith(HTTP_PREFIX) && !baseUrl.toLowerCase().startsWith(HTTPS_PREFIX)) {
//            baseUrl = HTTP_PREFIX + baseUrl;
//        }
//
//        int index = baseUrl.indexOf('?');
//        if (index > 0) {
//            try {
//                baseUrl = urlEncode(baseUrl, charset);
//            } catch (UnsupportedEncodingException e) {
//                logger.error(ERROR_INVALID_CHARSET + charset, e);
//                throw new IllegalArgumentException(ERROR_INVALID_CHARSET + charset);
//            }
//        } else if (index == 0) {
//            throw new IllegalArgumentException(ERROR_INVALID_URL + url);
//        }
//
//        return baseUrl;
//    }
//
//    /**
//     * 设置okhttp请求头
//     *
//     * @param builder
//     * @param headers
//     * @param charset
//     * @return 文档类型
//     */
//    private static String setOkHttpHeader(Request.Builder builder, Map<String, Object> headers, String charset) {
//        boolean hasContentType = false;
//        boolean hasUserAgent = false;
//
//        String contentType = HttpRequest.CONTENT_TYPE_URLENCODED;
//        for (Entry<String, Object> entry : headers.entrySet()) {
//            String key = entry.getKey();
//            if (HttpRequest.CONTENT_TYPE.equalsIgnoreCase(key)) {
//                hasContentType = true;
//                contentType = (String) entry.getValue();
//            }
//            if ("user-agent".equalsIgnoreCase(key)) {
//                hasUserAgent = true;
//            }
//        }
//        if (!hasContentType || contentType.indexOf("charset") < 0) {
//            contentType += "; charset=" + charset;
//            headers.put(HttpRequest.CONTENT_TYPE, contentType);
//        }
//
//        if (!hasUserAgent) {
//            headers.put("user-agent", "PlatSystem");
//        }
//
//        if (!headers.isEmpty()) {
//            for (Entry<String, Object> entry : headers.entrySet()) {
//                String key = entry.getKey();
//                Object value = entry.getValue();
//                List<String> values = makeStringList(value);
//                for (String v : values) {
//                    builder.addHeader(key, v);
//                }
//            }
//        }
//
//        return contentType;
//    }
//
//    public static class MyAuthenticator implements com.squareup.okhttp.Authenticator {
//        /*
//         * public static class MyAuthenticator extends Authenticator { private String
//         * user = ""; private String password = "";
//         *
//         * public MyAuthenticator(String user, String password) { this.user = user;
//         * this.password = password; }
//         *
//         * protected PasswordAuthentication getPasswordAuthentication() { return new
//         * PasswordAuthentication(user, password.toCharArray()); }
//         */
//
//        @Override
//        public Request authenticate(Proxy com.hewking.proxy, Response response) throws IOException {
//            // TODO Auto-generated method stub
//            return null;
//        }
//
//        @Override
//        public Request authenticateProxy(Proxy com.hewking.proxy, Response response) throws IOException {
//            // TODO Auto-generated method stub
//            return null;
//        }
//    }
//
//    /**
//     * 构建okhttp客户端
//     *
//     * @param request
//     * @return
//     */
//    private static OkHttpClient buildOkHttpClient(HttpRequest request) {
//        int readTimeout = request.getReadTimeout();
//        int connectTimeout = request.getConnectTimeout();
//        OkHttpClient okHttpClient = new OkHttpClient();
//
//        okHttpClient.setReadTimeout(readTimeout, TimeUnit.MILLISECONDS);
//        okHttpClient.setConnectTimeout(connectTimeout, TimeUnit.MILLISECONDS);
//
//        // https support
//        if (request.getUrl().toLowerCase().startsWith(HTTPS_PREFIX)) {
//            okHttpClient.setSslSocketFactory(createSSLSocketFactory(request));
////            Proxy com.hewking.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));
////            okHttpClient.setProxy(com.hewking.proxy);
//            // Authenticator.setDefault(new MyAuthenticator(username, password));
//            // okHttpClient.setAuthenticator(new AuthenticatorAdapter());
//            if (request.isHostnameVerify()) {
//                okHttpClient.setHostnameVerifier(new TrustAllHostnameVerifier());
//            }
//        }
//
//        // disable cache
//        okHttpClient.setCache(null);
//
//        return okHttpClient;
//    }
//
//    /**
//     * url encode
//     *
//     * @param url
//     * @param charSet
//     * @return
//     * @throws UnsupportedEncodingException
//     */
//    public static String urlEncode(String url, String charSet) throws UnsupportedEncodingException {
//        if (url == null || url.trim().length() == 0) {
//            return url;
//        }
//        int splitIndex = url.indexOf('?');
//        if (splitIndex <= 0) {
//            return url;
//        }
//        String serviceUrl = url.substring(0, splitIndex);
//        String queryString = url.substring(splitIndex + 1, url.length());
//        String newQueryString = "";
//        StringBuilder strBuilder = new StringBuilder();
//        if (queryString.length() > 0) {
//            String[] nameValues = queryString.split("&");
//            for (String nameValue : nameValues) {
//                int index = nameValue.indexOf('=');
//                String pname;
//                String pvalue;
//                if (index < 0) {
//                    pname = nameValue;
//                    pvalue = "";
//                } else {
//                    pname = nameValue.substring(0, index);
//                    pvalue = nameValue.substring(index + 1, nameValue.length());
//                    pvalue = URLEncoder.encode(pvalue, charSet);
//                }
//                strBuilder.append(pname + "=" + pvalue + "&");
//            }
//            newQueryString = strBuilder.toString();
//            newQueryString = newQueryString.substring(0, newQueryString.length() - 1);
//        }
//        return serviceUrl + "?" + newQueryString;
//    }
//
//    /**
//     * 把Map转化为query com.hewking.string
//     *
//     * @param parameters
//     * @param charSet
//     * @return
//     */
//    public static String mapToQueryString(Map<String, Object> parameters, String charSet) {
//        String queryString = "";
//        StringBuilder strBuilder = new StringBuilder();
//        if (parameters != null && !parameters.isEmpty()) {
//            Set<Entry<String, Object>> entrySet = parameters.entrySet();
//            for (Entry<?, ?> entry : entrySet) {
//                try {
//                    String key = entry.getKey().toString();
//                    Object value = entry.getValue();
//                    List<?> values = makeStringList(value);
//                    for (Object v : values) {
//                        strBuilder.append(key + "=" + URLEncoder.encode(v == null ? "" : v.toString(), charSet) + "&");
//                    }
//                } catch (UnsupportedEncodingException e) {
//                    throw new IllegalArgumentException(ERROR_INVALID_CHARSET + charSet, e);
//                }
//            }
//
//            queryString = strBuilder.toString();
//            if (queryString.length() > 0) {
//                queryString = queryString.substring(0, queryString.length() - 1);
//            }
//        }
//        return queryString;
//    }
//
//    /**
//     * 把对象转化为字符串列表
//     *
//     * @param value
//     * @return
//     */
//    private static List<String> makeStringList(Object value) {
//        List<String> result = new ArrayList<>();
//        if (value == null) {
//            return result;
//        }
//        if (value.getClass().isArray()) {
//            for (int j = 0; j < Array.getLength(value); j++) {
//                Object obj = Array.get(value, j);
//                result.add(obj != null ? obj.toString() : "");
//            }
//            return result;
//        }
//
//        if (value instanceof Iterator) {
//            Iterator<?> it = (Iterator<?>) value;
//            while (it.hasNext()) {
//                Object obj = it.next();
//                result.add(obj != null ? obj.toString() : "");
//            }
//            return result;
//        }
//
//        if (value instanceof Collection) {
//            for (Object obj : (Collection<?>) value) {
//                result.add(obj != null ? obj.toString() : "");
//            }
//            return result;
//        }
//
//        if (value instanceof Enumeration) {
//            Enumeration<?> enumeration = (Enumeration<?>) value;
//            while (enumeration.hasMoreElements()) {
//                Object obj = enumeration.nextElement();
//                result.add(obj != null ? obj.toString() : "");
//            }
//            return result;
//        }
//        result.add(value.toString());
//        return result;
//    }
//
//    /**
//     * 创建ssl安全套接字工厂
//     *
//     * @param request
//     * @return
//     */
//    private static SSLSocketFactory createSSLSocketFactory(HttpRequest request) {
//
//        SSLSocketFactory sSLSocketFactory = null;
//
//        try {
//
//            SSLContext sc = SSLContext.getInstance(request.getSslProtocol() == null ? "TLS" : request.getSslProtocol());
//            sc.init(loadClientKeyManagers(request), loadTrustManagers(request), new SecureRandom());
//            sSLSocketFactory = sc.getSocketFactory();
//        } catch (Exception e) {
//            logger.error("create ssl socket failed", e);
//        }
//        return sSLSocketFactory;
//    }
//
//    /**
//     * 加载客户端密钥管理器
//     *
//     * @param request
//     * @return
//     * @throws NoSuchAlgorithmException
//     * @throws CertificateException
//     * @throws IOException
//     * @throws KeyStoreException
//     * @throws UnrecoverableKeyException
//     */
//    private static KeyManager[] loadClientKeyManagers(HttpRequest request) throws NoSuchAlgorithmException,
//            CertificateException, IOException, KeyStoreException, UnrecoverableKeyException {
//        String clientKeyStorePath = request.getClientKeyStorePath();
//        KeyManager[] managers = null;
//        if (!StringUtils.isNullOrEmpty(clientKeyStorePath)) {
//            FileInputStream instream = new FileInputStream(new File(clientKeyStorePath));
//            String keyStoreType = request.getKeyStoreType();
//            if (null == keyStoreType) {
//                keyStoreType = KeyStore.getDefaultType();
//            }
//
//            KeyStore clientKeyStore = KeyStore.getInstance(keyStoreType);
//            try {
//                clientKeyStore.load(instream,
//                        request.getClientKeyStorePass() != null ? request.getClientKeyStorePass().toCharArray() : null);
//            } finally {
//                instream.close();
//            }
//            KeyManagerFactory keyManagerFactory = KeyManagerFactory
//                    .getInstance(KeyManagerFactory.getDefaultAlgorithm());
//            keyManagerFactory.init(clientKeyStore,
//                    request.getPrivateKeyPass() != null ? request.getPrivateKeyPass().toCharArray() : null);
//            managers = keyManagerFactory.getKeyManagers();
//        }
//        return managers;
//    }
//
//    /**
//     * 加载授信证书管理器
//     *
//     * @param request
//     * @return
//     * @throws KeyStoreException
//     * @throws NoSuchAlgorithmException
//     * @throws CertificateException
//     * @throws IOException
//     */
//    private static TrustManager[] loadTrustManagers(HttpRequest request)
//            throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
//        String trustKeyStorePath = request.getTrustKeyStorePath();
//        TrustManager[] managers;
//        if (StringUtils.isNullOrEmpty(trustKeyStorePath)) {
//            managers = new TrustManager[] { new TrustAllManager() };
//        } else {
//            FileInputStream instream = new FileInputStream(new File(trustKeyStorePath));
//            String keyStoreType = request.getKeyStoreType();
//            if (null == keyStoreType) {
//                keyStoreType = KeyStore.getDefaultType();
//            }
//            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
//            try {
//                keyStore.load(instream,
//                        request.getTrustKeyStorePass() != null ? request.getTrustKeyStorePass().toCharArray() : null);
//            } finally {
//                instream.close();
//            }
//
//            TrustManagerFactory trustManagerFactory = TrustManagerFactory
//                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
//            trustManagerFactory.init(keyStore);
//            managers = trustManagerFactory.getTrustManagers();
//        }
//
//        return managers;
//    }
//
//    private static class TrustAllManager implements X509TrustManager {
//
//        @Override
//        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//            // do nothing
//
//        }
//
//        @Override
//        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//            // do nothing
//
//        }
//
//        @Override
//        public X509Certificate[] getAcceptedIssuers() {
//            // do nothing
//            return null;
//        }
//    }
//
//    private static class TrustAllHostnameVerifier implements HostnameVerifier {
//        @Override
//        public boolean verify(String hostname, SSLSession session) {
//            // do nothing
//            return true;
//        }
//    }
//
//    public static void main(String[] args) {
//        String httpGet = HttpClientUtils.httpGet(Constants.HUOBI_MARKET_URL, null);
//        System.out.println(httpGet);
//    }
//}
