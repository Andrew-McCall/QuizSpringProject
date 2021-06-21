package com.qa.quiz.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.quiz.domain.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long>{
	
}
