package com.bob.utils;

/**
 * 〈〉
 *
 * @author wangxiang
 * @create 2019-02-15
 */
public final class IpConverter {

  public final static long ip2Long(String ipAddress) {

    long result = 0;

    String[] ipAddressInArray = ipAddress.split("\\.");
    for (int i = 3; i >= 0; i--) {
      long ip = Long.parseLong(ipAddressInArray[3 - i]);
      result |= ip << (i * 8);
    }

    return result;
  }

  public final static String long2Ip(long i) {

    return ((i >> 24) & 0xFF) +
        "." + ((i >> 16) & 0xFF) +
        "." + ((i >> 8) & 0xFF) +
        "." + (i & 0xFF);

  }
}
