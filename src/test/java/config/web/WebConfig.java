package config.web;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:${env}.properties",
        "file:~/${env}.properties",
        "file:./${env}.properties"
})
public interface WebConfig extends org.aeonbits.owner.Config {
    @Key("browser")
    Browser getBrowser();

    @Key("browserVersion")
    String getBrowserVersion();

    @Key("browserSize")
    String getBrowserSize();

    @Key("browserPosition")
    String getBrowserPosition();

    @Key("isRemote")
    boolean isRemote();

    @Key("baseUrl")
    @DefaultValue("https://demowebshop.tricentis.com/")
    String getBaseUrl();

    @Key("remoteUrl")
    String getRemoteUrl();
}