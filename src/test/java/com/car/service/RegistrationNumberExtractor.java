package com.car.service;

import java.util.ArrayList;
import java.util.List;

public class RegistrationNumberExtractor {

    public static final String REGISTRATION_PREFIX = "registration ";
    public static final String REGISTRATIONS_PREFIX = "registraions ";
    private static final int REGISTRATION_NUMBER_MINIMUM_LENGTH = 5;

    public String findRegistrationNumber(String line) {
        int startIndex = line.indexOf(REGISTRATION_PREFIX) + REGISTRATION_PREFIX.length();
        int endIndex = line.indexOf(" ", startIndex + 5);
        String registrationNumber = line.substring(startIndex, endIndex);
        return normalize(registrationNumber);
    }

    public List<String> findRegistrationNumbers(String line) {
        List<String> registrationNumberList = new ArrayList<>();
        int startIndex = line.indexOf(REGISTRATIONS_PREFIX) + REGISTRATIONS_PREFIX.length();
        int endIndex = line.indexOf(".", startIndex + REGISTRATION_NUMBER_MINIMUM_LENGTH);
        String registrationNumbers = line.substring(startIndex, endIndex);
        String[] registrationNumberArray = registrationNumbers.split(" and ");
        for (String registrationNumber : registrationNumberArray) {
            registrationNumberList.add(normalize(registrationNumber));
        }
        return registrationNumberList;
    }

    private String normalize(String str) {
        return str.trim().replaceAll(" ", "").toUpperCase();
    }

}
