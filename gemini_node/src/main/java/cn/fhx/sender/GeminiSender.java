package cn.fhx.sender;

import cn.fhx.common.model.Future;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: fenghaoxian
 * @date: 2022/4/10 21:36
 * @description:
 */
public class GeminiSender {

    private Bootstrap bootstrap;

    private final NioEventLoopGroup worker = new NioEventLoopGroup(2);

    Map<String,Channel> receiverChannelMap = new HashMap<>();

    private final LoggingHandler loggingHandler = new LoggingHandler();

    @Autowired
    private DiscoveryClient discoveryClient;

    public void send(Channel channel) {

    }

    public Channel getChannel(String serviceName) {
        if (bootstrap == null){
            initBootStrap();
        }

        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        ServiceInstance instance = instances.get(0);
        // TODO: 2022/4/11 ip地址和端口号应从eureka根据服务名获得
        String ipAddress = instance.getHost();
        int port = instance.getPort();
        Channel channel = bootstrap.bind(ipAddress, port).syncUninterruptibly().channel();
        receiverChannelMap.put(serviceName,channel);
        return channel;
    }

    public static void main(String[] args) {
        GeminiSender geminiSender = new GeminiSender();
        geminiSender.getChannel("sender");
    }

    private void initBootStrap() {
        bootstrap = new Bootstrap()
                .group(worker)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        channel.pipeline()
                                .addLast(loggingHandler);
                    }
                });
    }
}
