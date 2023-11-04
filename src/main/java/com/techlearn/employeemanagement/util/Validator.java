package com.techlearn.employeemanagement.util;

import org.apache.commons.validator.routines.EmailValidator;

public class Validator {
    public static boolean isValidEmail(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
