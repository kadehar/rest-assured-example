package com.github.kadehar.api.retrofit.controller;

import com.github.kadehar.api.model.Issue;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface GitHubController {
    @GET("repos/kadehar/qa_guru_course_homework/issues/{issue}")
    Call<Issue> getIssue(@Path("issue") final int issue);

    @GET("repos/kadehar/qa_guru_course_homework/issues")
    Call<List<Issue>> getIssues();
}
