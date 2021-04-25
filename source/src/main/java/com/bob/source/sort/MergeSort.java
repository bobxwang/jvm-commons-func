package com.bob.source.sort;

import java.util.Arrays;

/**
 * 〈〉
 *
 * @author wangxiang
 * @create 2021/4/1
 */
public class MergeSort {

  private String desc() {
    return "归并排序是一种有效稳定的排序算法，采用分治法的一个典型应用，将已有序的子序列合并进而得到完全有序的序列\r\n"
        + "当有n个记录时，需进行logn轮归并排序，每一轮归并，其比较次数不超过n，元素移动次数都是n，\r\n"
        + "因此，归并排序的时间复杂度为 O(nlogn)。归并排序时需要和待排序记录个数相等的存储空间，所以空间复杂度为 O(n)。\r\n"
        + "适用于数据量大，并且对稳定性有要求的场景。\r\n"
        + "https://www.runoob.com/data-structures/merge-sort.html";
  }

  public static void sort(Comparable[] arr) {
    int n = arr.length;
    sort(arr, 0, n - 1);
  }

  /**
   * 递归使用归并排序,对arr[l...r]的范围进行排序
   *
   * @param arr 待排数组
   * @param l 开始点
   * @param r 结束点
   */
  private static void sort(Comparable[] arr, int l, int r) {
    if (l >= r) {
      return;
    }
    int mid = (l + r) / 2;
    sort(arr, l, mid);
    sort(arr, mid + 1, r);
    if (arr[mid].compareTo(arr[mid + 1]) > 0) {
      merge(arr, l, mid, r);
    }
  }

  /**
   * 将arr[l...mid]和arr[mid+1...r]两部分进行归并
   * @param arr
   * @param l
   * @param mid
   * @param r
   */
  private static void merge(Comparable[] arr, int l, int mid, int r) {

    Comparable[] aux = Arrays.copyOfRange(arr, l, r + 1);

    // 初始化，i指向左半部分的起始索引位置l；j指向右半部分起始索引位置mid+1
    int i = l, j = mid + 1;
    for (int k = l; k <= r; k++) {

      // 如果左半部分元素已经全部处理完毕
      if (i > mid) {
        arr[k] = aux[j - l];
        j++;

      } // 如果右半部分元素已经全部处理完毕
      else if (j > r) {
        arr[k] = aux[i - l];
        i++;
      } // 左半部分所指元素 < 右半部分所指元素
      else if (aux[i - l].compareTo(aux[j - l]) < 0) {
        arr[k] = aux[i - l];
        i++;
      } // 左半部分所指元素 >= 右半部分所指元素
      else {
        arr[k] = aux[j - l];
        j++;
      }
    }
  }
}
