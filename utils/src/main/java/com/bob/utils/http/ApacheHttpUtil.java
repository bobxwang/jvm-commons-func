package com.bob.utils.http;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.net.ssl.SSLContext;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 〈〉
 *
 * @author wangxiang
 * @create 2019-05-23
 */
public class ApacheHttpUtil {

  // 编码格式。发送编码格式统一用UTF-8
  private static final String ENCODING = "utf-8";
  // 设置连接超时时间，单位毫秒。
  private static final int CONNECT_TIMEOUT = 6000;
  // 请求获取数据的超时时间(即响应时间)，单位毫秒。
  private static final int SOCKET_TIMEOUT = 6000;

  private static final Logger logger = LoggerFactory.getLogger(ApacheHttpUtil.class);

  /**
   * setConnectTimeout：设置连接超时时间，单位毫秒。 setConnectionRequestTimeout：设置从connect
   * Manager(连接池)获取Connection超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。 setSocketTimeout：请求获取数据的超时时间(即响应时间)，单位毫秒。
   * 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
   */
  private static final RequestConfig requestConfig = RequestConfig.custom()
      .setConnectTimeout(CONNECT_TIMEOUT)
      .setSocketTimeout(SOCKET_TIMEOUT)
      .build();

  public static HttpResult doGet(String url) throws Exception {
    return doGet(url, ENCODING);
  }

  public static HttpResult doGet(String url, String encoding) throws Exception {
    return doGet(url, null, null, encoding);
  }

  public static HttpResult doGet(String url, Map<String, String> params, String encoding)
      throws Exception {
    return doGet(url, null, params, encoding);
  }

  public static HttpResult doGet(String url, Map<String, String> headers,
      Map<String, String> params, String encoding) throws Exception {
    URIBuilder uriBuilder = new URIBuilder(url);
    uriBuilder.setParameters(covertParams2NVPS(params));
    HttpGet httpGet = new HttpGet(uriBuilder.build());
    packageHeader(headers, httpGet);
    return doRequest(httpGet, encoding);
  }

  public static HttpResult doPost(String url) throws Exception {
    return doPost(url, ENCODING);
  }

  public static HttpResult doPost(String url, String encoding) throws Exception {
    return doPost(url, null, null, encoding);
  }

  public static HttpResult doPost(String url, Map<String, String> params, String encoding)
      throws Exception {
    return doPost(url, null, params, encoding);
  }

  public static HttpResult doPost(String url, Map<String, String> headers,
      Map<String, String> params, String encoding) throws Exception {

    HttpPost httpPost = new HttpPost(url);
    packageHeader(headers, httpPost);
    packageParam(params, httpPost, encoding);
    return doRequest(httpPost, encoding);
  }

  /**
   * @param pathName absolute local file path
   */
  public static HttpResult doPostForm(String url, String pathName) throws Exception {
    return doPostForm(url, "media", pathName, null);
  }

  public static HttpResult doPostForm(String url, File file) throws Exception {
    return doPostForm(url, "media", file, null);
  }

  public static HttpResult doPostForm(String url, String name, String pathName,
      Map<String, String> params)
      throws Exception {
    File file = new File(pathName);
    if (!file.isFile() || !file.exists()) {
      throw new Exception("pathname file is not exist");
    }

    return doPostForm(url, name, file, params);
  }

  public static HttpResult doPostForm(String url, String name, File file,
      Map<String, String> params)
      throws Exception {
    HttpPost httppost = new HttpPost(url);

    FileBody body = new FileBody(file);
    MultipartEntityBuilder builder = MultipartEntityBuilder.create().addPart(name, body);
    // 附加表单
    if (params != null && params.size() > 0) {
      for (Map.Entry<String, String> entry : params.entrySet()) {
        if (entry.getValue() == null) {
          continue;
        }
        builder.addTextBody(entry.getKey(), entry.getValue(), ContentType
            .create("text/plain", StandardCharsets.UTF_8));
      }
    }
    HttpEntity entity = builder.build();
    httppost.setEntity(entity);
    return doRequest(httppost, "utf-8");
  }

  /**
   * @param param json format string
   */
  public static HttpResult doPostJson(String url, String param) throws Exception {
    HttpPost httpPost = new HttpPost(url);
    httpPost.addHeader("Content-Type", "application/json; charset=UTF-8");
    httpPost.setEntity(new StringEntity(param, StandardCharsets.UTF_8.name()));
    return doRequest(httpPost, "utf-8");
  }

  /**
   * @param param xml format string
   */
  public static HttpResult doPostXml(String url, String param) throws Exception {
    HttpPost httpPost = new HttpPost(url);
    // 设置请求头
    httpPost.addHeader("Content-Type", "application/xml; charset=UTF-8");
    // 设置请求参数
    httpPost.setEntity(new StringEntity(param, StandardCharsets.UTF_8.name()));

    return doRequest(httpPost, "utf-8");
  }

  public static HttpResult doPut(String url) throws Exception {
    return doPut(url, ENCODING);
  }

  public static HttpResult doPut(String url, String encoding) throws Exception {
    return doPut(url, null, encoding);
  }

  public static HttpResult doPut(String url, Map<String, String> params, String encoding)
      throws Exception {
    HttpPut httpPut = new HttpPut(url);
    packageParam(params, httpPut, encoding);
    return doRequest(httpPut, encoding);
  }

  public static ByteArrayOutputStream downloandFile(String url) throws IOException {
    HttpGet httpGet = new HttpGet(url);
    CloseableHttpClient httpClient = HttpClients.custom()
        .setConnectionManager(cm)
        .setRedirectStrategy(new LaxRedirectStrategy()) // 确保302自动跳转
        .build();
    ByteArrayOutputStream baos = new ByteArrayOutputStream(1024 * 1024);
    try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
      HttpEntity entity = httpResponse.getEntity();
      IOUtils.copy(entity.getContent(), baos);
    }
    logger.info("file --> " + url + " download success");
    return baos;
  }

  public static void packageParam(Map<String, String> params,
      HttpEntityEnclosingRequestBase httpMethod, String encoding)
      throws UnsupportedEncodingException {
    if (params != null) {
      List<NameValuePair> nvps = new ArrayList<>();
      Set<Entry<String, String>> entrySet = params.entrySet();
      for (Entry<String, String> entry : entrySet) {
        nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
      }
      if (encoding != null) {
        httpMethod.setEntity(new UrlEncodedFormEntity(nvps, encoding));
      } else {
        httpMethod.setEntity(new UrlEncodedFormEntity(nvps, ENCODING));
      }
    }
  }

  private static List<NameValuePair> covertParams2NVPS(Map<String, String> params) {
    List<NameValuePair> pairs = new ArrayList<>();
    if (params != null) {
      for (Map.Entry<String, String> param : params.entrySet()) {
        pairs.add(new BasicNameValuePair(param.getKey(), param.getValue()));
      }
    }
    return pairs;
  }

  private static void packageHeader(Map<String, String> params, HttpRequestBase httpMethod) {
    if (params != null) {
      Set<Entry<String, String>> entrySet = params.entrySet();
      for (Entry<String, String> entry : entrySet) {
        httpMethod.setHeader(entry.getKey(), entry.getValue());
      }
    }
  }

  private static HttpResult doRequest(HttpRequestBase httpMethod, String encoding)
      throws Exception {
    HttpResult result = new HttpResult();
    httpMethod.setConfig(requestConfig);
    CloseableHttpClient httpClient = HttpClients.custom()
        .setConnectionManager(cm)
        .setRedirectStrategy(new LaxRedirectStrategy()) // 确保302自动跳转
        .build();
    try (CloseableHttpResponse httpResponse = httpClient.execute(httpMethod)) {
      HttpEntity entity = httpResponse.getEntity();
      if (encoding == null) {
        result.setContent(EntityUtils.toString(entity, ENCODING));
      } else {
        result.setContent(EntityUtils.toString(entity, encoding));
      }
      result.setCode(httpResponse.getStatusLine().getStatusCode());
      HeaderIterator headerIterator = httpResponse.headerIterator();
      Map<String, String> m = new HashMap<>();
      while (headerIterator.hasNext()) {
        Header header = headerIterator.nextHeader();
        m.put(header.getName(), header.getValue());
      }
      result.setRsHeaders(m);
      return result;
    }
  }

  private static PoolingHttpClientConnectionManager cm;

  static {
    cm = new PoolingHttpClientConnectionManager(getRegistry());
    cm.setMaxTotal(200);
    cm.setDefaultMaxPerRoute(5);
  }

  private static Registry<ConnectionSocketFactory> getRegistry() {
    Registry<ConnectionSocketFactory> registry;
    try {
      registry = RegistryBuilder.<ConnectionSocketFactory>create()
          .register("http", new PlainConnectionSocketFactory())
          .register("https", getSSLFactory())
          .build();
    } catch (Exception e) {
      throw new RuntimeException("can not obtain http client instance");
    }
    return registry;
  }

  private static SSLConnectionSocketFactory getSSLFactory() throws Exception {
    SSLContextBuilder sslBuilder = new SSLContextBuilder()
        .loadTrustMaterial(null, (chain, authType) -> true);
    SSLContext sslContext = sslBuilder.build();
    SSLConnectionSocketFactory sslCsf = new SSLConnectionSocketFactory(sslContext,
        new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2"}, null,
        NoopHostnameVerifier.INSTANCE);
    return sslCsf;
  }

  private static SSLConnectionSocketFactory getSSLFactory(String path, String password)
      throws Exception {
    KeyStore keyStore = KeyStore.getInstance("PKCS12");
    FileInputStream input = new FileInputStream(new File(path));
    try {
      keyStore.load(input, password.toCharArray());
    } finally {
      input.close();
    }
    SSLContext sslContext = SSLContexts.custom().loadKeyMaterial(keyStore, password.toCharArray())
        .build();
    // 获取HTTPS连接工厂，指定TSL版本
    return new SSLConnectionSocketFactory(sslContext,
        new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2"}, null,
        SSLConnectionSocketFactory.getDefaultHostnameVerifier());
  }
}
