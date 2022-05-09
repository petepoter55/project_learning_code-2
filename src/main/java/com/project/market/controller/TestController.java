package com.project.market.controller;

import org.apache.log4j.Logger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {
    private static final Logger logger = Logger.getLogger(TestController.class);
    @GetMapping(value = "/get")
    public String test()
    {
        logger.info("Hello Log");
        return "test";}
}
