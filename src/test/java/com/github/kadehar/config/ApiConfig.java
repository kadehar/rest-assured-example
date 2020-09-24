package com.github.kadehar.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:api.properties",
        "file:~/.token.properties"
})
public interface ApiConfig extends Config {
    @Key("github.api.base.url")
    String baseURL();

    @Key("github.api.token")
    @DefaultValue("GJKHjhgjhgjhgjhgjhGJHGkjgjkhgjsgdfjksdgfkj")
    String token();

    @Key("github.api.base.path")
    String basePath();
}
