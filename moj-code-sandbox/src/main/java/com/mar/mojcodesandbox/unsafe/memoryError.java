package com.mar.mojcodesandbox.unsafe;

import java.util.ArrayList;
import java.util.List;

/**
 * @description；无限占用空间（浪费系统内存)
 * @author:mar1
 * @data:2024/09/03
 **/
public class memoryError {
    public static void main(String[] args) throws InterruptedException {
        List<byte[]> bytes = new ArrayList<>();
        while (true){
            bytes.add(new byte[1024*1024]);
        }
    }

}
