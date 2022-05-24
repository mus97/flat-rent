package by.hata.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ApartmentSearch extends AbstractPage {
    protected ApartmentSearch(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[@data-select2-id ='9']")
    private WebElement selectDo;

    String buttonChooseRentOrBuy = "//li[text()= '%s']";

    @FindBy(xpath = "(//span[@class= 'selection'])[2]")
    private WebElement selectWhat;

    private String buttonChooseWhat = "//li[text()= '%s']";

    private String chooseNumberOfRooms = "//label[text()= '%s']";

    @FindBy(xpath = "//button[@class='b-landing__item b-landing__item_btn btn btn-primary']")
    private WebElement buttonSearch;



    public ApartmentSearch selectAction(String action) {
        selectDo.click();
        driver.findElement(By.xpath(String.format(buttonChooseRentOrBuy, action))).click();
        return this;
    }

    public ApartmentSearch selectWhatTo(String what) {
        selectWhat.click();
        driver.findElement(By.xpath(String.format(buttonChooseWhat, what))).click();
        return this;
    }

    public ApartmentSearch selectNumberOfRooms(String rooms) {
        driver.findElement(By.xpath(String.format(chooseNumberOfRooms, rooms))).click();
        return this;
    }


    public SearchResultPage clickButtonSearch() throws InterruptedException {

        buttonSearch.click();
        Thread.sleep(5000);
        return new SearchResultPage(driver);
    }
}
