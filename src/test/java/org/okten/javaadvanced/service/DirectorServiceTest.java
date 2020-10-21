package org.okten.javaadvanced.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.okten.javaadvanced.dao.DirectorDAO;
import org.okten.javaadvanced.entity.Director;
import org.okten.javaadvanced.service.impl.DirectorService;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class DirectorServiceTest {

    @Mock
    private DirectorDAO directorDAO;

    @InjectMocks
    private DirectorService directorService;

    @Test
    public void givenDirectorIdWhenTakingDirectorThenReturnSuccessResponse() throws Exception{
        Director director = new Director(1, "Kubrick", LocalDate.of(1945, 12, 22), Collections.emptyList());
        Mockito.when(directorDAO.getOne(1)).thenReturn(director);
        Director actualDirector = directorService.getDirector(1);
        Assertions.assertEquals(director.getId(), actualDirector.getId());
    }
    @Test
    public void givenDirectorRequestBodyWhenInsertingThenReturnSuccessfulResponse(){
        Director director = new Director(1, "Kubrick", LocalDate.of(1945, 12, 22), Collections.emptyList());
        Mockito.when(directorDAO.save(director)).thenReturn(director);
        final Director actualDirector = directorService.insertDirector(director);
        Assertions.assertEquals(director.getName(), actualDirector.getName());
    }
    @Test
    public void givenDirectorNameWhenTakingDirectorThenReturnSuccessfulResponse(){
        Director director = new Director(1, "Kubrick", LocalDate.of(1945, 12, 22), Collections.emptyList());
        Mockito.when(directorDAO.findDirectorByName("Kubrick")).thenReturn(director);
        Director kubrick = directorService.getDirectorByName("Kubrick");
        Assertions.assertEquals(director.getName(), kubrick.getName());
    }
}
