package config;

import com.codeborne.selenide.Configuration;
import config.web.WebConfig;
import io.restassured.RestAssured;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverProvider {

    private final WebConfig webConfig;

    public WebDriverProvider(WebConfig webConfig) {
        this.webConfig = webConfig;
    }

  public void apiConfig() {
       RestAssured.baseURI = webConfig.getBaseUrl();
  }

    public void webConfig() {
        Configuration.baseUrl = webConfig.getBaseUrl();
        Configuration.browser = webConfig.getBrowser().toString();
        Configuration.browserVersion = webConfig.getBrowserVersion();
        Configuration.browserSize = webConfig.getBrowserSize();
        Configuration.browserPosition = webConfig.getBrowserPosition();
        if (webConfig.isRemote()) {
            Configuration.remote = webConfig.getRemoteUrl();
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;
        }
    }
}
