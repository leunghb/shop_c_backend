package shop.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.demo.DemoApplication;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello shop";
    }
}
