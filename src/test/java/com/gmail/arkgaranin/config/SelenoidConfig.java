package com.gmail.arkgaranin.config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:selenoid.properties")
public interface SelenoidConfig extends Config {

  @Key("login")
  String getLogin();

  @Key("password")
  String getPassword();
}
