package com.github.kadehar.api.retrofit.steps;

import com.github.kadehar.api.model.GitHubBody;
import com.github.kadehar.api.retrofit.client.GitHubClient;
import com.github.kadehar.api.model.Issue;
import com.github.kadehar.config.ConfigHelper;

import java.util.ArrayList;
import java.util.List;

public class RetrofitSteps {
    private final GitHubClient client = new GitHubClient(ConfigHelper.getBaseURL());

    public Issue getIssueUsingRetrofit(final int issue) {
        return client.getIssueBy(issue);
    }

    public Issue getIssueUsingJava8(final int issue) {
        return client.getIssues().stream().filter(iss -> issue == iss.getNumber()).findFirst().get();
    }

    public Issue createNewIssue() {
        GitHubBody body = new GitHubBody();
        List<String> assignees = new ArrayList<>();
        assignees.add("kadehar");

        List<String> labels = new ArrayList<>();
        labels.add("bug");

        body.setTitle("Found a bug");
        body.setBody("I'm having a problem with this.");
        body.setAssignees(assignees);
        body.setLabels(labels);

//        String body = "{\n" +
//                "  \"title\": \"Found a bug\",\n" +
//                "  \"body\": \"I'm having a problem with this.\",\n" +
//                "  \"assignees\": [\n" +
//                "    \"kadehar\"\n" +
//                "  ],\n" +
//                "  \"milestone\": 1,\n" +
//                "  \"labels\": [\n" +
//                "    \"bug\"\n" +
//                "  ]\n" +
//                "}";

        return client.createNewIssue(body);
    }
}
