package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class WomenItemsPage extends BasePage {
    public WomenItemsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Women")
    static
    WebElement WomenButton;

    @FindBy(id = "subcategories")
    static
    WebElement presenceOfSubcategories;

    @FindBy(css = ".right-block .price.product-price")
    static
    List<WebElement> listOfPricesByCss;


    public static void clickOnWomensButton() {
        WomenButton.click();
        wait.until(ExpectedConditions.visibilityOf(presenceOfSubcategories));
    }


    public static List<Double> getProductPrices() {
        return listOfPricesByCss.stream()
                .map(el -> el.getText().replace("$", ""))
                .map(el -> el.trim())
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }
}
