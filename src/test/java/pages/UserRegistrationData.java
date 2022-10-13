package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class UserRegistrationData {
    private SelenideElement //genderSelect = $("#gender-female"),
            firstNameInput = $("#FirstName"),
            lastNameInput = $("#LastName"),
            emailInput = $("#Email"),
            passwordInput = $("#Password"),
            confirmpasswordInput = $("#ConfirmPassword"),
            registrationResult = $(".result"),
            customerEmail = $(".account");

    public UserRegistrationData openPage() {
        open("/register");
        return this;
    }

    public UserRegistrationData registerSubmit() {
        $("#register-button").click();
        return this;
    }

    public UserRegistrationData setFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public UserRegistrationData setLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public UserRegistrationData setEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    public UserRegistrationData setPassword(String value) {
        passwordInput.setValue(value);
        return this;
    }

    public UserRegistrationData setConfirmPassword(String value) {
        confirmpasswordInput.setValue(value);
        return this;
    }

//    public UserRegistrationData setGender(String value) {
//        $(genderSelect).setValue("F");
//        return this;
//    }

    public UserRegistrationData checkRegistration(String email) {
        registrationResult.should(Condition.appear).shouldHave(Condition.text("Your registration completed"));
        customerEmail.shouldBe(Condition.visible).shouldHave(Condition.text(email));
        return this;
    }
}
