package com.mz.aclasser.controller.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceError {
    private String errorMessage;
}
