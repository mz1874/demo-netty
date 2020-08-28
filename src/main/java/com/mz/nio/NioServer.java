package com.mz.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author: candy
 * @date: 2020/8/23
 * @description :
 **/
public class NioServer {
    public static void main(String[] args) throws IOException {

        // 创建一个客户端
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open().bind(new InetSocketAddress(6666));

        Selector selector = Selector.open();

        // 设置非阻塞
        serverSocketChannel.configureBlocking(false);

        // 由于Nio 是 基于事件驱动 selector 监听channal中的事件。 SelectionKey.OP_ACCEPT 索要监听的事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            // 若有链接进行链接 未连接
            if (selector.select(1000) == 0) {
                System.out.println(" 服务器等待了一秒 - 暂时没有连接");
                continue;
            }
            // 有链接  监听某些事件

            // 获取链接上服务端的 所有的sessionKey
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            if (iterator.hasNext()) {
                // 拿到单一的选择Key
                SelectionKey key = iterator.next();

                // 开始监听某些事件  连接 时
                if (key.isAcceptable()) {
                    // 通过serverSocketChannel 创建一个连接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println(" -- 客户端已经创建一个连接");
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }

                if (key.isReadable()) {
                    SocketChannel channel =(SocketChannel) key.channel();

                    ByteBuffer attachment = (ByteBuffer) key.attachment();

                    // 将channel中的数据读取到 ByteBuffer中
                    channel.read(attachment);
                    System.out.println("收到数据" + new String(attachment.array()));
                }

                // 移除 Key
                iterator.remove();
            }

        }


    }
}
