package com.bob.utils;


import org.junit.Assert;
import org.junit.Test;

/**
 * 〈〉
 *
 * @author wangxiang
 * @create 2019-02-15
 */
public class IpConverterTest {

  @Test
  public void test() {

    String ip = "192.168.45.34";
    Long l = IpConverter.ip2Long(ip);
    String iip = IpConverter.long2Ip(l);

    Assert.assertTrue("equal", ip.contentEquals(iip));
  }
}