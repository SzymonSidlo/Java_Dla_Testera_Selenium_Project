package pages;

import com.github.javafaker.Faker;
import model.AddressInformactionData;
import model.PersonalInformationData;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class SignInFormPage extends BasePage {


    public SignInFormPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(css = ".header_user_info .login")
    static
    WebElement signInButton;

    @FindBy(css = ".page-heading")
    static
    WebElement setEmailPossibility;

    @FindBy(css = ".is_required.validate.account_input.form-control")
    static
    WebElement emailPanel1;

    @FindBy(css = "#SubmitCreate.btn.btn-default.button.button-medium.exclusive")
    static
    WebElement createAnAccountButton;


    // część dot. formularza

    @FindBy(id = "uniform-id_gender1")
    static
    WebElement maleGenderLocation;

    @FindBy(css = "#customer_firstname")
    static
    WebElement customerFirstNameInput;

    @FindBy(css = "#customer_lastname")
    static
    WebElement customerLastNameOutput;

    @FindBy(css = "#passwd")
    static
    WebElement passwordInput;

    @FindBy(id = "days")
    static
    WebElement dayOfBirthCheckbox;

    @FindBy(id = "months")
    static
    WebElement monthOfBirthCheckbox;

    @FindBy(id = "years")
    static
    WebElement yearOfBirthCheckbox;

    @FindBy(id = "address1")
    static
    WebElement addressInput;

    @FindBy(id = "city")
    static
    WebElement cityInput;

    @FindBy(id = "postcode")
    static
    WebElement zipOrPostalCode;

    @FindBy(id = "phone_mobile")
    static
    WebElement mobilePhone;

    @FindBy(id = "submitAccount")
    static
    WebElement registerButton;

    @FindBy(id = "id_state")
    static
    WebElement stateCheckbox;

    @FindBy(xpath = "//span[contains(@class,'navigation_page') and contains(text(),'My account')]")
    static
    WebElement properMyAccountPage;

    @FindBy(css = "#create_account_error.alert.alert-danger")
    static
    WebElement redAlertBox;


    public static void clickOnSignInButton() {
        signInButton.click();
        wait.until(ExpectedConditions.visibilityOf(setEmailPossibility));
    }

    public static void clickOnEmailPanel1() {
        emailPanel1.click();
    }

    public static void enterEmail1(String email1) {
        emailPanel1.sendKeys(email1);
    }

    public static void clickOnCreateAnAccountButton() {
        createAnAccountButton.click();
    }

    public static void clickOnMaleGenderLocation() {
        wait.until(ExpectedConditions.visibilityOf(maleGenderLocation));
        maleGenderLocation.click();
    }

    public static void enterPersonalInformationData(PersonalInformationData personalData) {
        customerFirstNameInput.sendKeys(personalData.getFirstName());
        customerLastNameOutput.sendKeys(personalData.getLastName());
        passwordInput.sendKeys(personalData.getPassword());
    }

    public static void enterDateOfBirthData() {
        Select dayOfBirthCheckbox = new Select(SignInFormPage.dayOfBirthCheckbox);
        dayOfBirthCheckbox.selectByIndex(2);

        Select monthOfBirthCheckbox = new Select(SignInFormPage.monthOfBirthCheckbox);
        monthOfBirthCheckbox.selectByIndex(8);

        Select yearOfBirthCheckbox = new Select(SignInFormPage.yearOfBirthCheckbox);
        yearOfBirthCheckbox.selectByValue("1985");
    }

    public static void enterAddressInformationData(AddressInformactionData addressData) {
        addressInput.sendKeys(addressData.getAddress());
        cityInput.sendKeys(addressData.getCity());
        zipOrPostalCode.sendKeys(addressData.getZipOrPostalCode());
        mobilePhone.sendKeys(addressData.getMobilePhone());

        Select stateCheckbox = new Select(SignInFormPage.stateCheckbox);
        stateCheckbox.selectByIndex(17);
    }

    public static void clickOnRegisterButton() {
        registerButton.click();
    }

    public static boolean isRedAlertBoxDisplayed() {
        return isProperWebelementDisplayed(redAlertBox);
    }


    public static boolean isMyAccountSpanDisplayed() {
        return isProperWebelementDisplayed(properMyAccountPage);
    }

    private static boolean isProperWebelementDisplayed(WebElement box) {
        wait.until(ExpectedConditions.visibilityOf(box));

        boolean isDisplayed = false;
        try {
            isDisplayed = box.isDisplayed();
        } catch (NoSuchElementException e) {
        }
        return isDisplayed;
    }
}
