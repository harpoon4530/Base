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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class NewMain {

    public static void main(String[] args) {
        System.out.println("hello world...!");

    }


}