/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agency.exceptionHandler;

import org.springframework.http.HttpStatus;

/**
 *
 * @author kwamaGithub
 */
public interface ErrorCode {
    
    String ERROR_CODE_FOR_UNKNOWN_ERROR = "unknown";

    /**
     * Represents the error code.
     *
     * @return The resource based error code
     */
    String code();

    /**
     * The corresponding HTTP status for the given error code
     *
     * @return Corresponding HTTP status code, e.g. 400 Bad Request for a validation
     * error code
     */
    HttpStatus httpStatus();

    
    /**
     * Default implementation representing the Unknown Error Code. When the
     * {@linkplain ErrorCodes} couldn't find any appropriate
     * {@linkplain ErrorCode} for any given {@linkplain Exception}, it will
     * use this implementation by default.
     */
    enum UnknownErrorCode implements ErrorCode {
        INSTANCE;

        @Override
        public String code() {
            return ERROR_CODE_FOR_UNKNOWN_ERROR;
        }

        @Override
        public HttpStatus httpStatus() {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
