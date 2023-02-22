package se.lexicon.kerry.booklender_rest_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
//is a Spring annotation that configures the MockMvc instance for use in testing
@AutoConfigureMockMvc
@Transactional
public class LibraryUserControllerTest {

    //to simulate HTTP requests to your REST API and verify the responses.
    @Autowired
    private MockMvc mockMvc;

    //convert Java objects to and from JSON  format.
    ObjectMapper objectMapper;

    @BeforeEach
    public void setup() throws Exception {
        String requestBody1 = "{\"name\": \"kerry\"}";

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/library/")
                .content(requestBody1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("kerry"));


        String requestBody2 = "{\"name\": \"sandra\"}";

        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/library/")
                .content(requestBody2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("name").value("sandra"));

                objectMapper = new ObjectMapper();
                objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
                objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
                objectMapper.registerModule(new JavaTimeModule());

    }


    @Test
    public void test_1(){

    }
}
