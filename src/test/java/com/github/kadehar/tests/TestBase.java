package com.github.kadehar.tests;

import com.github.kadehar.config.ConfigHelper;
import com.github.kadehar.api.restassured.steps.RestAssuredSteps;
import com.github.kadehar.api.retrofit.steps.RetrofitSteps;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    static RestAssuredSteps restAssured;
    static RetrofitSteps retrofit;
    static final String BUG = "bug";
    static final String DUPLICATE = "duplicate";
    static final String ASSIGNEE = ConfigHelper.getAssignee();
    static final String TITLE = ConfigHelper.getTitle();
    static final Integer ISSUE = 23;

    @BeforeAll
    public static void init() {
        restAssured = new RestAssuredSteps();
        retrofit = new RetrofitSteps();
    }
}
