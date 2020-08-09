package io.github.chester2.tfsalimits.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LimitNotFoundException extends RuntimeException {
    private static String createErrorMessage(int year) {
        return "Limit for year '" + year + "' not found";
    }

    public LimitNotFoundException(int year) {
        super(LimitNotFoundException.createErrorMessage(year));
    }

    public LimitNotFoundException(int year, Throwable cause) {
        super(LimitNotFoundException.createErrorMessage(year), cause);
    }
}
