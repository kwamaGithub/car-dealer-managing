/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agency.exceptionHandler;

import java.util.HashSet;
import java.util.Objects;
import org.springframework.http.HttpStatus;

/**
 *
 * @author kwamaGithub
 * @Created: 19/03/2022
 */
public class InterneExpection extends RuntimeException implements ErrorCode {

    private final String code;
    private  final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    private final HashSet<String> errorsCodes;


    
    public InterneExpection(String code, HashSet<String> errorsCodes) {
        this.code = code;
        this.errorsCodes = errorsCodes;
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public HashSet<String> getErrorsCodes() {
        return errorsCodes;
    }

    public static String getErrorCodeForUnknownError() {
        return ERROR_CODE_FOR_UNKNOWN_ERROR;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.code);
        hash = 29 * hash + Objects.hashCode(this.httpStatus);
        hash = 29 * hash + Objects.hashCode(this.errorsCodes);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final InterneExpection other = (InterneExpection) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (this.httpStatus != other.httpStatus) {
            return false;
        }
        return Objects.equals(this.errorsCodes, other.errorsCodes);
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public HttpStatus httpStatus() {
        return httpStatus;
    }

}
