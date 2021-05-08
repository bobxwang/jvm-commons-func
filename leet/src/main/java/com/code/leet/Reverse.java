package com.code.leet;

/**
 * 反转数字
 *
 * @author wangxiang
 * @create 2021/4/9
 */
public class Reverse {

  public static void main(String[] args) {

    System.out.println(reverse(124679));
    System.out.println(reverse(124670));
    System.out.println(reverse(-124670));
  }

  private static Integer reverse(Integer x) {
    Integer rev = 0;
    while (x != 0) {
      int y = x % 10;
      x = x / 10;
      if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10
          && y > Integer.MAX_VALUE % 10)) {
        rev = 0;
        break;
      } else if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10
          && x < Integer.MIN_VALUE % 10)) {
        rev = 0;
        break;
      }

      rev = rev * 10 + y;
    }
    return rev;
  }
}
