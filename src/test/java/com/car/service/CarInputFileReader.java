package com.car.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CarInputFileReader {

    public List<String> findCarNumbers(String filePath) {
        List<String> carRegistrationNumberList = new ArrayList<>();
        RegistrationNumberExtractor registrationNumberExtractor = new RegistrationNumberExtractor();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.indexOf(RegistrationNumberExtractor.REGISTRATION_PREFIX) != -1) {
                    carRegistrationNumberList.add(registrationNumberExtractor.findRegistrationNumber(line));
                } else if (line.indexOf(RegistrationNumberExtractor.REGISTRATIONS_PREFIX) != -1) {
                    carRegistrationNumberList.addAll(registrationNumberExtractor.findRegistrationNumbers(line));
                }
            }
        } catch (IOException exception) {
            throw new RuntimeException("Exception when reading input file: " + filePath, exception);
        }
        return carRegistrationNumberList;
    }
}
