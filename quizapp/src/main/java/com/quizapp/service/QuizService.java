package com.quizapp.service;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quizapp.dao.QuestionDao;
import com.quizapp.dao.QuizDao;
import com.quizapp.model.QuestionWrapper;
import com.quizapp.model.Questions;
import com.quizapp.model.Quiz;
import com.quizapp.model.Response;

@Service
public class QuizService {

	@Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;



    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
try {
        List<Questions> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    } catch (Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>("Failure", HttpStatus.BAD_REQUEST);

    }
    }



	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		
		 Optional<Quiz> quiz = quizDao.findById(id);
	        List<Questions> questionsFromDB = quiz.get().getQuestions();
	        List<QuestionWrapper> questionsForUser = new ArrayList<>();
	        for(Questions q : questionsFromDB){
	            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
	            questionsForUser.add(qw);
	        }

	        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);

	}
	  public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
	        Quiz quiz = quizDao.findById(id).get();
	        List<Questions> questions = quiz.getQuestions();
	        int right = 0;
	        int i = 0;
	        for(Response response : responses){
	            if(response.getResponse().equals(questions.get(i).getRightAnswer()))
	                right++;

	            i++;
	        }
	        return new ResponseEntity<>(right, HttpStatus.OK);
	    }
}

