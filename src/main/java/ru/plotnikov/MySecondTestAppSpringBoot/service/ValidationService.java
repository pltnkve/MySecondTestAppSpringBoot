package ru.plotnikov.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import ru.plotnikov.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.plotnikov.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.plotnikov.MySecondTestAppSpringBoot.model.Request;

@Service
public interface ValidationService {

    void isValid(BindingResult bindingResult) throws ValidationFailedException;

    void validate123(Request request) throws UnsupportedCodeException;
}
