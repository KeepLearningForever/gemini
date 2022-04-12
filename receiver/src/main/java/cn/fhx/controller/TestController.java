package cn.fhx.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: fenghaoxian
 * @date: 2022/4/12 23:20
 * @description:
 */
@RestController
public class TestController {

    @PostMapping(value = "/test/receive")
    public String send(){
        return "hello springboot!";
    }

}
