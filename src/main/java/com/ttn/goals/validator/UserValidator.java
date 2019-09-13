package com.ttn.goals.validator;


import com.ttn.goals.co.UserCO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import java.io.IOException;
import java.util.Objects;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserCO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        UserCO userCO = (UserCO) target;
        validateUserCO(userCO,errors);


    }

   private void  validateUserCO(UserCO userCO, Errors errors)
   {

       if(StringUtils.isBlank(userCO.getUserId())){

           errors.reject("userIdNotNull","Please entere UserId");

       }
       if(StringUtils.isBlank(userCO.getPassword())){

           errors.reject("passwordNotNull","Please specify password");
       }

       if(StringUtils.isBlank(userCO.getEmail())){

           errors.reject("emailNotNull", "Please enter any email");
       }


    }
}
