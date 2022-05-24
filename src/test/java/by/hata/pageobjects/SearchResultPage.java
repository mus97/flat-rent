package by.hata.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchResultPage extends AbstractPage {
    private static final Logger logger = LogManager.getLogger();
    ArrayList<String> listResults = new ArrayList<>();

    protected SearchResultPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        //getListResults();
    }

    private String searchResultPrice = "//div[@class = 'value']";
    private String searchResAdress = "//div[@class = 'b-catalog-table']//div[@class = 'title']/a[@target = '_blank']";

    @FindBy(xpath = "//div[@data-type = 'region']")
    private WebElement buttonRegion;

    private String chooseRegion = "//label[contains(text(), '%s')]";

    @FindBy(xpath = "//div[@class = 'b-modal b-modal_big js-region-search-modal']//a[@class = 'btn btn-sub-block btn-primary js-dismiss-modal']")
    private WebElement buttonSubmit;

    @FindBy(xpath = "//button[@class = 'btn btn-block btn-xl btn-primary']")
    private WebElement buttonSearchOnResP;

    public SearchResultPage selectRegion(String region){
        buttonRegion.click();
        driver.findElement(By.xpath(String.format(chooseRegion, region))).click();
        return this;
    }

    public SearchResultPage clickButtonSubmit (){
        buttonSubmit.click();
        return this;
    }

    public SearchResultPage clickButtonSearch (){
        buttonSearchOnResP.click();
        return this;
    }

    public SearchResultPage getListResults() {
        List<WebElement> adresses = driver.findElements(By.xpath(searchResAdress));
        List<String> listAdresses = adresses.stream().map(s -> s.getText()).collect(Collectors.toList());
        List<WebElement> prices = driver.findElements(By.xpath(searchResultPrice));
        List<String> listPrices = prices.stream().map(s -> s.getText()).collect(Collectors.toList());
        for (int i = 0; i < listAdresses.size() ; i++){
            listResults.add("\n" + listAdresses.get(i) + " - " + listPrices.get(i));
        }
        logger.info(listResults);
        return this;
    }
    public SearchResultPage findSmallestPrice() {
        int minPrise = driver.findElements(By.xpath(searchResultPrice))
                .stream().map(s -> s.getText().replaceAll("[^0-9]", ""))
                .mapToInt(s -> Integer.parseInt(s)).min().getAsInt();
        String smallestPrice = "Smallest Price - " + minPrise + " $/мес.";
        logger.info(smallestPrice);
        return this;
    }
}



