package com.qa.quiz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.quiz.domain.Quiz;
import com.qa.quiz.repo.QuizRepo;

@Service
public class QuizService {

	private QuizRepo repo;

	public QuizService(QuizRepo repo) {
		super();
		this.repo = repo;
	}

	public Quiz createQuiz(Quiz quiz) { 
		return this.repo.save(quiz);
	}

	public List<Quiz> getQuiz() {
		return this.repo.findAll();
	}

	public Quiz getQuizById(Long id) {
		Optional<Quiz> quiz = this.repo.findById(id);
		return quiz.get();
	}

	public boolean removeQuiz(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

	public Quiz updateQuiz(Long id, Quiz quiz) {
		Optional<Quiz> optionalQuiz = this.repo.findById(id);
		Quiz existing = optionalQuiz.get();

		existing.setId(id);
		existing.setName(quiz.getName());
		existing.setDescription(quiz.getDescription());
//		existing.setQuestions(quiz.getQuestions());
		
		return this.repo.save(existing);
	}

}

