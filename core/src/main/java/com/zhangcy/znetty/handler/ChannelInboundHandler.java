package com.zhangcy.znetty.handler;

public interface ChannelInboundHandler extends ChannelHandler{

    void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception;

    void channelReadComplete(ChannelHandlerContext ctx) throws Exception;
}
