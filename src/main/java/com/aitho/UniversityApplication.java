package com.aitho;

import com.aitho.Repository.StudentsRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UniversityApplication  {

	private final StudentsRepository studentsRepository;

	public UniversityApplication(StudentsRepository studentsRepository) {
		this.studentsRepository = studentsRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(UniversityApplication.class, args);
	}

}
  