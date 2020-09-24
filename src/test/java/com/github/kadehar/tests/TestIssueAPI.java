package com.github.kadehar.tests;

import com.github.kadehar.api.model.Issue;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestIssueAPI extends TestBase {
    @Test
    void checkIssueByStandardExtractableResponse() {
        Issue issue = apiSteps.standardRequestWithExtractableResponse(12);

        assertThat(issue.getTitle(),is(TITLE));
        assertThat(issue.getAssignee().getLogin(), is(ASSIGNEE));
        assertThat(issue.getLabels().get(0).getName(), is(BUG));
        assertThat(issue.getLabels().get(1).getName(), is(DUPLICATE));
    }

    @Test
    void checkIssueByExtractableResponse() {
        Issue issue = apiSteps.requestWithExtractableResponse(12);

        assertThat(issue.getTitle(),is(TITLE));
        assertThat(issue.getAssignee().getLogin(), is(ASSIGNEE));
        assertThat(issue.getLabels().get(0).getName(), is(BUG));
        assertThat(issue.getLabels().get(1).getName(), is(DUPLICATE));
    }

    @Test
    void checkIssueByStandardCheck() {
        apiSteps.standardCheckThatIssueIsCreated(12, ASSIGNEE,
                TITLE, BUG, DUPLICATE);
    }

    @Test
    void checkIssue() {
        apiSteps.checkThatIssueIsCreated(12, ASSIGNEE,
                TITLE, BUG, DUPLICATE);
    }
}
