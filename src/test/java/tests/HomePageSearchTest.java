package tests;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.SearchItemPage;
import pages.WomenItemsPage;
import utils.PageTitleUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePageSearchTest extends BaseTest {

    private SearchItemPage searchItemPage;

    @BeforeEach
    public void setupTest() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        assertThat(driver.getTitle()).isEqualTo(PageTitleUtils.HOME_PAGE_TITLE);

        searchItemPage = new SearchItemPage(driver);
    }

    @Test

    public void shouldSeeSearchResults() {
        SearchItemPage.clickOnSearchPanel();
        SearchItemPage.enterSearchWords("Blouse");
        SearchItemPage.clickOnSearchButton();

        Assertions.assertThat(SearchItemPage.getHeadingCounter()).containsExactly("1 result has been found.");

        Assertions.assertThat(SearchItemPage.getResultOfSearching()).containsExactly("Blouse");

        Assertions.assertThat(SearchItemPage.getSomePrice()).isNotEmpty();
    }
}
