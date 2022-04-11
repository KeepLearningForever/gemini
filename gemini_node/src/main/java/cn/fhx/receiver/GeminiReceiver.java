package cn.fhx.receiver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fenghaoxian
 * @date 2022/4/11 14:50
 * @description
 */
public class GeminiReceiver {

    private ServerBootstrap serverBootstrap;

    private final NioEventLoopGroup worker = new NioEventLoopGroup(2);

    private final LoggingHandler loggingHandler = new LoggingHandler();

    private void initServerBootStrap() {
        serverBootstrap = new ServerBootstrap()
                .group(worker)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_BACKLOG, 1)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        channel.pipeline()
                                .addLast(loggingHandler);
                    }
                });
    }

    public Channel getChannel(int port) {
        if (serverBootstrap == null){
            initServerBootStrap();
        }
        return serverBootstrap.bind(port).syncUninterruptibly().channel();
    }

}
