package com.qa.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.qa.quiz.SQPApplication;
import com.qa.quiz.domain.Quiz;
import com.qa.quiz.repo.QuizRepo;
import com.qa.quiz.service.QuizService;

@SpringBootTest(classes = SQPApplication.class)
@ActiveProfiles("testing")
public class QuizUnitTest {

	@Autowired
	private QuizService service;

	@MockBean
	private QuizRepo repo;
	
	@Test
	void quizCreate() {
		Quiz quiz = new Quiz("Name", "Desc");
		Quiz returnedQuiz= new Quiz(2L, "Name", "Desc", null);
		
		Mockito.when(this.repo.save(quiz)).thenReturn(returnedQuiz);

		assertEquals(this.service.createQuiz(quiz), returnedQuiz);

		Mockito.verify(this.repo, Mockito.times(1)).save(quiz);
	}
	
	@Test
	void quizUpdate() {
		Long id = 1L;
		
		Quiz quiz = new Quiz("Test Name", "Test Description");
		
		Quiz updatedQuiz = new Quiz(id, quiz.getName(), quiz.getDescription(), quiz.getQuestions());

		Optional<Quiz> optionalQuiz = Optional.of(quiz);
		
		Mockito.when(this.repo.findById(id)).thenReturn(optionalQuiz);
		
		Mockito.when(this.repo.save(quiz)).thenReturn(quiz);

		assertEquals(this.service.updateQuiz(id, quiz), updatedQuiz);

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(quiz);
	}
	
	@Test
	void quizDelete() {
		Long id = 1L;

		Mockito.when(this.repo.existsById(id)).thenReturn(false);
		
		assertTrue(this.service.removeQuiz(id));

		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
		
		id = -1L;

		Mockito.when(this.repo.existsById(id)).thenReturn(true);
		
		assertTrue(!this.service.removeQuiz(id));

		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
	}
	
}
