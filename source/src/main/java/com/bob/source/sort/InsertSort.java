package com.bob.source.sort;

/**
 * 〈〉
 *
 * @author wangxiang
 * @create 2021/3/22
 */
public class InsertSort {

  private String desc() {
    return "对于少量的元数它是一个有效的算法，其基本思想是将一个记录插入到已经排好序的有序列表中\r\n"
        + "其实现过程使用双层循环，外层循环对除了第一个元素之外的所有元素，内层循环对当前元素前面有序表进行待插入位置查找并进行移动\r\n"
        + "https://www.runoob.com/data-structures/insertion-sort.html";
  }

  /**
   * 插入排序的平均时间复杂度是O(n^2),空间复杂度是O(1),如果本身数组有序那么时间复杂度是O(N)
   */
  public static void sort(Comparable[] arr) {

    int n = arr.length;
    for (int i = 0; i < n; i++) {
      // 寻找元素 arr[i] 合适的插入位置
      for (int j = i; j > 0; j--) {
        if (arr[j].compareTo(arr[j - 1]) < 0) {
          swap(arr, j, j - 1);
        } else {
          break;
        }
      }
    }
  }

  /**
   * 交换数组中i/j两个下标的值
   *
   * @param arr 目标数组
   * @param i 下标
   * @param j 下标
   */
  private static void swap(Object[] arr, int i, int j) {
    Object t = arr[i];
    arr[i] = arr[j];
    arr[j] = t;
  }
}
