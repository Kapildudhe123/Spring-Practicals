package com.rays.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMultiple {
	
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("Multiple.xml");

		Emp emp = (Emp) context.getBean("Emp"); 

		Person person = (Person) context.getBean("Person"); 
		
		System.out.println("Emp name:"+emp.getName());
		System.out.println("Emp lastName:"+emp.getLastName());
		System.out.println("Emp address:"+emp.getAddress());
		
		System.out.println("person name:"+person.getName());
		System.out.println("person city:"+person.getCity());
	}

}
