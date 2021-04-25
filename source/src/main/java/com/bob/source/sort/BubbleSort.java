package com.bob.source.sort;

import java.util.Arrays;

/**
 * 〈〉
 *
 * @author wangxiang
 * @create 2021/4/2
 */
public class BubbleSort {

  public static Integer[] sort(Integer[] sourceArray) {
    // 对 arr 进行拷贝，不改变参数内容
    Integer[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

    for (int i = 1; i < arr.length; i++) {
      // 设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已经完成。
      boolean flag = true;
      for (int j = 0; j < arr.length - i; j++) {
        if (arr[j] > arr[j + 1]) {
          int tmp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = tmp;
          flag = false;
        }
      }
      if (flag) {
        break;
      }
    }

    return arr;
  }

  public static Integer[] antherSort(Integer[] sourceArray) {

    Integer[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

    for (int i = 0; i < arr.length; i++) {
      boolean flag = false;
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[i] > arr[j]) {
          int tmp = arr[i];
          arr[i] = arr[j];
          arr[j] = tmp;
          if (!flag) {
            flag = true;
          }
        }
      }
      if (!flag) {
        break;
      }
    }

    return arr;
  }
}
