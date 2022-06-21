package com.anil.example.springboot.service;


import javax.validation.Valid;

import com.anil.example.springboot.entity.Student;

import java.util.List;

public interface StudentService {


	
	List<Student> getAllStudent();

	Student addStudent(Student student);

	void deleteStudentById(Student student);

	Student getStudent(Long id);

	
	Object findAll();

	
	void save(@Valid Student student);

	Student findById(long id);

	List<Student> updateStudentDetails(Student student);

	void deleteById(Student student);

	void delete(Student student);

	void deleteStudentById(long id);

	Student updateStudent(Student student);

	void updateStudent(long id, Student student);

	

	
	
	

}
