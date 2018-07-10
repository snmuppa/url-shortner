package com.sample.url.shortner.service;

public class UrlValidator {
    public static boolean validateUrl(String url) {
        String[] schemes = { "http", "https" }; // DEFAULT schemes = "http", "https", "ftp"
        org.apache.commons.validator.routines.UrlValidator urlValidator = new org.apache.commons.validator.routines.UrlValidator(schemes);
        return urlValidator.isValid(url);
    }
}
