package by.hata.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePage extends AbstractPage {
    private final String BASE_URL = "https://www.hata.by/";
    private String buttonSelectCity = "//span[@id = 'select2-select2-select-city-container']";
    private String chooseCity = "//input[@type = 'search']";

    public HomePage(WebDriver driver) {

        super(driver);
    }

    public HomePage openPage() {
        driver.get(BASE_URL);
        return this;
    }

    public ApartmentSearch selectCity (String city) {

        driver.findElement(By.xpath(String.format(buttonSelectCity))).click();
        driver.findElement(By.xpath(String.format(chooseCity))).sendKeys(city);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath(String.format(chooseCity))).sendKeys(Keys.ENTER);
        return new ApartmentSearch(driver);
    }

}
