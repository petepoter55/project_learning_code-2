package com.project.market.impl.validation.validate;

import com.project.market.constant.Constant;
import com.project.market.dto.req.register.RegisterDtoRequest;
import com.project.market.impl.exception.ResponseException;
import org.springframework.stereotype.Component;

@Component
public class ValidateRequestRegister {

    public void validateRequest(RegisterDtoRequest registerDtoRequest){
        if (!registerDtoRequest.getPassword().equals(registerDtoRequest.getConfirmPassword()))
            throw new ResponseException(Constant.STATUS_CODE_ERROR, Constant.ERROR_REGISTER_PASSWORD_CONFIRM_INVALID);

        if (!registerDtoRequest.getEmail().equals(registerDtoRequest.getConfirmEmail()))
            throw new ResponseException(Constant.STATUS_CODE_ERROR, Constant.ERROR_REGISTER_EMAIL_CONFIRM_INVALID);
    }
}
