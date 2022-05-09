package com.project.market.impl.validation;

import com.project.market.impl.exception.ResponseException;

public abstract class ValidationAbstract {
    public abstract void validate(String requestName, String jsonRequest) throws ResponseException;
}
