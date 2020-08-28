package com.mz.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author: candy
 * @date: 2020/8/29
 * @description :
 **/
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    public NettyServerHandler() {
        super();
    }

    /**
     * 注册时
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("注册事件");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("取消注册事件");
        System.out.println(ctx.channel().remoteAddress() + "下线了");
        super.channelUnregistered(ctx);
    }

    /**
     * 数据激活时
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("激活");
        super.channelActive(ctx);
    }


    /**
     * 失效时
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("失效");
        super.channelInactive(ctx);
    }

    /**
     * 读取时
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("发生了读取事件");

        try {
            ByteBuf byteBuf = (ByteBuf) msg;

            System.out.println("客户端地址是 : \t" + ctx.channel().remoteAddress());
            System.out.println("读取的数据是" + byteBuf.toString(CharsetUtil.UTF_8));
        } catch (Exception e) {
            System.out.println("我异常");
        }
        super.channelRead(ctx, msg);
    }

    /**
     * 数据读取完毕
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("此时数据读取完毕");
        super.channelReadComplete(ctx);
    }

    /**
     * 用户事件出发时
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("用户事件触发");
        super.userEventTriggered(ctx, evt);
    }


    /**
     * 通道可写性改变时
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
