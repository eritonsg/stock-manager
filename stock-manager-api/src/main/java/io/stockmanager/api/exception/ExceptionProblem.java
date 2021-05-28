package io.stockmanager.api.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionProblem {

    private Integer status;
    private OffsetDateTime timeStamp;
    private String message;
    private List<Field> fields = new ArrayList<>();

    @AllArgsConstructor
    @Getter
    public static class Field {

        private String name;
        private String message;

    }

}
