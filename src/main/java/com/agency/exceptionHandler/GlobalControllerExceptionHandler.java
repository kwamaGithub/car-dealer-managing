/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agency.exceptionHandler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author kwamaGithub
 * @Created: 19/03/2022
 */
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    private final MessageSource apiErrorMessageSource;

    GlobalControllerExceptionHandler(MessageSource apiErrorMessageSource) {
        Objects.requireNonNull(apiErrorMessageSource);
        this.apiErrorMessageSource = apiErrorMessageSource;
    }

    /**
     * INTERNAL SERVER ERROR HANDLER
     *
     * @param exception
     * @param locale
     * @return
     */
    @ExceptionHandler(InterneExpection.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseEntity<ErrorResponse> handleInternalServerExceptions(InterneExpection exception,
            Locale locale) {

        ErrorResponse errorResponse = ErrorResponse.of(exception.httpStatus(), toErrorPhrase(exception.getCode(), locale),
                toApiErrors(exception.getErrorsCodes(), locale));

        return ResponseEntity.status(exception.httpStatus()).body(errorResponse);
    }

    private String toErrorPhrase(String errorCode, Locale locale) {
        String message;
        try {
            message = apiErrorMessageSource.getMessage(errorCode,
                    new Object[]{},
                    locale);
        } catch (NoSuchMessageException e) {
            message = errorCode;
        }
        return message;
    }

    /**
     * Convert the passed {@code errorCode} to an instance of
     * {@linkplain ErrorResponse} using the given {@code locale}
     */
    private List<ErrorResponse.ApiError> toApiErrors(HashSet<String> errorCode, Locale locale) {
        if (errorCode == null || errorCode.isEmpty()) {
            return new ArrayList<>();
        }
        String message;
        List<ErrorResponse.ApiError> list = new ArrayList<>();
        for (String er : errorCode) {
            try {
                message = apiErrorMessageSource.getMessage(er,
                        new Object[]{},
                        locale);
            } catch (NoSuchMessageException e) {
                message = errorCode.toString();
            }
            list.add(new ErrorResponse.ApiError(er, message));
        }
        return list;
    }
}
