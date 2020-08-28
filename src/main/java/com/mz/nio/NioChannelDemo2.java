package com.mz.nio;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: candy
 * @date: 2020/8/22
 * @description :  从文件读取字符串
 **/
public class NioChannelDemo2 {

    public static void main(String[] args) {
        try {
            FileInputStream fileOutputStream = new FileInputStream("D:\\demo.txt");

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            FileChannel channel = fileOutputStream.getChannel();

            // 将缓冲区写入通道
            channel.read(byteBuffer);
            int position = byteBuffer.position();
            System.out.println(new java.lang.String(byteBuffer.array(),0,position));
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
