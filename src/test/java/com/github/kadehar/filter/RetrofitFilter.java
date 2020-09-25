package com.github.kadehar.filter;

import io.qameta.allure.okhttp3.AllureOkHttp3;
import okhttp3.logging.HttpLoggingInterceptor;

public class RetrofitFilter {
    private static class Helper {
        private static final RetrofitFilter reporter = new RetrofitFilter();
    }

    private RetrofitFilter() {}

    /** Получение экземпляра класса. */
    public static RetrofitFilter reportingHelper() {
        return Helper.reporter;
    }

    /**
     * Получение кастомизированного allure-interceptor для сбора данных в отчет.
     * @return кастомизированный allure-interceptor
     */
    public AllureOkHttp3 getAllureOkHttp3() {
        final AllureOkHttp3 allureOkHttp3 = new AllureOkHttp3();
        allureOkHttp3.setRequestTemplate("request.ftl");
        allureOkHttp3.setResponseTemplate("response.ftl");
        return allureOkHttp3;
    }

    /**
     * Получение логирования HTTP запросов
     * @return http logger с уровнем логирования BODY
     */
    public HttpLoggingInterceptor getHttpLogger() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }
}
