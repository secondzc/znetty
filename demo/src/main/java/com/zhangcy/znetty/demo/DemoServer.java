package com.zhangcy.znetty.demo;



public class DemoServer {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(4);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new NettyServerHandler(), new NettyServerHandler2());
            System.out.println("netty server start...");
            bootstrap.bind(9000);
        } finally {
        }
    }
}
