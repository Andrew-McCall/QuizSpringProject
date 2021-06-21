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

import com.qa.quiz.SQPApplication;
import com.qa.quiz.domain.Question;
import com.qa.quiz.domain.Quiz;
import com.qa.quiz.repo.QuestionRepo;
import com.qa.quiz.service.QuestionService;

@SpringBootTest(classes = SQPApplication.class)
@ActiveProfiles("testing")
public class QuestionUnitTest {

	@Autowired
	private QuestionService service;

	@MockBean
	private QuestionRepo repo;
	
	@Test
	void questionCreate() {
		Question question = new Question(new Quiz("Name","Description"), "Question", "Answer");
		
		Question returnedQuestion = new Question(1L, new Quiz("Name","Description"), "Question", "Answer");
		
		Mockito.when(this.repo.save(question)).thenReturn(returnedQuestion);
		
		assertEquals(this.service.createQuestion(question), returnedQuestion);

		Mockito.verify(this.repo, Mockito.times(1)).save(question);

	}
	
	@Test
	void questionUpdate() {

		
		Long id = 1L;
		Question question = new Question(new Quiz("Name","Description"), "Question", "Answer");
		
		Question updatedQuestion = new Question(1L, question.getQuiz(), question.getQuestion(), question.getAnswer());
		
		Optional<Question> optionalQuestion = Optional.of(question);
		
		Mockito.when(this.repo.findById(id)).thenReturn(optionalQuestion);
		
		Mockito.when(this.repo.save(question)).thenReturn(question);

		assertEquals(this.service.updateQuestion(id, question), updatedQuestion);

		Mockito.verify(this.repo, Mockito.times(1)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(question);
		
	}

	@Test
	void questionDelete() {
		Long id = 1L;

		Mockito.when(this.repo.existsById(id)).thenReturn(false);
		
		assertTrue(this.service.removeQuestion(id));

		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
		
		id = -1L;

		Mockito.when(this.repo.existsById(id)).thenReturn(true);
		
		assertTrue(!this.service.removeQuestion(id));

		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
	}
	
}
