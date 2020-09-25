package com.github.kadehar.api.retrofit.client;

import com.github.kadehar.api.model.Issue;
import com.github.kadehar.api.retrofit.controller.GitHubController;
import com.github.kadehar.config.ConfigHelper;

import java.io.IOException;

import static com.github.kadehar.api.retrofit.client.RetrofitClient.client;

public class GitHubClient {
    private final GitHubController controller;

    public GitHubClient(final String url) {
        controller = client().withToken(url, ConfigHelper.getToken())
                .create(GitHubController.class);
    }

    public Issue getIssueByNumber(final int issueNumber) {
        try {
            return controller.get(issueNumber).execute().body();
        } catch (IOException e) {
            return null;
        }
    }
}
