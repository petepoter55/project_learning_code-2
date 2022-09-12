package com.project.market.impl.service.product;

import com.project.market.constant.Constant;
import com.project.market.dto.res.Response;
import com.project.market.entity.product.ProductImageMarket;
import com.project.market.entity.product.ProductMarket;
import com.project.market.repository.product.ProductImageMarketRepository;
import com.project.market.repository.product.ProductMarketRepository;
import com.project.market.impl.exception.ResponseException;
import com.project.market.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Service
public class MarketProductImageImpl {
    private static final Logger logger = Logger.getLogger(MarketProductsImpl.class);

    @Autowired
    private ProductMarketRepository marketRepository;
    @Autowired
    private ProductImageMarketRepository productImageMarketRepository;

    public Response insertImageByProductCode(MultipartFile files, String productCode) {
        List<ProductMarket> productMarkets = marketRepository.findByProductCode(productCode);
        ProductImageMarket productImageMarket = new ProductImageMarket();
        try {
            if (productMarkets.size() > 0) {
                productImageMarket.setProductCode(productMarkets.get(0).getProductCode());
                productImageMarket.setImage(files.getBytes());
                productImageMarket.setActive("Y");
                productImageMarket.setCreateDateTime(new DateUtil().getFormatsDateMilli());

                productImageMarketRepository.save(productImageMarket);
            }
        } catch (ResponseException e) {
            logger.error(String.format(Constant.THROW_EXCEPTION, e.getMessage()));
            return Response.fail(e.getExceptionCode(), e.getMessage(), null);
        } catch (IOException | ParseException ex) {
            return Response.fail(String.valueOf(ex.hashCode()), ex.getMessage(), null);
        }

        return Response.success(Constant.STATUS_CODE_SUCCESS, Constant.SUCCESS, "");
    }
}
