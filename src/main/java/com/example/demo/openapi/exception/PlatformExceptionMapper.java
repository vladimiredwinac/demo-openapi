package com.example.demo.openapi.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Exception mapper to object with error
 *
 * @author Vladimir Edwin Alaro
 * @version 1.0
 * @since 2020-12-04
 */
@Provider
public class PlatformExceptionMapper implements ExceptionMapper<PlatformException> {

    @Override
    public Response toResponse(PlatformException platformException) {
        return platformException.response;
    }
}
