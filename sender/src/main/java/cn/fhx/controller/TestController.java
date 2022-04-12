package cn.fhx.controller;

import cn.fhx.sender.GeminiSender;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: fenghaoxian
 * @date: 2022/4/12 23:20
 * @description:
 */
@RestController
public class TestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/test/send")
    public String send(){

        List<ServiceInstance> receiver1 = discoveryClient.getInstances("receiver");
        System.out.println(receiver1.get(0).getPort());

        GeminiSender geminiSender = new GeminiSender();
        Channel receiver = geminiSender.getChannel("receiver");
        return "hello springboot!";
    }

}
