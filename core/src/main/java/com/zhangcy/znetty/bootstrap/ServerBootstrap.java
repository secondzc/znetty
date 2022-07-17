package com.zhangcy.znetty.bootstrap;

import com.zhangcy.znetty.channel.Channel;
import com.zhangcy.znetty.channel.NioServerSocketChannel;
import com.zhangcy.znetty.eventloop.EventLoopGroup;
import com.zhangcy.znetty.handler.ChannelHandler;
import com.zhangcy.znetty.handler.ChannelHandlerContext;
import com.zhangcy.znetty.handler.ChannelInboundHandler;
import com.zhangcy.znetty.handler.ChannelPipeline;

public class ServerBootstrap extends AbstractBootstrap<ServerBootstrap, NioServerSocketChannel> {

    private volatile EventLoopGroup childGroup;

    /**
     * 这里简化处理
     */
    private volatile ChannelHandler[] childHandlers;

    public ServerBootstrap() { }

    public ServerBootstrap group(EventLoopGroup parentGroup, EventLoopGroup childGroup) {
        super.group(parentGroup);
        this.childGroup = childGroup;
        return this;
    }

    public ServerBootstrap childHandler(ChannelHandler... childHandlers) {
        this.childHandlers = childHandlers;
        return this;
    }

    @Override
    void init(Channel channel) {
        ChannelPipeline p = channel.pipeline();
        // 这里给ServerChannel添加管道处理器，简化了代码
        p.addLast(new ServerBootstrapAcceptor(childGroup, childHandlers));
    }

    private static class ServerBootstrapAcceptor implements ChannelInboundHandler {

        private final EventLoopGroup childGroup;
        private final ChannelHandler[] childHandlers;

        private ServerBootstrapAcceptor(EventLoopGroup childGroup, ChannelHandler[] childHandlers) {
            this.childGroup = childGroup;
            this.childHandlers = childHandlers;
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            final Channel child = (Channel) msg;
            for (ChannelHandler childHandler : childHandlers) {
                child.pipeline().addLast(childHandler);
            }
            childGroup.register(child);
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            // 略
        }
    }
}
