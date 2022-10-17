package tests;

import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import pages.UserEditionData;
import pages.UserRegistrationData;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class UserRegistrationTest extends TestBase {
    private final String authCookieName = "NOPCOMMERCE.AUTH";
    private final String verificationTokenName = "__RequestVerificationToken";
    private final String verificationTokenHeaderValue = "nAQYfvmVi-pb1ij8SGbNHMbZO3lIu26H42P2sHoj_sDBkBQg9ocpcWoltYc1OX1WDn-XljjWCftLdokWU7r2ewdCTyyRrlkWukuGvugNOVc1";
    private final String verificationTokenInputValue = "Cc9Ykppui5qcAMhSbmkrpI1LH0x0cRObBvLVaj221vFETCgeVcWAfOJRWUVJITIiJptbVQF9CRI7kSJzDaaFG3xWNRiBYFiAH4LifxWanuo1";
    UserRegistrationData userRegistrationData = new UserRegistrationData();
    UserEditionData userEditionData = new UserEditionData();
    Faker faker = new Faker();

    private String firstName;
    private String lastName;
    private String password;
    private String email;

    private String loginEmail = "test@new.tt";
    private String newEmail = "test2@new.tt";
    private String loginPassword = "121212";

    private WebDriver driver;

//    @BeforeEach
//    public void startDriver() {
//        driver = new WebDriverProvider().get();
//    }
//
//    @AfterEach
//    public void stopDriver() {
//        driver.quit();
//    }

    @BeforeEach
    void setUserData() {
        // gender = "Male";
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        email = faker.internet().emailAddress();
        password = faker.numerify("121212");
    }

    @Tag("demowebshop")
    @Test
    @DisplayName("Регистрация нового пользователя через UI")
    void userRegistrationTest() {
        step("Открываем страницу с формой регистрации", () -> {
            userRegistrationData.openPage();
        });
        step("Заполняем обязательный поля", () -> {
            userRegistrationData
                    //.setGender(gender)
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setEmail(email)
                    .setPassword(password)
                    .setConfirmPassword(password);
        });
        step("Нажимаем кнопку Register", () -> {
            userRegistrationData.registerSubmit();
        });
        step("Проверяем что пользователь с указанным email зарегистрировался", () -> {
            userRegistrationData.checkRegistration(email);
        });
    }

    @Tag("demowebshop")
    @Test
    @DisplayName("Регистрация нового пользователя через API")
    void UserRegistrationApiTest() {
        String authCookieValue = given()
                .log().all()
                .when()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam(verificationTokenName, verificationTokenInputValue)
                .formParam("firstName", firstName)
                .formParam("lastName", lastName)
                .formParam("email", email)
                .formParam("password", password)
                .formParam("confirmPassword", password)
                .cookie(verificationTokenName, verificationTokenHeaderValue)
                .post("/register")
                .then()
                .log().all()
                .statusCode(302)
                .extract().cookie(authCookieName);
        open("/Themes/DefaultClean/Content/images/logo.png");

        Cookie authCookie = new Cookie(authCookieName, authCookieValue);
        WebDriverRunner.getWebDriver().manage().addCookie(authCookie);

        open("/registerresult/1");

        userRegistrationData.checkRegistration(email);
    }

    @Tag("demowebshop")
    @Test
    @DisplayName("Редактирование профиля пользователя через UI")
    void UserEditionTest() {
        step("Открываем страницу логина", () -> {
            userEditionData.openLoginPage();
        });
        step("Заполняем обязательный поля", () -> {
            userEditionData.setEmailLoginPage(loginEmail)
                    .setPasswordLoginPage(loginPassword);
        });
        step("Нажимаем кнопку Login", () -> {
            userEditionData.loginSubmit();
        });
        step("Проверяем что пользователь с указанным email зашел", () -> {
            userEditionData.checkRegistration(loginEmail);
        });
        step("Открываем страницу редактирования профиля", () -> {
            open("/customer/info");
        });
        step("Меняем email", () -> {
            userEditionData.setEmailLoginPage(newEmail);
        });

        step("Нажимаем кнопку Save", () -> {
            userEditionData.saveSubmit();
        });

        step("Проверяем что email пользователя поменялся", () -> {
            userEditionData.checkRegistration(newEmail);
        });
    }
}