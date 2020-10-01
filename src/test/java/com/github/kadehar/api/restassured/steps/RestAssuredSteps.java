package com.github.kadehar.api.restassured.steps;

import com.github.kadehar.api.model.Issue;
import com.github.kadehar.config.ConfigHelper;
import io.qameta.allure.restassured.AllureRestAssured;

import static com.github.kadehar.api.restassured.spec.CustomSpec.spec;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

public class RestAssuredSteps {
    public Issue extractIssueAfterRequestWithoutSpec(final int issue) {
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

    public void validateResponseWithoutSpec(final int issue,
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

    public Issue extractIssueAfterRequestWithSpec(final int issue) {
        // @formatter:off
        return
                spec().request()
                        .get("issues/{issue}", issue)
                    .then()
                        .log().body()
                        .extract()
                        .as(Issue.class);
        // @formatter:on
    }

    public void validateResponseWithSpec(final int issue,
                                         final String assignee,
                                         final String title,
                                         final String... labels) {
        // @formatter:off
        spec().request()
                .get("issues/{issue}", issue)
            .then()
                .log().body()
                .spec(spec().expectedResponse())
                .body("labels.name.flatten()", hasItems(labels))
                .body("assignee.login", is(assignee))
                .body("title", is(title));
        // @formatter:on
    }

    public void validateResponseUsingGroovyPower(final int issue,
                                                 final String assignee,
                                                 final String title,
                                                 final String... labels) {
        // @formatter:off
        spec().request()
                .get("issues")
                .then()
                .log().body()
                .spec(spec().expectedResponse())
                .body("find{it.number == " + issue + "}.assignee.login", is(assignee))
                .body("find{it.number == " + issue + "}.title", is(title))
                .body("find{it.number == " + issue + "}.labels.name.flatten()",hasItems(labels));
        // @formatter:on
    }
}
