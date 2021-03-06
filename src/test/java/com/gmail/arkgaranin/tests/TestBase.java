package com.gmail.arkgaranin.tests;

import com.codeborne.selenide.Configuration;
import com.gmail.arkgaranin.config.SelenoidConfig;
import com.gmail.arkgaranin.helpers.Attachments;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

  @BeforeAll
  static void setup() {
    Configuration.baseUrl = "https://demoqa.com";

    SelenoidConfig config = ConfigFactory.create(SelenoidConfig.class, System.getProperties());

//    String login = System.getProperty("login");
//    String password = System.getProperty("password");
    String url = System.getProperty("url");
    Configuration.remote = "https://" + config.getLogin() + ":" + config.getPassword() + "@" + url;

    Configuration.browser = System.getProperty("browser");
    Configuration.browserVersion = System.getProperty("browserVersion");
    Configuration.browserSize = System.getProperty("browserSize");

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("enableVNC", true);
    capabilities.setCapability("enableVideo", true);
    Configuration.browserCapabilities = capabilities;
  }

  @AfterEach
  void addAttachments() {
    Attachments.takeScreenshot();
    Attachments.takePageSource();
    Attachments.addVideo();
    Attachments.browserConsoleLogs();
    closeWebDriver();
  }
}
