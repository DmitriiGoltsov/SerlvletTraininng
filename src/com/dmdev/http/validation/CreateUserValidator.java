package com.dmdev.http.validation;

import com.dmdev.http.dto.CreateUserDto;
import com.dmdev.http.entity.Sex;
import com.dmdev.http.util.LocalDateFormatter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserValidator implements Validator<CreateUserDto> {

    private static final CreateUserValidator INSTANCE = new CreateUserValidator();

    @Override
    public ValidationResult isValid(CreateUserDto object) {
        ValidationResult validationResult = new ValidationResult();

        if (Sex.find(object.getSex()).isEmpty()) {
            validationResult.add(ValidationError.of("invalid.gender","Sex is invalid"));
        } else if (!LocalDateFormatter.isValid(object.getBirthday())) {
            validationResult.add(ValidationError.of(
                    "invalid birthday date",
                    "The birthday date is not valid"));
        }

        return validationResult;
    }

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}
