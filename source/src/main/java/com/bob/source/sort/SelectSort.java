package com.bob.source.sort;

import java.util.Arrays;

/**
 * 〈〉
 *
 * @author wangxiang
 * @create 2021/4/6
 */
public class SelectSort {

  private String desc() {
    return "选择排序是一种简单直观的排序算法，无论什么数据进去都是 O(n²) 的时间复杂度。\r\n"
        + "所以用到它的时候，数据规模越小越好。唯一的好处可能就是不占用额外的内存空间了吧。\r\n"
        + "适合数据量小的数组，唯一好处是不占用额外空间。\r\n"
        + "https://www.runoob.com/data-structures/selection-sort.html";
  }

  /**
   * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置。 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。 重复第二步，直到所有元素均排序完毕。
   *
   * @param array 待排数组
   */
  public Integer[] sort(Integer[] array) {

    Integer[] arr = Arrays.copyOf(array, array.length);
    for (int i = 0; i < arr.length; i++) {
      int min = i;
      // 每轮需要比较的次数 N-i
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[j] < arr[min]) {
          // 记录目前能找到的最小值元素的下标
          min = j;
        }
      }

      // 将找到的最小值和i位置所在的值进行交换
      if (i != min) {
        int tmp = arr[i];
        arr[i] = arr[min];
        arr[min] = tmp;
      }
    }
    return arr;
  }

}
