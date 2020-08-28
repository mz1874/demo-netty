package com.mz.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.io.IOException;

/**
 * @author: candy
 * @date: 2020/8/27
 * @description :
 **/
public class NettyServer {

    public static void main(String[] args)  {

        // 只处理连接
        NioEventLoopGroup boosGroup = null;
        EventLoopGroup workGroup = null;
        try {
            boosGroup = new NioEventLoopGroup();
            // 业务处理交给 WorkGroup
            workGroup = new NioEventLoopGroup();
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boosGroup,workGroup)
            .channel(NioServerSocketChannel.class)
                    .option(
                            ChannelOption.SO_BACKLOG, 128
                    ).childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new NettyServerHandler());
                        }
                    });
            System.out.println("服务器 准备 就绪");
            ChannelFuture bind = serverBootstrap.bind(6688).sync();

            bind.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            boosGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }
}
