package com.project.market.controller;

import com.project.market.entity.information.InformationMarket;
import com.project.market.entity.repository.InformationMarketRepository;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TestController {
    private static final Logger logger = Logger.getLogger(TestController.class);

    @Autowired
    private InformationMarketRepository informationMarketRepository;

    @GetMapping(value = "/get")
    public List<InformationMarket> test()
    {
        logger.info("Hello Log");
        return this.informationMarketRepository.findAll();}
}
