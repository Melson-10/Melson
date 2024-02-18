package com.gl.controller;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.Employee;

@Controller
public class Employeecontroller {
	@RequestMapping("/")
	public String welcome() {
		return "welcome";//welcome.html file 
	}
	@RequestMapping("/Employee_page")//url
	public String empolyeepage(Model data) {
			SessionFactory factory=new Configuration().configure().buildSessionFactory();
	Session session = factory.openSession();
	try {
		
		Query q1= session.createQuery("from Employee");
		List<Employee> emp=q1.getResultList();
		data.addAttribute("Employees",emp);
		
	}
	catch(Exception ex){
		System.out.println("Error :"+ex.getMessage());
}
		
		return "Employee_page";//Employee_page.html file 
	}
	@RequestMapping("/add_form")
	public String add() {
		return "add_page";// add_page.html file and create a form 
	}
	
	@PostMapping("/add_insert")
	public String show( @RequestParam String employeeName,@RequestParam String employeeAddress,@RequestParam int employeePhone,@RequestParam int employeeSalary ,Model data) {
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		try {
		Transaction tx=session.beginTransaction();
		Employee e1=new Employee(employeeName,employeeAddress,employeePhone,employeeSalary);
		session.save(e1);
		
		Query q1= session.createQuery("from Employee");
		List<Employee> emp=q1.getResultList();
		data.addAttribute("Employees",emp);
		
		
		tx.commit();
		}
		catch(Exception ex) {
			System.out.println("Error :" + ex.getMessage());
		}
		return "Employee_page";
	}
	@GetMapping("/update_page")
	public String update(@RequestParam int id,Model data) {
		SessionFactory factory =new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		try {
			Employee updateemployee=session.get(Employee.class,id);
			data.addAttribute("Employees",updateemployee);

		}
		catch(Exception ex){
			System.out.println("Error :"+ex.getMessage());
			
		}
		return "update_form";
	}
	@PostMapping("/update")
	public String updatepage(@RequestParam int id,@RequestParam String employeeName,@RequestParam String employeeAddress,@RequestParam int employeePhone,@RequestParam int employeeSalary ,Model data) {
		SessionFactory factory =new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		try {
			Transaction tx=session.beginTransaction();
			Employee e2 =new Employee(id,employeeName,employeeAddress,employeePhone,employeeSalary);
			session.update(e2);
			Query q1= session.createQuery("from Employee");
			List<Employee> emp=q1.getResultList();
			data.addAttribute("Employees",emp);
			
			tx.commit();
		}
		catch(Exception ex) {
			System.out.println("Error :"+ex.getMessage());
		}
		return "Employee_page";
	}
	@GetMapping("/delete_page")
	public String Delete(@RequestParam int id,Model data) {
		SessionFactory factory =new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		try {
			Transaction tx=session.beginTransaction();
			Employee e3=new Employee(id,"","",0,0);
			session.delete(e3);
			Query q1= session.createQuery("from Employee");
			List<Employee> emp=q1.getResultList();
			data.addAttribute("Employees",emp);
			
			tx.commit();
		}catch(Exception ex) {
			System.out.println("Error :"+ex.getMessage());
		}
		return "Employee_page";
	}
}

	


