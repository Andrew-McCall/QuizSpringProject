package com.qa.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import com.qa.quiz.SQPApplication;
import com.qa.quiz.domain.Question;
import com.qa.quiz.domain.Quiz;

@SpringBootTest(classes = SQPApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("testing")
public class QuestionTest {
	
	@Test
	public void QuestionEqualHashTest() {
		Question question1 = new Question(1L, new Quiz(), "Example Q", "Example A");
		Question question2 = new Question(1L, new Quiz(), "Example Q", "Example A");
	    assertTrue(question1.equals(question2) && question2.equals(question1));
	    assertTrue(question1.hashCode() == question2.hashCode());
	}
	
	@Test
	public void lombok() {
		Question question1 = new Question();
		Question question2 = new Question(1L, new Quiz(), "Example Q", "Example A");
		
		question1.setQuestion(question2.getQuestion());
		question1.setAnswer(question2.getAnswer());
		question1.setId(question2.getId());
		question1.setQuiz(question2.getQuiz());
		
		assertEquals(question1, question2);
	}
	
}
