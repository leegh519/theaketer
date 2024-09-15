package com.chbb.theaketing.feature.common.exception;

import lombok.Getter;

@Getter
public class TheaketingException extends RuntimeException {

    private ErrorCode error;

    public TheaketingException(ErrorCode error) {
        super(error.getMessage());
        this.error = error;
    }

}
