package com.github.kadehar.tests;

import com.github.kadehar.api.steps.ApiSteps;
import com.github.kadehar.config.ConfigHelper;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    static ApiSteps apiSteps;
    static final String BUG = "bug";
    static final String DUPLICATE = "duplicate";
    static final String ASSIGNEE = ConfigHelper.getAssignee();
    static final String TITLE = ConfigHelper.getTitle();

    @BeforeAll
    public static void init() {
        apiSteps = new ApiSteps();
    }
}
