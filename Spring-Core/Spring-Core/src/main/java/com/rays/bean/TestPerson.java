package com.rays.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestPerson {

	public static void main(String[] args) {
		
		ApplicationContext context=new ClassPathXmlApplicationContext("Person.xml");
		Person person=(Person) context.getBean("Person");
		System.out.println(person.getName());
		System.out.println(person.getCity());
		
	}
}
