package com.ttn.goals.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

@Slf4j
public class BaseController {

    public void validateRequest(Validator validator, BindingResult bindingResult, Object co) throws Exception {
        log.info("-> Validating request");
        validator.validate(co, bindingResult);
        if (bindingResult.hasErrors()) {
            log.error("Error Found In Request:: Validator:" + validator.getClass() + " CO::" + co.getClass());
        }
        log.info("<- Request Validated Successfully:: for Validator::" + validator.getClass() + " CO::" + co.getClass());
    }
}
