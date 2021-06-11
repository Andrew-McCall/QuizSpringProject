package com.qa.quiz.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.quiz.domain.Quiz;
import com.qa.quiz.service.QuizService;

@RestController
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

		
//		@GetMapping("/getCar/{id}")
//		public Car getPenguinById(@PathVariable Long id) {
//			return this.service.getCarById(id);
//		}
//
//		@GetMapping("/getCarByModel/{model}")
//		public Car getCarByModel(@PathVariable String model) {
//			return this.service.getCarByModel(model);
//		}
//		@GetMapping("/getCarByMake/{make}")
//		public Car getCarByMake(@PathVariable String make) {
//			return this.service.getCarByModel(make);
//		}
//		@GetMapping("/getCarByColour/{colour}")
//		public Car getCarByColour(@PathVariable String colour) {
//			return this.service.getCarByModel(colour);
//		}
//
//		@DeleteMapping("/delete/{id}")
//		public ResponseEntity<Boolean> removePenguin(@PathVariable Long id) {
//			return this.service.removeCar(id) ? ResponseEntity.ok(true): new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//
//		@PutMapping("/update/{id}")
//		public Car updatePenguin(@PathVariable Long id, @RequestBody Car car) {
//			return this.service.updateCar(id, car);
//		}
//	}
		
		
}
