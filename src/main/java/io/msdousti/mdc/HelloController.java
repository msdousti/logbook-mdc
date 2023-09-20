package io.msdousti.mdc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    String sayHello() {
        MDC.put("traceId", "8634834348347834661212");

        logger.info("Hello from Spring Boot!");

        return "Hello";
    }

}
