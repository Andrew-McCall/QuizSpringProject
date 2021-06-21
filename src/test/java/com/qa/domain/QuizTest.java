package com.qa.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import com.qa.quiz.SQPApplication;
import com.qa.quiz.domain.Question;
import com.qa.quiz.domain.Quiz;

@SpringBootTest(classes = SQPApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("testing")
public class QuizTest {
	
	@Test
	public void QuizEqualHashTest() {
		Quiz quiz1 = new Quiz(1L,"Changed Name Value", "Changed Desc Value", Arrays.asList(new Question(1L, new Quiz(), "Example Q", "Example A")));
		Quiz quiz2 = new Quiz(1L,"Changed Name Value", "Changed Desc Value", Arrays.asList(new Question(1L, new Quiz(), "Example Q", "Example A")));
	    assertTrue(quiz1.equals(quiz2) && quiz2.equals(quiz1));
	    assertTrue(quiz1.hashCode() == quiz2.hashCode());
	}
	
	@Test
	public void lombok() {
		Quiz quiz1 = new Quiz(1L,"Changed Name Value", "Changed Desc Value", Arrays.asList(new Question(1L, new Quiz(), "Example Q", "Example A")));
		Quiz quiz2 = new Quiz();
		
		quiz2.setId(quiz1.getId());
		quiz2.setName(quiz1.getName());
		quiz2.setDescription(quiz1.getDescription());
		quiz2.setQuestions(quiz1.getQuestions());
		
		assertEquals(quiz1, quiz2);
	}
	
}
