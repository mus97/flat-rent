package by.hata.test;

import org.testng.annotations.Test;
import by.hata.pageobjects.HomePage;
import by.hata.pageobjects.SearchResultPage;

public class HataTest extends AbstractTest {
    @Test
    public void testHataSearch() throws InterruptedException {
        String city = "минСК";
        String action = "Снять";
        String what = "Квартиру";
        String rooms = "2";
        String region = "Фрунзенский";


        SearchResultPage searchResultPage = new HomePage(driver).openPage()
                .selectCity(city)
                .selectAction(action)
                .selectWhatTo(what)
                .selectNumberOfRooms(rooms)
                .clickButtonSearch()
                .selectRegion(region)
                .clickButtonSubmit()
                .clickButtonSearch()
                .getListResults()
                .findSmallestPrice();


    }
}
