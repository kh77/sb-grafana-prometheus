package com.sm;


import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationRunner {

    private AtomicInteger appInfoGaugeValue = new AtomicInteger(1);

    @Autowired
    private MeterRegistry registry;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        registry.gauge("app.info", Tags.of("version", "1.0.0", "java.version", "8"), appInfoGaugeValue);
    }
}

