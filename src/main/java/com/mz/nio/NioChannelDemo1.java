package com.mz.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: candy
 * @date: 2020/8/22
 * @description :  字符串写入文件
 **/
public class NioChannelDemo1 {

    public static void main(String[] args) {
        String str = "hello Netty";
            // UTf - 8 中文 占三个字节
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\demo.txt");

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            FileChannel channel = fileOutputStream.getChannel();

            // 顺序将数据写入缓冲区
            byteBuffer.put(str.getBytes());
            /**
             * 调整指针
             * limit = position
             *
             */
            byteBuffer.flip();
            // 将缓冲区写入通道
            channel.write(byteBuffer);
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
