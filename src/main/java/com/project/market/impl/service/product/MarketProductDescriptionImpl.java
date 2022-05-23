package com.project.market.impl.service.product;

import com.project.market.constant.Constant;
import com.project.market.dto.res.Response;
import com.project.market.impl.exception.ResponseException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class MarketProductDescriptionImpl {
    private static final Logger logger = Logger.getLogger(MarketProductDescriptionImpl.class);

    public Response insertProductDescription(String jsonRequest){
        try {

        }catch (ResponseException e){
            logger.error(String.format(Constant.THROW_EXCEPTION, e.getMessage()));
        }
        return null;
    }
}
