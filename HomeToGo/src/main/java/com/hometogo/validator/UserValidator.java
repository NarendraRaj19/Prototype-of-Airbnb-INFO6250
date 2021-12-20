/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hometogo.validator;

import com.hometogo.dao.UserDAO;
import com.hometogo.exception.UserException;
import com.hometogo.pojo.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author naren
 */
public class UserValidator implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return User.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "empty-username", "The Username field cannot be empty!");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "empty-password", "The Password field cannot be empty!");
        
    }
    
}
