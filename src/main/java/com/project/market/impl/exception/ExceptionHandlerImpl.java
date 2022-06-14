package com.project.market.impl.exception;

import com.project.market.constant.Constant;
import com.project.market.dto.res.Response;


public class ExceptionHandlerImpl {
    // Develop Fix : can't use try catch but use handle case by case in responseCode
    public static ExceptionHandler<Response> getCaseProductMarket() {
        return (response) -> {
            final String responseCode = response.getCode();
            final String responseMessage = response.getMessage();
            switch (responseCode) {
                case Constant.STATUS_CODE_SUCCESS:
                    break;
                case Constant.STATUS_CODE_FAIL:
                    throw new ResponseException(
                            Constant.ERROR_PRODUCT_CHECKDATA_DUPLICATE,
                            Constant.ERROR_PRODUCT_CHECKDATA_DUPLICATE,
                            Constant.ALL_OTHERS_DESC,
                            Constant.ERROR_PRODUCT_CHECKDATA_DUPLICATE
                    );
                default:
                    throw new ResponseException(
                            Constant.ALL_OTHERS_ERROR,
                            Constant.ALL_OTHERS_MESSAGE,
                            Constant.ALL_OTHERS_DESC,
                            String.format(Constant.ALL_OTHERS_MOREINFO, responseCode, responseMessage)
                    );
            }
        };
    }


}
