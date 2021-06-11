package com.qa.quiz.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.quiz.domain.Quiz;

@Repository
public interface QuestionRepo extends JpaRepository<Quiz, Long>{
	
}
