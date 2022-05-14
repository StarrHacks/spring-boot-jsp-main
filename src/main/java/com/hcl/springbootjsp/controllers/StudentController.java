package com.hcl.springbootjsp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.hcl.springbootjsp.model.Student;

@Controller
@RequestMapping("/student")
public class StudentController {
//	private List<StudentData> initData = new ArrayList<>();

    /*private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }*/
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public StudentController() {
		//old way
//	    initData.add( new StudentData("ISBN-1", "Student 1", "Student 1 Author"));
//	    initData.add(new StudentData("ISBN-2", "Student 2", "Student 2 Author"));
//	    initData.add(new StudentData("ISBN-3", "Student 3", "Student 3 Author"));
	}

	//To render the form for the student
    @GetMapping("/addStudent")
    public String addStudentView(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    //This one was called when you POST from a form tag
    @PostMapping("/addStudent")
    @Transactional
    public RedirectView addStudent(@ModelAttribute("student") Student student, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/student/addStudent", true);
        /*Student savedStudent = studentService.addStudent(student);*/
//        initData.add(student);
        
		entityManager.persist(student);
        redirectAttributes.addFlashAttribute("savedStudent", student);
        redirectAttributes.addFlashAttribute("addStudentSuccess", true);
        return redirectView;
    }
 
    @GetMapping("/updateStudent/{id}/{name}")
   	@Transactional
   	@ResponseBody
   	@Modifying
   	public void updateStudent(@PathVariable int id, @PathVariable String name) {
       	//using jql
//   		Query update = entityManager.createQuery("update from Student s set firstName =?1 where s.id=?0"); 
//   		update.setParameter(0, id);
//   		update.setParameter(1, firstName);
//   		update.executeUpdate();
       	//------------------------
       	System.out.println(id + " " + name);
       	//using entityManager
   		//Student updateS = entityManager.find(Student.class, id);
   		//(updateS.set).Name(Name);
   		Query update = entityManager.createQuery("update from Student s set name =?1 where s.id=?0");
   		update.setParameter(0, id);
   		update.setParameter(1, name);
   		update.executeUpdate();
}
	
	@GetMapping("/viewStudents")
    public String viewStudents(Model model) {
		Query readAll = entityManager.createQuery("select s from Student s");
		List<Student> resultListAll = readAll.getResultList();
		resultListAll.forEach(System.out::println);
        model.addAttribute("students", resultListAll);
        return "view-students";
    }
	
	@GetMapping("/updateStudent/{id}")
    public String updateStudent(Model m,@PathVariable int id) {
    	System.out.println("inside update student b4 anything happens");
        Student updateS = entityManager.find(Student.class, id);  
        m.addAttribute("student", updateS);
		System.out.println(updateS.getId() + " == " + id);
        return "update-student";
    }

    @PostMapping("/updateStudent/{id}")
    @Transactional
    @ResponseBody
    public RedirectView updateStudent(@ModelAttribute("student") Student student, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/student/viewStudents", true);
        entityManager.merge(student);
        return redirectView;
    }
	
	@GetMapping("/deleteStudent/{id}")
	@Transactional
	@ResponseBody
	public RedirectView deleteStudent(@PathVariable int id) {
        final RedirectView redirectView = new RedirectView("/student/viewStudents", true);
        Query delete = entityManager.createQuery("delete from Student s where s.id=?0");
        delete.setParameter(0, id);
        delete.executeUpdate();
        return redirectView;

    }
	
		@PostMapping("/viewStudents")
		
		public String viewStudents(@ModelAttribute("student") Student student, RedirectAttributes redirectAttributes, Model model) {
		
		
		final RedirectView redirectView = new RedirectView("/student/viewStudents", true);
		entityManager.persist(student);
		
		redirectAttributes.addFlashAttribute("deleteStudent", student);
		redirectAttributes.addFlashAttribute("deleteStudentSuccess", true);
		return "redirect:/student/viewStudents";
		
		}
		@GetMapping("/register")
	    public String registerNewUserView(Model model) {
	    	model.addAttribute("newUser", new NewUser());
	        return "register-new-user";
	    }
	    
	    
	    @Transactional
	    @PostMapping("/register")
	    public RedirectView registerNewUser(@ModelAttribute("user") NewUser newUser, RedirectAttributes redirectAttributes) {
	    	final RedirectView redirectView = new RedirectView("/student/register", true);
	    	
	    	entityManager.persist(newUser);
	    	
	    	redirectAttributes.addFlashAttribute("savedNewUser", newUser);
	    	redirectAttributes.addFlashAttribute("registerNewUserSuccess", true);
	    	return redirectView;
	    }
	    
		    
	    
	    @GetMapping("/login")
	    public String loginUserView(Model model) {
	    	model.addAttribute("newUser", new NewUser());
	        return "login-new-user";
	    }
	    
	    
	    @Transactional
	    @PostMapping("/login")
	    public RedirectView loginUser(@ModelAttribute("user") NewUser newUser, RedirectAttributes redirectAttributes) {
	    	final RedirectView redirectView = new RedirectView("/student/viewStudents", true);
	    	
	    	entityManager.persist(newUser);
	    	
	    	redirectAttributes.addFlashAttribute("savedNewUser", newUser);
	    	redirectAttributes.addFlashAttribute("registerNewUserSuccess", true);
	    	return redirectView;
	    }
}