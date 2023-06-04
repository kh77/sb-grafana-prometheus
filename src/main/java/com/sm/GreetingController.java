package com.sm;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class GreetingController {
    final static Logger logger = LoggerFactory.getLogger(BootApplication.class);

    @Autowired
    private MeterRegistry meterRegistry;

    @Timed
    @GetMapping("/message")
    public ResponseEntity<String> greeting() {
        logger.warn("Just checking");

        meterRegistry.counter("call_greet_total", "status", "called").increment();

        Random random = new Random();
        if (random.nextInt(9) > 5) {
            logger.error("error");
            meterRegistry.counter("call_greet_total", "status", "failed").increment();
            throw new RuntimeException("Number is greater than 5");
        }
        return ResponseEntity.ok().body("Good Morning");
    }


}
