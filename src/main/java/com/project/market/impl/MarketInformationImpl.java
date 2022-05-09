package com.project.market.impl;

import com.project.market.constant.Constant;
import com.project.market.impl.exception.ResponseException;
import com.project.market.impl.validation.ValidationAbstract;
import com.project.market.impl.validation.ValidatorFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MarketInformationImpl {
    private static final Logger logger = Logger.getLogger(MarketInformationImpl.class);

    @Autowired
    private ValidatorFactory validatorFactory;

    public ResponseEntity<String> register(String jsonRequest) throws ResponseException{
        try {
            ValidationAbstract validator = validatorFactory.getValidator(Constant.REGISTER);
            validator.validate(Constant.REGISTER,jsonRequest);

        }catch (ResponseException e){
            logger.error(String.format(Constant.THROW_EXCEPTION,e.getMessage()));
        }
        return null;
    }

}
