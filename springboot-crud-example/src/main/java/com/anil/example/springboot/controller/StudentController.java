package com.anil.example.springboot.controller;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.anil.example.springboot.entity.Admin;
import com.anil.example.springboot.entity.Student;
import com.anil.example.springboot.repository.AdminRepository;
import com.anil.example.springboot.repository.StudentRepository;
import com.anil.example.springboot.service.StudentService;

@Controller
@RequestMapping("/students/")
public class StudentController {
	private final StudentRepository studentRepository;
	
	@Autowired
	StudentService studentService;
	
	@Autowired
	AdminRepository adminrepository;
	HttpSession session;
	
	
	
	public StudentController(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	
	@RequestMapping(path = "checkdetails",method = RequestMethod.POST)
	public String showSignUpForm(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
		
	
			Admin admindata = adminrepository.findByusername(username);
			if(admindata == null) {
				return "index";
			}
			System.out.println(admindata);
			if(admindata.getUsername().equals(username)&&admindata.getPassword().equals(password))
			{
				model.addAttribute("students", studentService.getAllStudent());
				return "student";
			}
			else {
				System.out.println("I am in error but coming till here "
						+ "the entered user name is "+username +"the entereted password is "
						+password);
				return "index";
			}
		
		}
	
	@GetMapping("checkdetails")
	public String showAdminForm() {
	
		return "admin";
	}
	
	@GetMapping("signup")
	public String showSignUpForm(Student student) {
		return "add-student";
	}

	@GetMapping("list")
	public String showUpdateForm(Model model) {
		model.addAttribute("students", studentService.getAllStudent());
		return "student";
	}
	@PostMapping("adminregistration")
	public String addAdmin(@Valid Admin admin, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "admin";
		}
		System.out.println("This is testing") ;
		adminrepository.save(admin);
		return "index";
	}

//	@GetMapping("/status")
//	public String interviewUpdateForm() {
//		return "interviewstatus";
//	}
	
	@PostMapping("add")
	public String addStudent(@Valid Student student, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-student";
		}
		System.out.println("This is testing") ;
		studentService.addStudent(student);
		return "redirect:list";
	}

	@GetMapping("edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
		model.addAttribute("student", student);
		return "update-student";
	}

	@PostMapping("update/{id}")
	public String updateStudent(@PathVariable("id") long id, @Valid Student student, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			student.setId(id);
			return "update-student";
		}

		studentService.addStudent(student);
		model.addAttribute("students", studentService.getAllStudent());
		return "student";
	}

	@GetMapping("delete/{id}")
	public String deleteStudent(@PathVariable("id") long id, Model model) {
				/* .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id)); */	
		System.out.println("This is inside delete");
		studentService.deleteStudentById(id);
		model.addAttribute("students", studentService.getAllStudent());
		return "student";
	} 
}