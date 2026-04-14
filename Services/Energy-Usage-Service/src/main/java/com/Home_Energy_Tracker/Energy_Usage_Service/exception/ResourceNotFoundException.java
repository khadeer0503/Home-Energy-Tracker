package com.Home_Energy_Tracker.Energy_Usage_Service.exception;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class ResourceNotFoundException extends RuntimeException {

    String resourceName;
    String FieldName;
    String FieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.FieldName = fieldName;
        this.FieldValue = fieldValue;
    }
    public ResourceNotFoundException(String s) {
    }
}
