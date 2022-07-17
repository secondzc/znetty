package com.zhangcy.znetty.eventloop;

public interface EventLoop extends EventLoopGroup {
    EventLoopGroup parent();
}
