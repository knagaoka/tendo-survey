package com.tendo.survey.repositories;

import com.tendo.survey.models.dao.Prompt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.List;

import static org.mockito.Mockito.doReturn;

public class PromptRepositoryTest {

    @Spy
    private PromptRepository repositoryMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        Prompt p1 = new Prompt();
        Prompt p2 = new Prompt();
        Prompt p3 = new Prompt();
        p1.setSurveyId("1");
        p2.setSurveyId("1");
        p3.setSurveyId("2");
        doReturn(List.of(p1, p2, p3)).when(repositoryMock).findAll();
    }

    @Test
    public void getPromptsTest() {
        List<Prompt> prompts = repositoryMock.getPrompts("1");
        Assertions.assertNotNull(prompts);
        Assertions.assertEquals(2, prompts.size());
    }
}
