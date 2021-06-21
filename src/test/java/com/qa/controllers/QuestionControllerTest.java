package com.qa.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.quiz.SQPApplication;
import com.qa.quiz.domain.Question;
import com.qa.quiz.domain.Quiz;

@SpringBootTest(classes = SQPApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:test-schema.sql", "classpath:test-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles("testing")
class QuestionControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void CreateQuestionTest() throws Exception {
		Question question = new Question(new Quiz(), "Changed Q", "Changed A");
		Question created = new Question(2L, new Quiz(), "Changed Q", "Changed A");;
		
		String JSONQuestion = this.mapper.writeValueAsString(question);
		String JSONCreatedQuestion = this.mapper.writeValueAsString(created);

		
		RequestBuilder mockRequest = post("/question/create").contentType(MediaType.APPLICATION_JSON).content(JSONQuestion);

		ResultMatcher checkStatus = status().isCreated();

		ResultMatcher checkBody = content().string(JSONCreatedQuestion);

		this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void UpdateQuestionTest() throws Exception {
		Question question = new Question(1L, new Quiz(), "Changed Q", "Changed A");

		String JSONQuestion = this.mapper.writeValueAsString(question);

		RequestBuilder mockRequest = put("/question/update/1").contentType(MediaType.APPLICATION_JSON).content(JSONQuestion);

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkBody = content().json(JSONQuestion);

		this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void GetAllQuestionTest() throws Exception {
		Question question = new Question(1L, new Quiz(), "Example Q", "Example A");
		List<Question> questions = new ArrayList<>();
		questions.add(question);
		
		String JSONQuestion = this.mapper.writeValueAsString(questions);

		RequestBuilder mockRequest = get("/question/getAll");

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkBody = content().string(JSONQuestion);

		this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void GetQuestionByIdTest() throws Exception {
		Question question = new Question(1L, new Quiz(), "Example Q", "Example A");
		
		String JSONQuestion = this.mapper.writeValueAsString(question);

		
		RequestBuilder mockRequest = get("/question/getQuestion/1");

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher checkBody = content().string(JSONQuestion);

		this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void DeleteQuestionTest() throws Exception {
		RequestBuilder mockRequest = delete("/question/delete/1");

		ResultMatcher checkStatus = status().isOk();

		ResultMatcher test = content().string("true");
		
		this.mvc.perform(mockRequest).andExpect(checkStatus).andExpect(test);
	}

}