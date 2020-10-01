package com.github.kadehar.api.restassured.spec;

import com.github.kadehar.config.ConfigHelper;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.github.kadehar.filter.LogFilter.filters;
import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class CustomSpec {
    /** Формирует для всех запросов базовые параметры URL, path, header и фильтры отчета. */
    private final RequestSpecification request = given()
            .baseUri(ConfigHelper.getBaseURL())
            .basePath(ConfigHelper.getBasePath())
            .header("Authorization", String.format("token %s", ConfigHelper.getToken()))
            .filter(filters().withCustomTemplates())
            .log().uri()
            .when();

    /** Формирует для всех ответов базовый набор проверок */
    private final ResponseSpecification response = expect()
            .body("assignee.login", notNullValue())
            .body("title", notNullValue());

    public static CustomSpec spec() {
        return new CustomSpec();
    }

    public RequestSpecification request() {
        return request;
    }

    public ResponseSpecification expectedResponse() {
        return response;
    }
}
