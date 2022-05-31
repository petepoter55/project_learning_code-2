package com.scb.api.ent.customer.services.origination.impl.exception;

import com.scb.api.common.framework.domain.exception.BusinessProcessingException;
import com.scb.api.common.framework.domain.exception.ResourceConflictException;
import com.scb.api.common.framework.domain.exception.ResourceNotFoundException;

@FunctionalInterface
public interface ExceptionHandler<T> {
    public void handler(T response)
            throws ResourceNotFoundException, BusinessProcessingException, ResourceConflictException;
}
