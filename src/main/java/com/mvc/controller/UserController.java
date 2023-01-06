package com.mvc.controller;

import com.mvc.entity.User;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/name")
    @ResponseBody
    public User getUser(String name) {
        User user = new User();
        user.setAge(10);
        user.setName(name);
        return user;
    }

    @RequestMapping(value = "/exceptionTest")
    public String exceptionTest(String param) {
        param.equalsIgnoreCase("com.jack.controller.xx");
        return "Ok";
    }

    @RequestMapping("/invoke/{id}")
    public @ResponseBody String invoke(@PathVariable("id") String param) {
        final User user = restTemplate.getForObject("http://localhost:8090/user/name", User.class, new Object[]{param});
        System.out.println(user);
        return "Ok";
    }

    @RequestMapping("/json")
    public Map<String,String> json() {
        Map<String,String> map = new HashMap<>();
        map.put("key","value");
        return map;

    }

    @GetMapping
    public Map<String,String> json2() {
        Map<String,String> map = new HashMap<>();
        map.put("name","ta");
        return map;

    }
}
