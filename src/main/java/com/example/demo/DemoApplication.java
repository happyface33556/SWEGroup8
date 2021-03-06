package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


	@Bean
	CommandLineRunner courseLoader(CourseRepo repo) {
		return args -> {
			repo.save(new Course("COT3100"));
			repo.save(new Course("CEN3031"));
		};
	}

	@Bean
	CommandLineRunner submissionLoader(UserRepo repo, CourseRepo courseRepo, SubmissionRepo submissionRepo) {
		return args -> {
			Course swe = new Course("CEN3031");
			HashSet<Course> s = new HashSet<Course>();
			s.add(swe);
			repo.save(new User((long) 1, "Sanethia", "Thomas", "admin", "password", s, User.Type.TEACHER));

			User eric = new User((long) 2, "Eric", "McGuirk", "ericSWE", "password", s, User.Type.STUDENT);
			User zach = new User((long) 3, "Zachary", "Deptula", "zachSWE", "password", s, User.Type.STUDENT);
			User stone = new User((long) 4, "Stone", "Blumauer", "stoneSWE", "password", s, User.Type.STUDENT);
			repo.save(eric);
			repo.save(zach);
			repo.save(stone);
			Course dsa = new Course("COP3503");
			courseRepo.save(dsa);

			Submission google = new Submission("www.google.com", "COP3503", "", "ericSWE", eric);
			Submission khanAcademy = new Submission("www.khanacademy.com", "CEN3031", "Useful for a variety of subjects", "ericSWE", eric);
			Submission youtube = new Submission("www.youtube.com", "COP3503", "Instructional Videos", "zachSWE", zach);
			Submission wikipedia = new Submission("www.wikipedia.com", "CEN3031", "Open-Source Encyclopedia", "stoneSWE", stone);
			submissionRepo.save(wikipedia);
			submissionRepo.save(youtube);
			submissionRepo.save(google);
			submissionRepo.save(khanAcademy);
		};
	}
}
