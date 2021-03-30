package com.bob.source.sort;

import java.util.Arrays;

/**
 * 〈〉
 *
 * @author wangxiang
 * @create 2021/3/22
 */
public class QuickSort {

  private String desc() {
    return "快排是不稳定的算法，平均时间复杂度是O(n*logn),最差是O(n^2)，其需要一个栈空间来实现递归，空间复杂度也是O(logn)";
  }

  /**
   * 通过一次排序将数据分割成两部分，其中一部分数据比另外一部分数据都小，然后递归对两部分数据进行快排。优化的时候，可以取 pivot 为中间值，而不是第一个
   *
   * @param arr 待排数组
   * @param start 开始位置
   * @param end 结束位置
   */
  public static void quickSort(Integer[] arr, int start, int end) {
    if (start < end) {
      // 选择一个基准值
      int pivot = arr[start];
      int i = start, j = end;
      while (i < j) {
        // 从右向左移动j，直到找到第一个小于pivot的数
        while (i < j && arr[j] >= pivot) {
          j--;
        }
        if (i < j) {
          arr[i++] = arr[j];
        }

        // 从左向右移动i，直到找到第一个大于等于pivot的数
        while (i < j && arr[i] < pivot) {
          i++;
        }
        if (i < j) {
          arr[j--] = arr[i];
        }
      }
      // 直到i == j，遍历结束，将pivot赋给arr[i]
      arr[i] = pivot;
      // 一轮结束
      System.out.println("temp sort:" + Arrays.toString(arr));

      // 递归调用
      quickSort(arr, start, i - 1);
      quickSort(arr, i + 1, end);
    }
  }
}
