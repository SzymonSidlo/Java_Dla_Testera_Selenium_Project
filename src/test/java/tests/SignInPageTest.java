package tests;

import com.github.javafaker.Faker;
import model.AddressInformactionData;
import model.PersonalInformationData;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.SignInFormPage;
import utils.PageTitleUtils;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SignInPageTest extends BaseTest {

    private SignInFormPage signInFormPage;

    Faker faker = new Faker();
    String fakeEmail = faker.internet().emailAddress();

    @BeforeEach
    public void setupTest() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        assertThat(driver.getTitle()).isEqualTo(PageTitleUtils.HOME_PAGE_TITLE);

        signInFormPage = new SignInFormPage(driver);
    }

    @Test
    @Order(1)
    public void shouldNotAllowToSignInWithEmptyEmail() {
        SignInFormPage.clickOnSignInButton();
        SignInFormPage.clickOnEmailPanel1();
        SignInFormPage.clickOnCreateAnAccountButton();
        assertThat(SignInFormPage.isRedAlertBoxDisplayed()).isTrue();
    }

    @Test
    @Order(2)
    public void shouldNotAllowToSignInWithIncompleteEmail() {
        SignInFormPage.clickOnSignInButton();
        SignInFormPage.clickOnEmailPanel1();
        SignInFormPage.enterEmail1("testunfinished@.");
        SignInFormPage.clickOnCreateAnAccountButton();
        assertThat(SignInFormPage.isRedAlertBoxDisplayed()).isTrue();
    }

    @Test
    @Order(3)
    public void shouldNotAllowToSignInUsingEmailWithoutAtSign() {
        SignInFormPage.clickOnSignInButton();
        SignInFormPage.clickOnEmailPanel1();
        SignInFormPage.enterEmail1("testwithoutat.com");
        SignInFormPage.clickOnCreateAnAccountButton();
        assertThat(SignInFormPage.isRedAlertBoxDisplayed()).isTrue();
    }

    @Test
    @Order(4)
    public void shouldNotAllowToSignInWithExistingEmail() {
        SignInFormPage.clickOnSignInButton();
        SignInFormPage.clickOnEmailPanel1();
        SignInFormPage.enterEmail1("testowymail1@o2.pl");
        SignInFormPage.clickOnCreateAnAccountButton();
        assertThat(SignInFormPage.isRedAlertBoxDisplayed()).isTrue();
    }

    @Test
    @Order(5)
    public void shouldBeAbleToCreateAnAccount() {

        SignInFormPage.clickOnSignInButton();
        SignInFormPage.clickOnEmailPanel1();
        SignInFormPage.enterEmail1(fakeEmail);

        SignInFormPage.clickOnCreateAnAccountButton();
        // Część dot. formularza  personal data.
        SignInFormPage.clickOnMaleGenderLocation();

        PersonalInformationData setPersonalData = new PersonalInformationData();
        setPersonalData.setFirstName("Alojzy");
        setPersonalData.setLastName("Awaryjny");
        setPersonalData.setPassword("JavaDlaTestera4");
        SignInFormPage.enterPersonalInformationData(setPersonalData);

        SignInFormPage.enterDateOfBirthData();

        // Część dot. formularza address data.
        AddressInformactionData setAddressData = new AddressInformactionData();
        setAddressData.setAddress("17 street");
        setAddressData.setCity("Hagerhill");
        setAddressData.setZipOrPostalCode("41222");
        setAddressData.setMobilePhone("528751969");
        SignInFormPage.enterAddressInformationData(setAddressData);

        SignInFormPage.clickOnRegisterButton();

        assertThat(SignInFormPage.isMyAccountSpanDisplayed()).isTrue();
    }
}
