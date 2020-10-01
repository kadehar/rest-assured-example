package com.github.kadehar.api.retrofit.steps;

import com.github.kadehar.api.retrofit.client.GitHubClient;
import com.github.kadehar.api.model.Issue;
import com.github.kadehar.config.ConfigHelper;

public class RetrofitSteps {
    private final GitHubClient client = new GitHubClient(ConfigHelper.getBaseURL());

    public Issue getIssueUsingRetrofit(final int issue) {
        return client.getIssueBy(issue);
    }

    public Issue getIssueUsingJava8(final int issue) {
        return client.getIssues().stream().filter(iss -> issue == iss.getNumber()).findFirst().get();
    }
}
