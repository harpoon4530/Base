package org.base.scratch;

import java.util.HashMap;
import java.util.Map;

public class LRUClaude {

    public static class LRUCache<K, V> {
        private class Node {
            K key;
            V value;
            Node prev;
            Node next;

            Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        private Map<K, Node> cache;
        private int capacity;
        private Node head;
        private Node tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.cache = new HashMap<>();
            this.head = new Node(null, null);
            this.tail = new Node(null, null);
            head.next = tail;
            tail.prev = head;
        }

        public V get(K key) {
            Node node = cache.get(key);
            if (node == null) {
                return null;
            }
            moveToHead(node);
            return node.value;
        }

        public void put(K key, V value) {
            Node node = cache.get(key);
            if (node == null) {
                node = new Node(key, value);
                cache.put(key, node);
                addNode(node);
                if (cache.size() > capacity) {
                    Node tail = removeTail();
                    cache.remove(tail.key);
                }
            } else {
                node.value = value;
                moveToHead(node);
            }
        }

        private void addNode(Node node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }

        private void removeNode(Node node) {
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;
        }

        private void moveToHead(Node node) {
            removeNode(node);
            addNode(node);
        }

        private Node removeTail() {
            Node res = tail.prev;
            removeNode(res);
            return res;
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello world...!");

        LRUCache<Integer, String> cache = new LRUCache<>(2);
        cache.put(1, "one");
        cache.put(2, "two");
        System.out.println(cache.get(1)); // Outputs: one
        cache.put(3, "three"); // This will evict key 2
        System.out.println(cache.get(2)); //
        System.out.println(cache.get(3));
    }

}
