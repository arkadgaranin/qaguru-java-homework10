package com.gmail.arkgaranin.tests;

import com.codeborne.selenide.Configuration;
import com.gmail.arkgaranin.helpers.Attachments;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBase {

  @BeforeAll
//  @Step("Конфигурируем браузер и удаленный запуск")
  static void setup() {
    Configuration.baseUrl = "https://demoqa.com";
    Configuration.browser = System.getProperty("browser", "chrome");
    Configuration.browserVersion = System.getProperty("browserVersion", "97");
    Configuration.browserSize = System.getProperty("browserSize", "1920x1080");

    String login = System.getProperty("login");
    String password = System.getProperty("password");
    String url = System.getProperty("url");
    String remoteUrl = "https://" + login + ":" + password + "@" + url;
    Configuration.remote = remoteUrl;

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
