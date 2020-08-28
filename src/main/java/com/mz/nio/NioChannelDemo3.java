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
 * @description :  文件copy
 **/
public class NioChannelDemo3 {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("F:\\5 maven的简单构建，springaop.vep");

        FileChannel inputChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("F:\\2.vep");

        FileChannel outputChannel = fileOutputStream.getChannel();

        /**
         * 从哪个通道开始读取
         */
        outputChannel.transferFrom(inputChannel,0,inputChannel.size());

        inputChannel.close();
        outputChannel.close();

        fileInputStream.close();
        fileOutputStream.close();
    }
}
