package com.project.market.impl.exception;

import org.apache.velocity.exception.ResourceNotFoundException;

@FunctionalInterface
public interface ExceptionHandler<T> {
    public void handler(T response)
            throws ResourceNotFoundException, ResponseException;
}
