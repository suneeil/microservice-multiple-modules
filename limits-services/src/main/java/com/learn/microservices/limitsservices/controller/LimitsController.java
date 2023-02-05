package com.learn.microservices.limitsservices.controller;

import com.learn.microservices.limitsservices.bean.Limits;
import com.learn.microservices.limitsservices.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @Autowired
    private Configuration configuration;
    @GetMapping("/limits")
    public Limits retriveLimits() {

        return new Limits(configuration.getMinimum(), configuration.getMaximum());
    }
}
