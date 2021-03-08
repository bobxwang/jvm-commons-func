package com.bob.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 * 〈〉
 *
 * @author wangxiang
 * @create 2021/3/8
 */
public class ListTest {

  @Test
  public void testArrayList() {

    List<String> stringList = new ArrayList<>();
    stringList.add(null);
    Assert.assertTrue(stringList.size() > 0);
  }

  @Test
  public void testLinkedList() {

    List<String> stringList = new LinkedList<>();
    stringList.add(null);
    Assert.assertTrue(stringList.size() > 0);
  }
}
