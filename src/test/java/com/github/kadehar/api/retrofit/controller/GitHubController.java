package com.github.kadehar.api.retrofit.controller;

import com.github.kadehar.api.model.GitHubBody;
import com.github.kadehar.api.model.Issue;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface GitHubController {
    @GET("repos/kadehar/qa_guru_course_homework/issues/{issue}")
    Call<Issue> getIssue(@Path("issue") final int issue);

    @GET("repos/kadehar/qa_guru_course_homework/issues")
    Call<List<Issue>> getIssues();

    @POST("repos/kadehar/qa_guru_course_homework/issues")
    Call<Issue> createNewIssue(@Body final GitHubBody body);
}
