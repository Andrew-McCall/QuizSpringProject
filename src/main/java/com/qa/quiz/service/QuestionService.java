package com.qa.quiz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.quiz.domain.Question;
import com.qa.quiz.repo.QuestionRepo;

@Service
public class QuestionService {

	private QuestionRepo repo;

	public QuestionService(QuestionRepo repo) {
		super();
		this.repo = repo;
	}

	public Question createQuestion(Question question) { 
		return this.repo.save(question);
	}

	public List<Question> getQuestion() {
		return this.repo.findAll();
	}

	public Question getQuestionzById(Long id) {
		Optional<Question> question = this.repo.findById(id);
		return question.get();
	}

	public boolean removeQuestion(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

	public Question updateQuestion(Long id, Question question) {
		Optional<Question> optionalQuestion = this.repo.findById(id);
		Question existing = optionalQuestion.get();

		existing.setId(id);
		existing.setQuestion(question.getQuestion());
		existing.setAnswer(question.getAnswer());
		
		return this.repo.save(existing);
	}

}

