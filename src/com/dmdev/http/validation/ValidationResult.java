package com.dmdev.http.validation;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {

    @Getter
    private final List<ValidationError> errors = new ArrayList<>();

    public void add(ValidationError error) {
        this.errors.add(error);
    }

    public boolean isValid() {
        return errors.isEmpty();
    }


}
