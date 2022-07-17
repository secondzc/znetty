package com.zhangcy.znetty.eventloop;

import com.zhangcy.znetty.channel.Channel;

import java.util.Iterator;
import java.util.concurrent.Executor;

public interface EventLoopGroup extends Executor, Iterable<EventLoop>{

    void register(Channel channel);

    EventLoop next();

    @Override
    Iterator<EventLoop> iterator();
}
