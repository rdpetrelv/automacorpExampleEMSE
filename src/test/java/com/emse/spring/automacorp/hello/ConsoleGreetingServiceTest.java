package com.emse.spring.automacorp.hello;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(OutputCaptureExtension.class)
class ConsoleGreetingServiceTest {

    @Test
    public void testGreeting(CapturedOutput output){
        GreetingService greetingService =  new ConsoleGreetingService();
        greetingService.greet("Spring");
        assertThat(output.getAll()).contains("Hello, Spring!");
    }
}