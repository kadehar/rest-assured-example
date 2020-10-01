package com.github.kadehar.api.retrofit.client;

import com.github.kadehar.api.retrofit.controller.GitHubController;
import com.github.kadehar.api.model.Issue;
import com.github.kadehar.config.ConfigHelper;

import java.io.IOException;
import java.util.List;

public class GitHubClient {
    private final GitHubController controller;

    public GitHubClient(final String url) {
        controller = RetrofitClient.client().withToken(url, ConfigHelper.getToken())
                .create(GitHubController.class);
    }

    public Issue getIssueBy(final int issueNumber) {
        try {
            return controller.getIssue(issueNumber).execute().body();
        } catch (IOException e) {
            return null;
        }
    }

    public List<Issue> getIssues() {
        try {
            return controller.getIssues().execute().body();
        } catch (IOException e) {
            return null;
        }
    }
}
