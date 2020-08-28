package com.mz.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author: candy
 * @date: 2020/8/23
 * @description :
 **/
public class NioClient {
    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel = SocketChannel.open();

        InetSocketAddress inetSocketAddress = new InetSocketAddress(6666);
        if (!socketChannel.connect(inetSocketAddress)) {
            while (! socketChannel.finishConnect()) {
                System.out.println("可以做其他事情");
            }
        }

        String str = "hello word";

        ByteBuffer wrap = ByteBuffer.wrap(str.getBytes());

        socketChannel.write(wrap);

        System.in.read();
    }
}
