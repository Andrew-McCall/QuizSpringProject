package com.qa.controllers;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.quiz.SQPApplication;
import com.qa.quiz.domain.Question;
import com.qa.quiz.domain.Quiz;

@SpringBootTest(classes = SQPApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:test-schema.sql", "classpath:test-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("testing")
class QuizControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void CreateQuizTest() throws Exception {
		Quiz quiz = new Quiz("Starting Name Value", "Starting Desc Value");
		Quiz created = quiz;
		created.setId(2L);
		
		String JSONQuiz = this.mapper.writeValueAsString(quiz);
		String JSONCreatedQuiz = this.mapper.writeValueAsString(created);

		
		RequestBuilder mockRequest = post("/quiz/create").contentType(MediaType.APPLICATION_JSON).content(JSONQuiz);

		ResultMatcher checkStatus = status().isCreated();

		ResultMatcher checkBody = content().string(JSONCreatedQuiz);

		this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void UpdateQuizTest() throws Exception {
		Quiz quiz = new Quiz(1L,"Changed Name Value", "Changed Desc Value", Arrays.asList(new Question(1L, new Quiz(), "Example Q", "Example A")));

		String JSONQuiz = this.mapper.writeValueAsString(quiz);

		RequestBuilder mockRequest = put("/quiz/update/1").contentType(MediaType.APPLICATION_JSON).content(JSONQuiz);

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkBody = content().json(JSONQuiz);

		this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void GetAllQuizTest() throws Exception {
		Quiz quiz = new Quiz(1L,"Starting Name Value", "Starting Desc Value", Arrays.asList(new Question(1L, new Quiz(), "Example Q", "Example A")));
		List<Quiz> quizzes = new ArrayList<>();
		quizzes.add(quiz);
		
		String JSONQuizzes = this.mapper.writeValueAsString(quizzes);

		RequestBuilder mockRequest = get("/quiz/getAll");

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkBody = content().string(JSONQuizzes);

		this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void GetQuizByIdTest() throws Exception {
		Quiz quiz = new Quiz(1L,"Starting Name Value", "Starting Desc Value", Arrays.asList(new Question(1L, new Quiz(), "Example Q", "Example A")));
		
		String JSONQuiz = this.mapper.writeValueAsString(quiz);

		
		RequestBuilder mockRequest = get("/quiz/getQuiz/1");

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkBody = content().string(JSONQuiz);

		this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void DeleteQuizTest() throws Exception {
		RequestBuilder mockRequest = delete("/quiz/delete/1");

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher test = content().string("true");
		
		this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(test);
	}

}