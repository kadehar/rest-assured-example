package com.github.kadehar.api.retrofit.client;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static com.github.kadehar.filter.RetrofitFilter.reportingHelper;

public class RetrofitClient {
    private static class Client {
        private static final RetrofitClient client = new RetrofitClient();
    }

    private RetrofitClient() {}

    public static RetrofitClient client() {
        return Client.client;
    }

    /**
     * Создаёт новый клиент для контроллера с логированием и отчетом
     * @param baseUrl endpoint контроллера
     * @param token token запроса
     * @return новый клиент для отправки запросов
     */
    public Retrofit withToken(final String baseUrl, final String token) {
        OkHttpClient client = commonClient().addInterceptor(chain -> {
            final Request request = chain.request()
                    .newBuilder()
                    .header("Authorization",
                            "token " + token)
                    .build();
            return chain.proceed(request);
        }).build();

        return create(baseUrl, client);
    }

    private Retrofit create(final String baseURL, final OkHttpClient client) {
        return new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl(baseURL)
                .client(client)
                .build();
    }

    private OkHttpClient.Builder commonClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(reportingHelper().getAllureOkHttp3())
                .addInterceptor(reportingHelper().getHttpLogger());
    }
}
