package com.github.kadehar.api.steps;

import com.github.kadehar.api.model.Issue;
import com.github.kadehar.config.ConfigHelper;
import io.qameta.allure.restassured.AllureRestAssured;

import static com.github.kadehar.spec.CustomSpec.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

public class ApiSteps {
    public Issue requestWithExtractableResponse(final int issue) {
        // @formatter:off
        return
                spec().request()
                        .log().uri()
                .when()
                        .get("issues/{issue}", issue)
                .then()
                        .log().body()
                        .extract()
                        .as(Issue.class);
        // @formatter:on
    }

    public Issue standardRequestWithExtractableResponse(final int issue) {
        // @formatter:off
        return
                given()
                        .baseUri("https://api.github.com")
                        .header("Authorization", String.format("token %s", ConfigHelper.getToken()))
                        .filter(new AllureRestAssured())
                        .log().uri()
                .when()
                        .get("/repos/kadehar/qa_guru_course_homework/issues/{issue}", issue)
                .then()
                        .log().body()
                        .extract()
                        .as(Issue.class);
        // @formatter:on
    }

    public void standardCheckThatIssueIsCreated(final int issue,
                                                final String assignee,
                                                final String title,
                                                final String... labels) {
        // @formatter:off
        given()
                .baseUri("https://api.github.com")
                .header("Authorization", String.format("token %s", ConfigHelper.getToken()))
                .filter(new AllureRestAssured())
                .log().uri()
        .when()
                .get("/repos/kadehar/qa_guru_course_homework/issues/{issue}", issue)
        .then()
                .log().body()
                .body("labels.name.flatten()", hasItems(labels))
                .body("assignee.login", is(assignee))
                .body("title", is(title));
        // @formatter:on
    }

    public void checkThatIssueIsCreated(final int issue,
                                        final String assignee,
                                        final String title,
                                        final String... labels) {
        // @formatter:off
        spec().request()
                .log().uri()
        .when()
                .get("issues/{issue}", issue)
        .then()
                .log().body()
                .spec(spec().response())
                .body("labels.name.flatten()", hasItems(labels))
                .body("assignee.login", is(assignee))
                .body("title", is(title));
        // @formatter:on
    }
}
