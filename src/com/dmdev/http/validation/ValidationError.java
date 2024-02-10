package com.dmdev.http.validation;

import lombok.Value;

@Value(staticConstructor = "of")
public class ValidationError {

    String code;

    String message;

}
