package ru.plotnikov.MySecondTestAppSpringBoot.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.plotnikov.MySecondTestAppSpringBoot.exception.UnsupportedCodeException;
import ru.plotnikov.MySecondTestAppSpringBoot.exception.ValidationFailedException;
import ru.plotnikov.MySecondTestAppSpringBoot.model.*;
import ru.plotnikov.MySecondTestAppSpringBoot.service.AnnualBonusService;
import ru.plotnikov.MySecondTestAppSpringBoot.service.ModifyRequestService;
import ru.plotnikov.MySecondTestAppSpringBoot.service.ModifyResponseService;
import ru.plotnikov.MySecondTestAppSpringBoot.service.ValidationService;
import ru.plotnikov.MySecondTestAppSpringBoot.util.DateTimeUtil;

import java.util.Date;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    private final ModifyRequestService modifyRequestService;
    private final AnnualBonusService annualBonusService;

    @Autowired
    public MyController(ValidationService validationService, @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifyResponseService, ModifyRequestService modifyRequestService, AnnualBonusService annualBonusService) {
        this.validationService = validationService;
        this.modifyResponseService = modifyResponseService;
        this.modifyRequestService = modifyRequestService;
        this.annualBonusService = annualBonusService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request, BindingResult bindingResult) {

        log.info("request: {}", request);

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();

        try {
            validationService.isValid(bindingResult);
            validationService.validate123(request);
        } catch (ValidationFailedException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (UnsupportedCodeException e) {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNSUPPORTED);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        modifyResponseService.modify(response);
        modifyRequestService.modify(request);

        annualBonusService.calculate(request.getPosition(), request.getSalary(), request.getBonus(), request.getWorkDays());

        return new ResponseEntity<>(modifyResponseService.modify(response), HttpStatus.OK);
    }
}
