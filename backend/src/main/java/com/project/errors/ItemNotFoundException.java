package com.project.errors;

import org.springframework.http.HttpStatus;

public class ItemNotFoundException  extends AppException {

    public ItemNotFoundException() {
        super(HttpStatus.NOT_FOUND, AppExceptionStrings.NOT_FOUND);
    }

    public ItemNotFoundException(Object... params) {
        super(HttpStatus.NOT_FOUND, AppExceptionStrings.NOT_FOUND_BY_ID, params);
    }

    public ItemNotFoundException(String msg) {
        super(HttpStatus.NOT_FOUND, msg);
    }
}
