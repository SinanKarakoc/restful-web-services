package com.rest.webservices.restfulwebservices.helloWorld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
    @GetMapping(path = "/hello")
    public String helloWorld(){
        return "heyy";
    }

    @GetMapping(path = "/hello-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("Hello-Bean");
    }
    @GetMapping(path = "hello/path/{name}")
    public HelloWorldBean helloWorldPath(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello world,%s",name));
    }
}
