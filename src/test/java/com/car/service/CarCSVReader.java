package com.car.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarCSVReader {

    public List<Car> readCars(String filePath) {
        List<Car> carList = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            CsvToBean<Car> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(Car.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<Car> carIterator = csvToBean.iterator();
            while (carIterator.hasNext()) {
                carList.add(carIterator.next());
            }
        } catch (IOException exception) {
            throw new RuntimeException("Exception when reading output file: " + filePath, exception);
        }
        return carList;
    }
}
