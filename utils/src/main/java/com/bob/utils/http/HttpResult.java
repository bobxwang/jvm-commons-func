package com.bob.utils.http;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * 〈〉
 *
 * @author wangxiang
 * @create 2019-05-23
 */
public class HttpResult implements Serializable {

  private static final long serialVersionUID = 2168152194164783950L;
  private Integer code;
  private String content;
  private Map<String, String> rsHeaders;

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Map<String, String> getRsHeaders() {
    if (rsHeaders == null) {
      rsHeaders = new HashMap<>();
    }
    return Collections.unmodifiableMap(rsHeaders);
  }

  public void setRsHeaders(Map<String, String> rsHeaders) {
    this.rsHeaders = rsHeaders;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", HttpResult.class.getSimpleName() + "[", "]")
        .add("code=" + code)
        .add("content='" + content + "'")
        .add("rsHeaders=" + rsHeaders)
        .toString();
  }
}