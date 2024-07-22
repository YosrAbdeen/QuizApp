package com.quizzapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.quizapp.model.QuestionWrapper;
import com.quizapp.model.Response;
import com.quizapp.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
	QuizService quizService;
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numOfQuestions, @RequestParam String title ){
		return quizService.createQuiz(category,numOfQuestions,title );
	}
	
	 @GetMapping("get/{id}")
	    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
	        return quizService.getQuizQuestions(id);
	    }

	 @PostMapping("submit/{id}")
	    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
	        return quizService.calculateResult(id, responses);
	    }

}
