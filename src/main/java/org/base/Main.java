package org.base;


/*
===================
You are given a static log file containing billions of entries. Each entry contains a timestamp and the name of an NFT collection. The entries in the log file appear in order of increasing timestamp.
Design a method getCommon(k) to determine the k most common NFTs found in the log file.
```
SolanaMonkeyBusiness 1595268625
DeGods 1595268626
Mindfolk 1595268627
SolanaMonkeyBusiness 1595268628
...

 */

import com.google.common.base.Objects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {


    public static final long topK = 2L;
    public static final String filePath = "./src/test/resources/t1.txt";
    /*
    SolanaMonkeyBusiness 1595268625
    DeGods 1595268626
    Mindfolk 1595268627
    SolanaMonkeyBusiness 1595268628
     */
    public static Map<String, Long> freqMap;
    public static PriorityQueue priorityQueue;

    public static void main(String[] args) {
        System.out.println("hello world...!");

        freqMap = new HashMap<>();

        // O(n) - where n total # of line items
        loadFreqMap(filePath);

        // write the comparator here
        // Om(log(m)) - where m is the # of unique itmes
        priorityQueue = new PriorityQueue(freqMap.size(), new NodeComparator()); // TODO: write comparator
        loadPriorityQueue();

        // klog(m)
        List<String> topKElements = getTopK(topK);
        printTopK(topKElements);

    }

    public static void printTopK(List<String> list) {
        System.err.println("==============================================================");
        System.err.println("The topK elements are: ");
        int count = 0;
        for (String l : list) {
            System.err.println(count + "==> " + l);
            count++;
        }
    }

    public static List<String> getTopK(long n) {
        List<String> topK = new ArrayList<String>();

        // make a copy of the PQ
        PriorityQueue<Node> copyPQ = new PriorityQueue<>(priorityQueue);

        long count = (priorityQueue.size() > n) ? n : priorityQueue.size();

        // make sure to check if k > q.size()
        for(int i = 0; i < count; i++) {
            Node item = copyPQ.poll();
            topK.add(item.getKey());
        }

        return topK;
    }

    public static void loadPriorityQueue() {

        for (Map.Entry<String, Long> entry : freqMap.entrySet()) {

            String key = entry.getKey();
            Long count = entry.getValue();

            Node node = new Node(key, count);

            priorityQueue.add(node);

        }
    }

    public static void loadFreqMap(String filePath) {

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line = null;

            // read next line
            while ((line = reader.readLine()) != null) {
                System.err.println(line);

                String[] tokens = tokenizeLine(line);
                String key = tokens[0];

                if (!freqMap.containsKey(key)) {
                    freqMap.put(key, 1L);
                } else {
                    long count = freqMap.get(key);
                    long newCount = count + 1;
                    freqMap.put(key, newCount);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {

            if (o1.count < o2.count) {
                return 1;
            } else if (o1.count > o2.count) {
                return -1;
            }
            return 0;
        }
    }

    public static class Node {
        private String key;
        private Long count;

        public Node(String key, Long count) {
            this.key = key;this.count = count;

        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node node)) return false;
            return Objects.equal(key, node.key) && Objects.equal(count, node.count);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(key, count);
        }

        public Long getCount() {
            return count;
        }

        public void setCount(Long count) {
            this.count = count;
        }
    }


    private static String[] tokenizeLine(String line) {

        String[] strParts = line.split(" ");
        if (strParts.length != 2) {
            throw new RuntimeException("Invalid line");
        }
        return strParts;
    }

}