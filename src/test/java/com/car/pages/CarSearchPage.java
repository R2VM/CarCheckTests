package com.car.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.car.constants.WebDriverConstants.WAIT_TIME_MEDIUM;

public class CarSearchPage {
    private WebDriver driver;

    @FindBy(how = How.ID, using = "vrm-input")
    private WebElement inputField;

    @FindBy(how = How.XPATH, using = "//button[text()='Free Car Check']")
    private WebElement searchButton;

    public CarSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void open(String url) {
        driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_MEDIUM);
        wait.until(ExpectedConditions.titleIs("Car Tax Check | Free Car Check"));
    }

    public void searchForCar(String registrationNumber) {
        System.out.println("Searching for car: " + registrationNumber);
        inputField.sendKeys(registrationNumber);
        searchButton.click();
    }

}
