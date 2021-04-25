package com.bob.source.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringJoiner;

/**
 * 〈〉
 *
 * @author wangxiang
 * @create 2020/7/13
 */
public class TreeClass {

  public static void main(String[] args) {

    TreeClass treeClass = new TreeClass();
    BinaryTree root = treeClass.buildBinaryTree(5);

    treeClass.iterateBinaryTree(root);
  }

  private void iterateBinaryTree(BinaryTree tree) {

    System.out.println(tree);
    Queue<BinaryTree> queue = new LinkedList<>();
    BinaryTree currentNode = tree;
    queue.offer(currentNode);
    while (!queue.isEmpty()) {
      BinaryTree temp = queue.poll();
      System.out.println(temp.getValue());
      if(null != temp.getLeft()) {
        queue.offer(temp.getLeft());
      }
      if(null != temp.getRight()) {
        queue.offer(temp.getRight());
      }
    }
  }

  private BinaryTree buildBinaryTree(int length) {

    List<BinaryTree> treeList = new ArrayList<>();
    for (int i = 0; i < length; i++) {
      treeList.add(new BinaryTree(i));
    }
    BinaryTree root = treeList.get(0);
    for (int index = 0; index < treeList.size() / 2; index++) {
      if (2 * index + 2 < treeList.size()) {
        treeList.get(index).setRight(treeList.get(2 * index + 2));
      }
      treeList.get(index).setLeft(treeList.get(2 * index + 1));
    }
    return root;
  }

  /**
   * 二叉树
   */
  static class BinaryTree {

    private int value;
    private BinaryTree left;
    private BinaryTree right;

    public BinaryTree() {
    }

    public BinaryTree(int value) {
      this.value = value;
    }

    public int getValue() {
      return value;
    }

    public void setValue(int value) {
      this.value = value;
    }

    public BinaryTree getLeft() {
      return left;
    }

    public void setLeft(BinaryTree left) {
      this.left = left;
    }

    public BinaryTree getRight() {
      return right;
    }

    public void setRight(BinaryTree right) {
      this.right = right;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", BinaryTree.class.getSimpleName() + "[", "]")
          .add("value=" + value)
          .add("left=" + left)
          .add("right=" + right)
          .toString();
    }
  }
}
