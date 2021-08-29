package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.WomenItemsPage;
import utils.PageTitleUtils;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


public class WomanPageTests extends BaseTest {

    private WomenItemsPage womenItemPage;


    @BeforeEach
    public void setupTest() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        assertThat(driver.getTitle()).isEqualTo(PageTitleUtils.HOME_PAGE_TITLE);

        womenItemPage = new WomenItemsPage(driver);
    }

    @Test

    public void shouldSeeTheListOfPrices() {
        WomenItemsPage.clickOnWomensButton();

        List<Double> WomenPricesList = WomenItemsPage.getProductPrices();

        List<Double> listOfPricesLowerOrEqualZero = WomenPricesList.stream()
                .filter(el -> el <= 0)
                .collect(Collectors.toList());

        assertThat(listOfPricesLowerOrEqualZero).isEmpty();
    }
}
