package com.project.market.controller;

import com.project.market.impl.MarketInformationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/information")
public class MarketInformationController {

    @Autowired
    private MarketInformationImpl marketInformationImpl;

//    @Consumes ใช้สำหรับ request และ @Produces ใช้สำหรับ response
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(
            @RequestBody String jsonString
    ){
        return this.marketInformationImpl.register(jsonString);
    }
}
