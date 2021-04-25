package com.bob.source.sort;

import java.util.Arrays;
import java.util.List;

/**
 * 〈〉
 *
 * @author wangxiang
 * @create 2021/3/22
 */
public class Runner {

  public static void main(String[] args) {

    List<Integer> list = Arrays.asList(6, 1, 2, 7, 9, 3, 4, 5, 10, 8);
    Integer[] a = list.toArray(new Integer[0]);
    System.out.println("before sort:" + Arrays.toString(a));
    QuickSort.quickSort(a);
    System.out.println("after quick sort:" + Arrays.toString(a));

    list = Arrays.asList(7, 6, 9, 3, 1, 5, 2, 4);
    a = list.toArray(new Integer[0]);
    InsertSort.sort(a);
    System.out.println("after insert sort:" + Arrays.toString(a));

    a = list.toArray(new Integer[0]);
    Integer[] b = BubbleSort.antherSort(a);
    System.out.println("after bubble sort:" + Arrays.toString(b));

  }
}
