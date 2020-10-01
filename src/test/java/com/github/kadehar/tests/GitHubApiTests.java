package com.github.kadehar.tests;

import com.github.kadehar.api.model.Issue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GitHubApiTests extends TestBase {
    @Test
    @DisplayName("Проверка ответа, используя модель без применения спецификаций")
    void validateIssueWithExtractableResponseWithoutSpec() {
        Issue issue = restAssured.extractIssueAfterRequestWithoutSpec(ISSUE);

        assertThat(issue.getNumber(), is(ISSUE));
        assertThat(issue.getAssignee().getLogin(), is(ASSIGNEE));
        assertThat(issue.getTitle(),is(TITLE));
        assertThat(issue.getLabels().get(0).getName(), is(BUG));
        assertThat(issue.getLabels().get(1).getName(), is(DUPLICATE));
    }

    @Test
    @DisplayName("Проверка ответа без использования модели и применения спецификации")
    void validateResponseWithoutSpec() {
        restAssured.validateResponseWithoutSpec(ISSUE, ASSIGNEE,
                TITLE, BUG, DUPLICATE);
    }

    @Test
    @DisplayName("Проверка ответа, используя модель с применением спецификации запроса")
    void validateIssueWithRequestSpecification() {
        Issue issue = restAssured.extractIssueAfterRequestWithSpec(ISSUE);

        assertThat(issue.getNumber(), is(ISSUE));
        assertThat(issue.getAssignee().getLogin(), is(ASSIGNEE));
        assertThat(issue.getTitle(),is(TITLE));
        assertThat(issue.getLabels().get(0).getName(), is(BUG));
        assertThat(issue.getLabels().get(1).getName(), is(DUPLICATE));
    }

    @Test
    @DisplayName("Проверка ответа, применяя спецификации запроса и ответа")
    void validateResponseWithRequestAndResponseSpecifications() {
        restAssured.validateResponseWithSpec(ISSUE, ASSIGNEE,
                TITLE, BUG, DUPLICATE);
    }

    @Test
    @DisplayName("Проверка ответа, используя мощь Groovy")
    void validateResponseWithGroovyPower() {
        restAssured.validateResponseUsingGroovyPower(ISSUE, ASSIGNEE,
                TITLE, BUG, DUPLICATE);
    }

    @Test
    @DisplayName("Проверка ответа, используя клиент Retrofit")
    void validateIssueWithRetrofitClient() {
        Issue issue = retrofit.getIssueUsingRetrofit(ISSUE);

        assertThat(issue.getNumber(), is(ISSUE));
        assertThat(issue.getAssignee().getLogin(), is(ASSIGNEE));
        assertThat(issue.getTitle(),is(TITLE));
        assertThat(issue.getLabels().get(0).getName(), is(BUG));
        assertThat(issue.getLabels().get(1).getName(), is(DUPLICATE));
    }

    @Test
    @DisplayName("Проверка ответа, используя клиент Retrofit и Java 8")
    void validateIssueWithRetrofitClientAndJava8() {
        Issue issue = retrofit.getIssueUsingJava8(ISSUE);

        assertThat(issue.getNumber(), is(ISSUE));
        assertThat(issue.getAssignee().getLogin(), is(ASSIGNEE));
        assertThat(issue.getTitle(),is(TITLE));
        assertThat(issue.getLabels().get(0).getName(), is(BUG));
        assertThat(issue.getLabels().get(1).getName(), is(DUPLICATE));
    }

    @Test
    void validateThatIssueIsCreated() {
        Issue issue = retrofit.createNewIssue();

        assertThat(issue.getLabels().get(0).getName(), is(BUG));
    }
}
