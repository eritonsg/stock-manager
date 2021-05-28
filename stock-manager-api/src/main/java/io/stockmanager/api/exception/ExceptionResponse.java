package io.stockmanager.api.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Builder
@Getter
public class ExceptionResponse {

    private Integer status;
    private OffsetDateTime timeStamp;
    private String message;

}
