package com.zhangcy.znetty.eventloop;

import com.zhangcy.znetty.channel.Channel;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.Executor;

public abstract class SingleThreadEventLoop extends SingleThreadEventExecutor implements EventLoop {

    private final Collection<EventLoop> selfCollection = Collections.<EventLoop>singleton(this);

    private final EventLoopGroup parent;

    public SingleThreadEventLoop(EventLoopGroup parent, Executor executor) {
        super(executor);
        this.parent = parent;
    }

    @Override
    public EventLoopGroup parent() {
        return parent;
    }


    @Override
    public void register(Channel channel) {
        // netty源码  promise.channel().unsafe().register(this, promise); 简化不区分unsafe，如下
        channel.register(this);
    }

    @Override
    public EventLoop next() {
        return this;
    }

    @Override
    public Iterator<EventLoop> iterator() {
        return selfCollection.iterator();
    }

}
