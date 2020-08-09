package io.github.chester2.tfsalimits.exceptions;

import io.github.chester2.tfsalimits.model.Limit;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PutRequestYearMismatchException extends RuntimeException {
    public PutRequestYearMismatchException(int year, Limit limit) {
        super("Year mismatch between request URI '" + year + "' and body '" + limit.getYear() + "'");
    }
}
