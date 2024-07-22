package com.quizzapp.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizapp.model.Questions;
import com.quizapp.service.QuestionService;

@RestController
@RequestMapping ("/questions")
public class questionController {
	
	@Autowired
	QuestionService questionService;
	
	
	@GetMapping("/allQuestions")
public ResponseEntity<List<Questions>> getAllQuestions() {
	return questionService.getAllQuestions();
	
}
	@GetMapping("/category/{category}")
	public ResponseEntity<List<Questions>> getAllQuestionsByCategory(String category){
		return questionService.getAllQuestionsByCategory(category);
	}

	@PostMapping("add")
	public ResponseEntity<String> addQuestion(@RequestBody Questions question) {
	return questionService.addQuestion(question);}
}
