package com.qa.quiz.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.quiz.domain.Quiz;
import com.qa.quiz.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {
	
	private QuizService service;
	
	public QuizController(QuizService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<Quiz> createQuiz(@RequestBody Quiz quiz) {
		return new ResponseEntity<Quiz>(this.service.createQuiz(quiz), HttpStatus.CREATED);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Quiz>> getQuiz() {
		return ResponseEntity.ok(this.service.getQuiz());
	}

	@GetMapping("/getQuiz/{id}")
	public Quiz getQuizById(@PathVariable Long id) {
		return this.service.getQuizById(id);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> removeQuiz(@PathVariable Long id) {
		return this.service.removeQuiz(id) ? ResponseEntity.ok(true): new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("/update/{id}")
	public Quiz Quiz(@PathVariable Long id, @RequestBody Quiz quiz) {
		return this.service.updateQuiz(id, quiz);
	}
	
}
		

