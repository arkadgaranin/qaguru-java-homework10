package com.gmail.arkgaranin.tests;

import com.codeborne.selenide.Configuration;
import com.gmail.arkgaranin.helpers.Attachments;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBase {

  @BeforeAll
  @Step("Конфигурируем браузер и удаленный запуск")
  static void setup() {
    Configuration.baseUrl = "https://demoqa.com";

    String browser = System.getProperty("browser");
    String browserVersion = System.getProperty("version");
    String browserSize = System.getProperty("size");
    String url = System.getProperty("url");
    String login = System.getProperty("login");
    String password = System.getProperty("password");
    String remoteUrl = "https://" + login + ":" + password + "@" + url;

    Configuration.browser = browser;
    Configuration.browserVersion = browserVersion;
    Configuration.browserSize = browserSize;
    Configuration.remote = remoteUrl;

//    Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

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
  }
}
