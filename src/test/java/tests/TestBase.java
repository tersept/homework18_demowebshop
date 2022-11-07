package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import config.ConfigReader;
import config.WebDriverProvider;
import config.web.WebConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    private static final WebConfig webConfig = ConfigReader.Instance.read();
    @BeforeAll
    static void config() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebDriverProvider webDriverProvider = new WebDriverProvider(webConfig);
        webDriverProvider.webConfig();
        webDriverProvider.apiConfig();
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
