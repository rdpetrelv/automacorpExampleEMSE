package com.emse.spring.automacorp.api;

import jakarta.transaction.Transactional;
import org.apache.logging.log4j.message.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
@Transactional
public class HelloControler {
    @GetMapping("/{name}")
    public Message welcome(@PathVariable String name){
        return new Message("Hello "+name);
    }

    public record Message(String message){

    }

}
