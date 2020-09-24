package com.github.kadehar.config;

import org.aeonbits.owner.Config;

public interface TestDataConfig extends Config {
    @Key("title")
    String title();

    @Key("assignee")
    String assignee();
}
