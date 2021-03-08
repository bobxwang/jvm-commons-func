package com.bob.source.collection;

public class ListClass {

  interface Iterable<T> {

  }

  interface Collection<E> extends Iterable<E> {

  }

  interface Queue<E> extends Collection<E> {

    /**
     * 队列满时会返回 false
     */
    boolean offer(E e);

    /**
     * 队列满时会抛出异常，一般实现中会转而调用 offer
     */
    boolean add(E e);

    /**
     * 队列空时抛出异常，会将元素从队列中移除
     */
    E remove();

    /**
     * 队列空时返回 null，会将元素从队列中移除
     */
    E poll();

    /**
     * 队列空时抛出异常，元素不会从队列中移除
     */
    E element();

    /**
     * 队列空时返回 null，元素不会从队列中移除
     */
    E peek();
  }

  /**
   * 顺序容器，允许放入 null 元素
   */
  class ArrayList {

    transient Object[] elementData;

    // 自动扩容，当向数组添加元素时，都要去检查添加后元素的个数是否会超出当前数组长度，如果超出将会进行扩容以满足数据的需求，扩容通过一个公开的方法实现
    void ensureCapacity(int minCapacity) {
    }

    int Size;

    // Fail-Fast，通过记录 modCount 参数实现，在面对并发的修改时，迭代器很快就会完全失败，而不是冒着在将来某个不确定时间发生任意不确定行为的风险
  }

  class LinkedList {
    // 所有跟下标相关的操作都是线性时间
  }

  /**
   * 基于数组实现的有界队列，一旦创建后容量不可更改
   */
  class ArrayBlockingQueue {

  }

  /**
   * 基于链表的有界阻塞队列，添加跟获取是两个不同的锁，队列默认长度是 Integer.MAX_VALUE
   */
  class LinkedBlockingQueue {

  }

  /**
   * 基于数组，支持优先级使用二叉堆数据结构实现的无界阻塞队列
   */
  class PriorityBlockingQueue {

  }

  /**
   * 支持延时获取元素的无界阻塞队列
   */
  class DelayQueue {

  }

  /**
   * 容量为 0 ，一个添加操作后必须等待一个获取操作才可以继续添加，CPU 自旋等待消费者取走元素
   */
  class SynchronousQueue {

  }

  /**
   * 双向链表组成的双向阻队列，无界
   */
  class LinkedBlockingDeque {

  }

  /**
   * 一个由链表组成的无界阻塞队列
   */
  class LinkedTransferQueue {

    /**
     * 如果当前有消费线程正在获取元素则把该元素直接传递给消费线程否则加入到队列中直到该元素被消费才返回
     */
    void transfer() {

    }
  }
}