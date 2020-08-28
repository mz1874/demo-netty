package com.mz.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: candy
 * @date: 2020/8/21
 * @description :
 **/
public class ServerBio {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(666);

        System.out.printf("服务器已经启动");

        while (true) {
            // 监听等待
            final Socket accept = serverSocket.accept();
            System.out.println("连接到一个客户端");
            // accept 客户端的连接
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    handler(accept);
                }
            });
        }
    }

    public static void handler(Socket socket) {
        byte [] bytes = new byte[1024];
        try {
            InputStream inputStream = socket.getInputStream();

            while (true) {
                int read = inputStream.read(bytes);
                /*继续读取*/
                if (read != -1) {
                    System.out.println(Thread.currentThread().getName() + "读取的数据 ： \t" +new String(bytes, 0, read));
                }else {
                    System.out.println("读取完毕");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.printf("关闭连接出现了异常");
            }
        }

    }
}
