package cn.fhx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: fenghaoxian
 * @date: 2022/4/12 23:20
 * @description:
 */
@SpringBootApplication
public class ReceiverApplication {
    public static void main(String[] args) {
        // 只需要run一下，就能发布一个springboot应用
        // 相当于之前将web工程发布到tomcat服务器，只是在springboot中集成了tomcat插件
        SpringApplication.run(ReceiverApplication.class,args);
    }
}