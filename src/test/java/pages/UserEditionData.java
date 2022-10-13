package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class UserEditionData {
    private SelenideElement newEmail = $("#Email"),
            loginPassword = $("#Password"),

    customerEmail = $(".account");

    public UserEditionData checkUserEdition(String newEmail) {
        customerEmail.shouldBe(Condition.visible).shouldHave(Condition.text(newEmail), Duration.ofSeconds(5));
        return this;
    }


    public UserEditionData openLoginPage() {
        open("/login");
        return this;
    }

    public UserEditionData setEmailLoginPage(String value) {
        newEmail.setValue(value);
        return this;
    }
    public UserEditionData setPasswordLoginPage(String value) {
        loginPassword.setValue(value);
        return this;
    }

    public UserEditionData loginSubmit() {
        $(".login-button").click();
        return this;
    }

    public UserEditionData saveSubmit() {
        $("[value=Save]").click();
        return this;
    }
    public UserEditionData checkRegistration(String email) {
        customerEmail.shouldBe(Condition.visible).shouldHave(Condition.text(email));
        return this;
    }
}

