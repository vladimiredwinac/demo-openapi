package com.example.demo.openapi.exception;

import javax.ws.rs.core.Response;

/**
 * This class carries the code and message error in the response
 *
 * @author Vladimir Edwin Alaro
 * @version 1.0
 * @since 2020-12-04
 */
public class PlatformException extends RuntimeException {
    Response response;

    /**
     * With this constructor the default error code is 400
     *
     * @param message description or error content
     */
    public PlatformException(String message) {
        this(Response.Status.BAD_REQUEST.getStatusCode(), message);
    }

    /**
     * With this constructor you can customize the error code
     *
     * @param code    http error code
     * @param message description or error content
     */
    public PlatformException(int code, String message) {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.code = code;
        errorMessage.message = message;
        this.response = Response.status(code).entity(errorMessage).build();
    }
}
