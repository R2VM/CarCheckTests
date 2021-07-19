package com.car.pages;

import com.car.service.Car;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.car.constants.WebDriverConstants.WAIT_TIME_LARGE;

public class CarSearchResultsPage {
    public static final String REGISTRATION = "Registration";
    public static final String MAKE = "Make";
    public static final String MODEL = "Model";
    public static final String COLOUR = "Colour";
    public static final String YEAR = "Year";
    private WebDriver driver;
    @FindBy(how = How.XPATH, using = "//div[@id='m']//dt[text()='Registration']/../dd")
    private WebElement registrationElement;

    @FindBy(how = How.XPATH, using = "//div[@id='m']//dt[text()='Make']/../dd")
    private WebElement makeElement;

    @FindBy(how = How.XPATH, using = "//div[@id='m']//dt[text()='Model']/../dd")
    private WebElement modelElement;

    @FindBy(how = How.XPATH, using = "//div[@id='m']//dt[text()='Colour']/../dd")
    private WebElement colourElement;

    @FindBy(how = How.XPATH, using = "//div[@id='m']//dt[text()='Year']/../dd")
    private WebElement yearElement;

    public CarSearchResultsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public Car getSearchResults() {
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_LARGE);
        wait.until(ExpectedConditions.visibilityOf(registrationElement));
        //
        String registrationNumber = registrationElement.getText();
        if (StringUtils.isAllBlank(registrationNumber)) {
            System.out.println("Car details not found in website");
            return null;
        }
        String make = makeElement.getText();
        String model = modelElement.getText();
        String colour = colourElement.getText();
        String year = yearElement.getText();
        //
        Car car = new Car(registrationNumber, make, model, colour, year);
        System.out.println("Search results: " + car);
        return car;
    }
}
