package com.car;

import com.car.pages.CarSearchPage;
import com.car.pages.CarSearchResultsPage;
import com.car.service.Car;
import com.car.service.CarCSVReader;
import com.car.service.CarInputFileReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

import static com.car.util.PropertiesReader.getPropertiesReader;

public class CarCheckTest {

    private WebDriver driver;

    @Test
    public void testCarRegistrationNumbers() {

        //=================================================
        //GIVEN input file and output file having car details
        // Read cars from input file
        CarInputFileReader carInputFileReader = new CarInputFileReader();
        String inputFile = getPropertiesReader().getProperty("input_file");
        List<String> registrationNumberList = carInputFileReader.findCarNumbers(inputFile);

        // Read cars from output file
        CarCSVReader carCSVReader = new CarCSVReader();
        String outputFile = getPropertiesReader().getProperty("output_file");
        List<Car> carListFromFile = carCSVReader.readCars(outputFile);

        //=================================================
        //WHEN car details are retrieved from cartaxcheck website
        StringBuilder carCheckResults = new StringBuilder();

        CarSearchPage searchPage = new CarSearchPage(driver);
        CarSearchResultsPage searchResultsPage = new CarSearchResultsPage(driver);
        String url = getPropertiesReader().getProperty("url");

        List<Car> searchResultList = new ArrayList<>();
        for (String registrationNumber : registrationNumberList) {
            searchPage.open(url);
            searchPage.searchForCar(registrationNumber);
            Car car = searchResultsPage.getSearchResults();
            if (car == null) {
                carCheckResults.append(String.format("Car details (%s) are not found in cartaxcheck site.", registrationNumber));
            } else {
                searchResultList.add(car);
                System.out.println("Car details added: " + car);
            }
        }

        //=================================================
        //THEN the car details that are retrieved from cartaxcheck website should match with the details from output file.
        for (Car car : searchResultList) {
            Car carFromOutputFile = findCar(carListFromFile, car.getRegistration());
            if (carFromOutputFile == null) {
                carCheckResults.append(String.format("Car details (%s) are not found in output file.", car.getRegistration()));
            } else if (!car.equals(carFromOutputFile)) {
                carCheckResults.append(String.format("Car details (%s) are not matched.", car.getRegistration()));
            }
        }

        Assert.assertEquals("", carCheckResults.toString());

    }

    private Car findCar(List<Car> carList, String registrationNumber) {
        for (Car car : carList) {
            if (car.getRegistration().equals(registrationNumber)) {
                return car;
            }
        }
        return null;
    }

    @Before
    public void before() {
        WebDriverManager.chromedriver().setup();
        //TODO: get browser name from env file
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @After
    public void after() {
        driver.quit();
    }

}
