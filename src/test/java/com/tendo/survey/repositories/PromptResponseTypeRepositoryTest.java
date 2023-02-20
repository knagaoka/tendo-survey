package com.tendo.survey.repositories;

import com.tendo.survey.models.dao.PromptResponseType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.List;

import static org.mockito.Mockito.doReturn;

public class PromptResponseTypeRepositoryTest {

    @Spy
    private PromptResponseTypeRepository repositoryMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        PromptResponseType prt1 = new PromptResponseType();
        PromptResponseType prt2 = new PromptResponseType();
        PromptResponseType prt3 = new PromptResponseType();
        prt1.setPromptId("1");
        prt2.setPromptId("1");
        prt3.setPromptId("2");
        doReturn(List.of(prt1, prt2, prt3)).when(repositoryMock).findAll();
    }

    @Test
    public void getPromptResponseTypeTest() {
        Assertions.assertNotNull(repositoryMock.getPromptResponseType("2"));
        Assertions.assertNull(repositoryMock.getPromptResponseType("unknown"));
    }
}
