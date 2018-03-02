package com.bob.source.collection;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangxiang on 18/3/1.
 */
public class KVClass {

    /**
     * 线程不安全的HashMap,在进行put操作时会引起死循环,导致CPU利用率接近100%
     */
    class HMClass {

        final HashMap<String, String> map = new HashMap<>();
        final Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(), "");
                        }
                    }, "ftf" + i).start();
                }
            }
        }, "ftf");

        public void start() {
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * HashTable容器使用synchronized来保证线程安全,但在线程竞争激烈的情况下HashTable的效率非常低下.
     */
    class HTClass {

        // hash table的性能虽然不如concurrent hash map,但它的迭代器是强一致的,而后者是弱一致的
    }

    /**
     * HashTable容器在竞争激烈的并发环境下表现出效率低下的原因是所有访问HashTable的线程都必须竞争同一把锁,ConcurrentHashMap使用多把锁,分别锁容器中一部分数据
     */
    class CHMClass {
        final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

        /**
         * 1, 定位 Segment
         * 2, get
         * 3, put
         * 4, size
         */
    }

}