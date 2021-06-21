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

import com.qa.quiz.domain.Question;
import com.qa.quiz.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {
	
	private QuestionService service;
	
	public QuestionController(QuestionService service) {
		super();
		this.service = service;
	}

	@PostMapping("/create")
	public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
		return new ResponseEntity<Question>(this.service.createQuestion(question), HttpStatus.CREATED);
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Question>> getQuestion() {
		return ResponseEntity.ok(this.service.getQuestion());
	}

	@GetMapping("/getQuestion/{id}")
	public Question getQuestionById(@PathVariable Long id) {
		return this.service.getQuestionById(id);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> removeQuestion(@PathVariable Long id) {
		return this.service.removeQuestion(id) ? ResponseEntity.ok(true): new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("/update/{id}")
	public Question Question(@PathVariable Long id, @RequestBody Question question) {
		return this.service.updateQuestion(id, question);
	}
	
}
		

