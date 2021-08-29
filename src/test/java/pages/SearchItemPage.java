package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class SearchItemPage extends BasePage {

    public SearchItemPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#search_query_top.search_query.form-control.ac_input")
    static
    WebElement searchPanel;

    @FindBy(css = "button.btn.btn-default.button-search")
    static
    WebElement searchButton;

    @FindBy(css = ".page-heading.product-listing .heading-counter")
    static
    List<WebElement> headingCounter;

    @FindBy(css = ".top-pagination-content.clearfix .product-count")
    static
    WebElement topPagination;

    @FindBy(css = ".right-block .product-name")
    static
    List<WebElement> resultOfSearching;

    @FindBy(css = ".right-block .price.product-price")
    static
    List<WebElement> somePrice;

    public static void clickOnSearchPanel() {
        searchPanel.click();
    }

    public static void enterSearchWords(String searchWords) {
        searchPanel.sendKeys(searchWords);
    }

    public static void clickOnSearchButton() {
        searchButton.click();
        wait.until(ExpectedConditions.visibilityOf(topPagination));
    }

    public static List<String> getHeadingCounter() {
        return headingCounter.stream()
                .map(el -> el.getText().trim())
                .collect(Collectors.toList());
    }

    public static List<String> getResultOfSearching() {
        return resultOfSearching.stream()
                .map(el -> el.getText().trim())
                .collect(Collectors.toList());
    }

    public static List<String> getSomePrice() {
        return somePrice.stream()
                .map(el -> el.getText().trim())
                .collect(Collectors.toList());
    }
}
