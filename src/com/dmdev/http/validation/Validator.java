package com.dmdev.http.validation;

public interface Validator<T> {

    ValidationResult isValid(T object);
}
