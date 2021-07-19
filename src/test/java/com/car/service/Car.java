package com.car.service;

import com.opencsv.bean.CsvBindByName;

import java.util.Objects;

public class Car {

    //Header: REGISTRATION,MAKE,MODEL,COLOR,YEAR

    @CsvBindByName(column = "REGISTRATION", required = true)
    private String registration;

    @CsvBindByName(column = "MAKE", required = true)
    private String make;

    @CsvBindByName(column = "MODEL", required = true)
    private String model;

    @CsvBindByName(column = "COLOR", required = true)
    private String color;

    @CsvBindByName(column = "YEAR", required = true)
    private String year;

    public Car() {
    }

    public Car(String registration, String make, String model, String color, String year) {
        this.registration = registration;
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Car{" +
                "registration='" + registration + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(registration, car.registration) &&
                Objects.equals(make, car.make) &&
                Objects.equals(model, car.model) &&
                Objects.equals(color, car.color) &&
                Objects.equals(year, car.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registration, make, model, color, year);
    }
}
