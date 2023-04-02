package com.example.demo.common;

import com.example.demo.DemoApplication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@Transactional
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(classes = DemoApplication.class)
public class BaseIT {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    protected String asJson(final Object object) throws JsonProcessingException{
        return objectMapper.writeValueAsString(object);
    }

    protected <T> T asObject(ResultActions resultActions, Class<T> clazz) throws JsonProcessingException, UnsupportedEncodingException{
        String contentAsString = resultActions.andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        return objectMapper.readValue(contentAsString, clazz);
    }


}
