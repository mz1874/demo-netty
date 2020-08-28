package com.mz.nio;

import java.nio.ByteBuffer;

/**
 * @author: candy
 * @date: 2020/8/22
 * @description :
 **/
public class NioByteBufferGetPut {

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        byteBuffer.putInt(1);

        byteBuffer.flip();

        System.out.println(byteBuffer.getInt());
    }
}
