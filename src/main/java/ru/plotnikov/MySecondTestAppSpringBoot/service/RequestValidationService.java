package ru.plotnikov.MySecondTestAppSpringBoot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.plotnikov.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.plotnikov.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.plotnikov.MySecondTestAppSpringBoot.model.Request;

import java.util.Objects;

@Service
public class RequestValidationService implements ValidationService {

    @Override
    public void isValid(BindingResult bindingResult) throws ValidationFailedException {
        if (bindingResult.hasErrors()) {
            throw new ValidationFailedException(Objects.requireNonNull(bindingResult.getFieldError()).toString());
        }
    }

    @Override
    public void validate123(Request request) throws UnsupportedCodeException {
        if ("123".equals(request.getUid())) {
            throw new UnsupportedCodeException("uid 123 не поддерживается");
        }
    }
}
