package com.orderservice.payloads.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GenericResponse {
    private String message;
    private boolean status;
}
