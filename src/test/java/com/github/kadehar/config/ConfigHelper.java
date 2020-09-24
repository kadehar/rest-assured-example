package com.github.kadehar.config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigHelper {

    public static String getBaseURL() {
        return getConfig().baseURL();
    }

    public static String getToken() {
        return getConfig().token();
    }

    public static String getBasePath() {
        return getConfig().basePath();
    }

    public static String getTitle() {
        return getDataConfig().title();
    }

    public static String getAssignee() {
        return getDataConfig().assignee();
    }

    private static ApiConfig getConfig() {
        return ConfigFactory.newInstance().create(ApiConfig.class);
    }

    private static TestDataConfig getDataConfig() {
        return ConfigFactory.newInstance().create(TestDataConfig.class);
    }
}
