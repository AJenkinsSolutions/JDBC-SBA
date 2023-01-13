package jpa.service;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

public class MenuService {
	
	
	
	public StudentService stdService =  new StudentService();
	public CourseService crsService = new CourseService();
	
	
	
	public  void runMenu(Session session) {
		
		Scanner consoleIn = new Scanner(System.in);
		int userInput = 0;
		boolean menuOn = true;
		

		
		
		
		while(menuOn) {
			System.out.println("\n====== Welcome to the Menu ======\n");
			System.out.println("1. Login");
			System.out.println("2. Exit\n");
			System.out.println("Please select 1 or 2");
			
			userInput = consoleIn.nextInt();
			
			
			switch (userInput) {
			case 1:
				System.out.println("Loggin in .....");
				//potienal redundant code 
				boolean isLoggedIn = false;
				
				//prompt user for email to login
				System.out.println("Please Enter Email");
				Scanner input = new Scanner(System.in);
				//try catch
				String emailInput = input.nextLine();
				
				//login
				if(login(session, stdService, emailInput)) {
					isLoggedIn = true;
					
					//=========Welcome=========//
					///get the current student
					Student student = stdService.getStudentByEmail(emailInput, session);
					//welcome message
					System.out.println("\n====== Welcome: "+ student.getsName()+ " ========\n");
					System.out.println("My Classes:\n");
					System.out.println("#  COURSE NAME  INSTRUCTOR NAME\n");
					//display all courses for that student
					stdService.getStudentCourses(student, session);
					
					
					//Phase 2
					System.out.println("\n======Select An Option====\n");
					System.out.println("1. Register to Class");
					System.out.println("2. Logout");
					
					int phaseTwoSelection = input.nextInt();
					
					switch (phaseTwoSelection) {
					case 1:
						//display all courses
						System.out.println("\nAll Courses:\n");
						System.out.println("#  COURSE NAME  INSTRUCTOR NAME\n");
						List <Course> crslist = crsService.getAllCourses(session);
						//prompt user
						System.out.println("\nPlease select a course");
						
						int courseId = consoleIn.nextInt();
						stdService.registerStudentToCourse(student, session, courseId, crslist);
						
						System.out.println("My Classes:\n");
						System.out.println("#  COURSE NAME  INSTRUCTOR NAME\n");
						//display all courses for that student
						stdService.getStudentCourses(student, session);
						
						break;
					case 2:
						menuOn =logout();
						break;
						
					default:
						break;
					}
				}
				
				
				break;
			
			case 2:
				menuOn = logout();
				break;
			
			default:
				System.out.println("Invalid input");
				break;
			}
		
		}

		
		
		
		
		
	}
	
	
	
	
	public boolean login(Session session, StudentService service, String emailInput){
		
		
		
		//validate email
		boolean isvald = stdService.validateStudent(session, emailInput);
		
		if(isvald) {
			System.out.println("!!!Successfull Logged in!!");
			return true;
		}else {
			System.out.println("Log in Failed");
			return false;
		}

		
	}
	
	public boolean logout() {
		System.out.println("Thank You Exiting...");
		return false;
	}
	

}
