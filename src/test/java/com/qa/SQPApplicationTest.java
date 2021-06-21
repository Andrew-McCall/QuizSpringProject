package com.qa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.qa.quiz.SQPApplication;

@SpringBootTest(classes = SQPApplication.class)
@ActiveProfiles("testing")
public class SQPApplicationTest {

	@Test
	void contextLoads() {
	}
	
	
}
