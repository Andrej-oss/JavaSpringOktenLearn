package org.okten.javaadvanced.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.okten.javaadvanced.entity.Director;
import org.okten.javaadvanced.service.impl.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@WebMvcTest(DirectorController.class)

public class DirectorControllerTest {
    @MockBean
    private DirectorService directorService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenDirectorIdWhenTakingDirectorThenReturnSuccessResponse() throws Exception{
        BDDMockito.given(directorService.getDirectorByName(ArgumentMatchers.anyString()))
                .willReturn(new Director(1, "Kubrick", null, null));

        mockMvc.perform(MockMvcRequestBuilders.get("/directors/Kubrick"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Kubrick"));
    }
    @Test
    public void givenDirectorBodyWhenInsertingThenReturnSuccessResponse() throws Exception{
        BDDMockito.given(directorService.insertDirector(ArgumentMatchers.any(Director.class)))
                .willReturn(new Director(1, "Kubrick", null, null));

        mockMvc.perform(MockMvcRequestBuilders.post("/directors")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Kubrick\",\n" +
                "    \"birthDate\": \"1947-03-03\"\n" +
                "    }"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Kubrick"));
    }
}
