package com.github.kadehar.api.retrofit.controller;

import com.github.kadehar.api.model.Issue;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubController {
    @GET("repos/kadehar/qa_guru_course_homework/issues/{issue}")
    Call<Issue> get(@Path("issue") final int issue);
}
