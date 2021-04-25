package com.code.leet;

/**
 * https://leetcode-cn.com/problems/add-two-numbers
 *
 * @author wangxiang
 * @create 2021/4/9
 */
public class AddTwoNum {

  public static void main(String[] args) {

    ListNode a1 = new ListNode(6);
    ListNode a2 = new ListNode(4);
    ListNode a3 = new ListNode(6);
    a1.next = a2;
    a2.next = a3;

    ListNode b1 = new ListNode(5);
    ListNode b2 = new ListNode(6);
    ListNode b3 = new ListNode(4);
    ListNode b4 = new ListNode(7);
    b1.next = b2;
    b2.next = b3;
    b3.next = b4;
    ListNode resultNode = addTwoNum(a1, b1);
    StringBuilder sb = new StringBuilder(resultNode.val.toString());
    while (resultNode.next != null) {
      sb.insert(0, resultNode.next.val.toString());
      resultNode = resultNode.next;
    }
    System.out.println(sb.toString());
  }

  private static ListNode addTwoNum(ListNode l1, ListNode l2) {

    ListNode head = new ListNode(l1.val + l2.val);
    ListNode cur = head;
    while (l1.next != null || l2.next != null) {
      l1 = l1.next != null ? l1.next : new ListNode(0);
      l2 = l2.next != null ? l2.next : new ListNode(0);
      cur.next = new ListNode(l1.val + l2.val + cur.val / 10);
      cur.val %= 10;
      cur = cur.next;
    }
    if (cur.val >= 10) {
      cur.next = new ListNode(cur.val / 10);
      cur.val %= 10;
    }
    return head;
  }

  public static class ListNode {

    Integer val;
    ListNode next;

    ListNode(Integer x) {
      val = x;
    }
  }
}
