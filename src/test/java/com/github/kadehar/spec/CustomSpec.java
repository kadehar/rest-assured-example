package com.github.kadehar.spec;

import com.github.kadehar.config.ConfigHelper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.github.kadehar.filter.LogFilter.filters;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Предоставляет шаблоны для запроса и ответа, которые могут быть использованы
 * для однотипных запросов.
 */
public class CustomSpec {
    /**
     * При получении ответа автоматически проверяет что поля assignee.login и title
     * не null.
     */
    private final ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectBody("assignee.login", is(notNullValue()))
            .expectBody("title", is(notNullValue()))
            .build();

    /**
     * При отправке запроса добавляет к запросу базовый URL, базовый путь,
     * header с токеном и фильтры по заданным шаблонам.
     */
    private final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri(ConfigHelper.getBaseURL())
            .setBasePath(ConfigHelper.getBasePath())
            .addHeader("Authorization", String.format("token %s", ConfigHelper.getToken()))
            .addFilter(filters().withCustomTemplates())
            .build();

    private static class Initializer {
        private static final CustomSpec spec = new CustomSpec();
    }

    private CustomSpec() {}

    public static CustomSpec spec() {
        return Initializer.spec;
    }

    public ResponseSpecification response() {
        return responseSpec;
    }

    public RequestSpecification request() {
        return requestSpec;
    }
}
