package com.aihto;

import com.aihto.Repository.StudentsRepository;
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

//	@Override
//	public void run(String... args) throws Exception {
//		if(studentsRepository.findAll().isEmpty()) {
//			studentsRepository.save(new Students(0,"Ciccio"));
//		}
//		for (Students students : studentsRepository.findAll()) {
//			System.out.println("students");
//		}
//	}
}
  