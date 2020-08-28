package com.mz.nio;

import java.nio.ByteBuffer;

/**
 * @author: candy
 * @date: 2020/8/22
 * @description :
 **/
public class ReadOnlyBuffer {
    public static void main(String[] args) {

        ByteBuffer byteBuffer = ByteBuffer.allocate(64);

        for (int i = 0; i < byteBuffer.capacity(); i ++) {
            byteBuffer.put((byte) i);
        }


    }
}
