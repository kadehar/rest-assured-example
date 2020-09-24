package com.github.kadehar.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:test.properties",
})
public interface TestDataConfig extends Config {
    @Key("title")
    String title();

    @Key("assignee")
    String assignee();
}
