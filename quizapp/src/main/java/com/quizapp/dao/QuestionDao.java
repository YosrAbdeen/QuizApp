package com.quizapp.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.quizapp.model.Questions;

public interface QuestionDao extends JpaRepository<Questions, Integer>{

	public default List<Questions> findAll() {
		
		return null;
	}
	
	List<Questions> findByCategory(String category);
	
	 @Query(value = "SELECT * FROM question q Where q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
	    List<Questions> findRandomQuestionsByCategory(String category, int numQ);

}
