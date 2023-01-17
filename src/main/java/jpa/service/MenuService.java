
package jpa.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder.Case;

import org.hibernate.Session;

import com.mysql.cj.x.protobuf.MysqlxExpect.Open.Condition.Key;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

public class MenuService {
	
	
	
	public StudentService stdService =  new StudentService();
	public CourseService crsService = new CourseService();
	
	
	
	public  void runMenu(Session session) {
		
		Scanner consoleIn = new Scanner(System.in);
		
		
		boolean menuOn = true;
		int userInput = 0;
		String emailInput = null;
		Student student = null;

		//Determines if we enter phase 2
		boolean phase1on = true;
		boolean phase2On = false;
		boolean phase3On = false;
		boolean phase4On = false;
		boolean phase5On = false;
		
		
		while(menuOn) {
			
			
			//==================Phase one=======================
			
			while(phase1on) {
				
				int p1UserInput = phase1();
				
				switch (p1UserInput) {
				case 1:
					System.out.println("DEBUG PHASE 1: login");
					phase2On = true;
					phase1on = false;
					break;

				default:
					System.out.println("DEBUG PHASE 1: logout");
					menuOn = logout();
					phase1on = false;
					break;
				}
			}
			
			
			
			
			//==================Phase Two=======================
			

			while(phase2On) {
				emailInput = phase2A();
				boolean p2UserInput = phase2(session, emailInput);
				
				//We need a match in order to continue
				if(p2UserInput) {
					System.out.println("Debug");
					phase3On = true;
					phase2On = false;
				
				}else {
					//maybe oprtion to exit
				}
				
				
			}
			
			
			//==================Phase Three=======================
			
			while(phase3On) {
				System.out.println("DEBUG: INSIDE PHASE 3");
				student = phase3A(session, emailInput);
				int p3UserInput = phase3(session, student);
				
				switch (p3UserInput) {
				case 1:
					System.out.println("DEBUG PHASE 3: Register");
					phase4On = true;
					phase3On = false;
					break;
				
				default:
					System.out.println("DEBUG PHASE 3: Logout");
					menuOn = logout();
					phase3On = false;
					break;
				}
				
				
			}
				
			//==================Phase Four=======================	
			
				while(phase4On) {
					System.out.println("DEBUG: INSIDE PHASE 4");
					phase4(session, student);
					phase4On = false;
					phase5On = true;
					
					
				}
				
				
				while(phase5On) {
					System.out.println("DEBUG: INSIDE PHASE 4");
					int p5UserInput = phase5();
					
					switch (p5UserInput) {
					case 1:
						System.out.println("1. Register Menu");
						phase3On = true;
						phase5On = false;
						break;
					case 2:
						System.out.println("Login Menu");
						phase2On = true;
						phase5On = false;
						break;
						
					default:
						System.out.println("Exit");
						menuOn = logout();
						phase5On = false;
						break;
					}
				}
				
				
				
				
				
				
				}
						
		
		}

		
		
		
		
		
	
	
	public boolean vaildEmail(String email) {
		//Email validation:  greater the 2 and contins at least one @, and one dot.
		// we will count this as valid
		if(email.length()>2 && email.contains("@") && email.contains(".")) {
			System.out.println("Is valid email");
			return true;
		}else {
			return false;
		}
		
	}
	
	
	
//	
//	public boolean login(Session session, StudentService service, String emailInput){
//		
//		
//		
//		//validate email
////		boolean isvald = stdService.validateStudent(session, emailInput);
//		
//		if(isvald) {
//			System.out.println("!!!Successfull Logged in!!");
//			return true;
//		}else {
//			System.out.println("Log in Failed");
//			return false;
//		}
//
//		
//	}
	
	public boolean logout() {
		System.out.println("Exiting...Goodbye");
		return false;
	}
	
	
	///=====Version 2
	
	
	
	
	public int phase1() {
	
		displayWelcomeMessageP1();
		displayMenuOptionsP1();
		return getPhase1Input();
		
		
	}
	
	public int getPhase1Input() {
		System.out.println("==========DEBUG: Inside phase 1============");
		int userSelection = 0;
		Scanner inputConsole = new Scanner(System.in);
		
		
		//While loop teminator
		boolean phase1Game = true;
		
		//This loop will continue until we get a numeric selection from user
		while(phase1Game) {
			
			try {
				//get user input
				userSelection = inputConsole.nextInt();
				
				//checking if numeric selection is 1 or 2
				if(userSelection == 1 || userSelection == 2) {
					System.out.println("DEBUG phase 1: Validate selection ");
					System.out.println("You entered " + userSelection);
					phase1Game = false;
				}else {
					System.out.println("Opps... Only Enter 1 or 2...");
					System.out.println("DEBUG phase 1: inValidate selection ");
					displayMenuOptionsP1();
				}
				
			//Catching Input missmatchException	
			} catch (InputMismatchException e) {
				System.out.println("Whoopies daisy! Only Enter 1 or 2, No words please ");
				inputConsole.nextLine();
			}
		}
		
		System.out.println("DEBUG: Phase1 InputOutside while loop");
		
		return userSelection;

	}
	
	
	public void displayWelcomeMessageP1() {
		System.out.println("\n====== Welcome to the Menu ======\n");
		
	}
	
	public void displayMenuOptionsP1() {
		System.out.println("1. Login");
		System.out.println("2. Exit\n");
		System.out.println("Please Select 1 or 2");
	}


	
	
	
	public String phase2A() {
		
		 return getPhase2Input();
	}
	
	public boolean phase2(Session session, String emailInput) {
		System.out.println(" ==========DEBUG: Inside phase 2 outter============ ");
		displayLoginMenuP2();
		String p2UserEmail = emailInput;
		
		boolean phase3GameOne = getEmailValidatePasswordP2(session, p2UserEmail);
		
		return phase3GameOne;
		
	}
	
	
	
	public String getUserEmail() {
		
		
		return null; 
	}

	public String getPhase2Input() {
		System.out.println(" ==========DEBUG: Inside phase 2 Inner");
		
		String emailInput = null;
		Scanner inputConsole = new Scanner(System.in); 
		boolean phase2Game = true;
		//get user input
		displayLoginOptionsP2();
		
		while(phase2Game) {
			////
		
			try {
				//try catch
				emailInput = (String) inputConsole.nextLine();
				System.out.println("DEBUG phase 2: Validate selection \'email\' ");
				//validate email
				if(vaildEmail(emailInput)) {
					//
					phase2Game = false;
				}else {
					System.out.println("DEBUG phase 2: inValidate selection ");
					System.out.println("Opps...That String is invalid...");
					displayLoginMenuP2();
					displayLoginOptionsP2();
				}
				
				
				
			} catch (InputMismatchException e) {
				System.out.println("Whoopies daisy! Only Enter Words, No Numeric values please.");
				inputConsole.nextLine();
				displayLoginMenuP2();
				displayLoginOptionsP2();
			}
		
		}
		System.out.println("DEBUG: Phase 2  Outside While loop");
		return emailInput;
	}
	
	
	
	public boolean getEmailValidatePasswordP2(Session session, String emailInput) {
		
		boolean phase2BGameOn = true;
		boolean phase3On = false;
		//Get email, validate Password
		int isvald = stdService.validateStudent(session, emailInput);
		
		
		
		
			//if its 1 email not found, if its 2 password not found, if its 3 then both were a match
			System.out.println(isvald);
			switch (isvald) {
			case 1:
				System.out.println("DEBUG: EMAIL IS NOT FOUND");
				System.out.println("Opp... I cant find that email" + emailInput);
				phase3On = false;
				break;
			case 2:
				System.out.println("DEBUG: PASSWORD DOES NOT MATCH");
				System.out.println();
				phase3On = false;
				break;
			case 3:
				System.out.println("DEBUG: SUCCESS BOTH MATCH");
				System.out.println("Yay!!! Student Found");
				phase3On = true;
				break;
			}
		
		
		return phase3On;
	}
	
	public void displayLoginMenuP2() {
		System.out.println("            Welcome to Login Menu");
	}
	public void displayLoginOptionsP2() {
		System.out.println("Please Enter Email");
	}
	
	
	public Student phase3A(Session session, String emailInput) {
		System.out.println(" ==========DEBUG: Inside phase 3 outter============ ");
		displayLoginMenuP2();
		//Get current student
		Student student = getCurrentStudentP3(session, emailInput);
		return student;
	}
	public int phase3(Session session,Student student){
//		System.out.println(" ==========DEBUG: Inside phase 3 outter============ ");
//		displayLoginMenuP2();
//		//Get current student
//		Student student = getCurrentStudentP3(session, emailInput);
		
		displayRegisterWelcomeMessageP3(student);
		displayCourseListHeaderP3();
		//display courses
		displayStudentCurrentCoursesP3(student, session);
		
		return phase3Input();
		
		
	}
	
	public int phase3Input() {
		
		System.out.println(" ==========DEBUG: Inside phase 3 Inner");
		//get user input
		displayRegisterOptionsP3();
		
		int userSelection = 0;
		Scanner inputConsole = new Scanner(System.in); 
		boolean phase3Game = true;
		
		
		while(phase3Game) {
			try {
				userSelection = inputConsole.nextInt();
				
				if(userSelection == 1 || userSelection == 2) {
					System.out.println("DEBUG phase 3: Validate selection ");
					System.out.println("You entered " + userSelection);
					phase3Game = false;
				}else {
					System.out.println("Opps... Only Enter 1 or 2...");
					System.out.println("DEBUG phase 3: inValidate selection ");
					displayRegisterOptionsP3();
				}
				
				
			} catch (InputMismatchException e) {
				System.out.println("Whoopies daisy! Only Enter 1 or 2, No words please.");
				inputConsole.nextLine();
				displayRegisterOptionsP3();
			}
			
			
		
		}
		
		System.out.println("DEBUG: Phase3 InputOutside while loop");
		//Display the classes 
		return userSelection;
		
		
	}
	
	public void displayStudentCurrentCoursesP3(Student student, Session session) {
		Set<Course> courseList = stdService.getStudentCourses(student, session);
		Iterator<Course> itr = courseList.iterator();
		
		while(itr.hasNext()) {
			Course c = itr.next();
			System.out.println(c.getCid() + "  " + c.getcName() + "  " + c.getcInstructorName());
		}
		
	}
	/**
	 * Returns the current Student Object
	 * @param session
	 * @param emailInput
	 * @return
	 */
	public Student getCurrentStudentP3(Session session, String emailInput) {
		return stdService.getStudentByEmail(emailInput, session);
	}
	
	public void displayRegisterWelcomeMessageP3(Student student) {
		System.out.println("                     Welcome " + student.getsName());
	}
	public void displayCourseListHeaderP3() {
		System.out.println("My Classes:\n");
		System.out.println("#  COURSE NAME  INSTRUCTOR NAME\n");
	}
	
	public void displayRegisterOptionsP3() {
		System.out.println("\n          Select An Option\n");
		System.out.println("1. Register to Class");
		System.out.println("2. Logout");
		
		
		
	}
	
	public void displayMenuOptionsP3() {

		System.out.println("            Welcome to Register page");
	}
	
	
	public void phase4(Session session, Student student) {
		
		List<Course> courseList = getAllCoursesP4(session);
		displaySelectionMenuP4();
		displayAllCoursesP4(courseList);
		//select course
		int courseId = Phase4Input();
		
		//need stuen object 
		registerStudentForCourse(student, session, courseId, courseList);
		displaySelectionMenuP4();
		displayStudentCurrentCoursesP3(student, session);
	}
	
	public int Phase4Input() {
		promptSelectionOptionsP4();
		
		int userSelection = 0;
		Scanner inputConsole = new Scanner(System.in); 
		boolean phase4Game = true;
		
		while(phase4Game) {
			try {
				userSelection = inputConsole.nextInt();
				
				if(userSelection >= 1 && userSelection <=10) {
					System.out.println("DEBUG phase 4: Validate selection ");
					System.out.println("You entered " + userSelection);
					phase4Game = false;
				}else {
					System.out.println("Opps... Only Enter 1 through 10");
					System.out.println("DEBUG phase 4: inValidate selection ");
					promptSelectionOptionsP4();
				}
				
				
			} catch (InputMismatchException e) {
				System.out.println("Whoopies daisy! Only Enter 1 through 10, No words please.");
				inputConsole.nextLine();
				promptSelectionOptionsP4();
			}
		
		}
		
		return userSelection;
	}
	
	public void displaySelectionMenuP4() {
		System.out.println("\nAll Courses:\n");
		System.out.println("#  COURSE NAME  INSTRUCTOR NAME\n");
	}
	
	
	public List<Course> getAllCoursesP4(Session session) {
		List <Course> crslist = crsService.getAllCourses(session);
		return crslist;
	}
	
	public void registerStudentForCourse(Student student, Session session, int courseId, List<Course> courseList) {
		stdService.registerStudentToCourse(student, session, courseId, courseList);
	}
	
	public void promptSelectionOptionsP4() {
		System.out.println("\nPlease select a course 1-10");
	}
	public void displayAllCoursesP4(List<Course> courseList) {
		
		Iterator<Course> itr = courseList.iterator();
		while(itr.hasNext()) {
			Course c = itr.next();
			System.out.println("Course id: " + c.getCid() + ", Name: " + c.getcName() + ", Instructor: " + c.getcInstructorName());

		}
	}
	
	
	public int phase5() {
		promptRestartGameP5();
		return phase5Input();
	}
	public void promptRestartGameP5() {
		System.out.println("REGISTER FOR A COURSE");
		System.out.println("lOGIN");
		System.out.println("EXIT");
	}
	public int phase5Input() {
		
		int userSelection = 0;
		Scanner inputConsole = new Scanner(System.in); 
		boolean phase5Game = true;
		
		while(phase5Game) {
			try {
				
				userSelection = inputConsole.nextInt();
				
				if(userSelection >=1 && userSelection <=3) {
					System.out.println("DEBUG phase 5: Validate selection ");
					System.out.println("You entered " + userSelection);
					phase5Game = false;
				}else {
					System.out.println("Opps... Only Enter 1, 2 or 3..");
					System.out.println("DEBUG phase 5: inValidate selection ");
				}
			} catch (InputMismatchException  e) {
				System.out.println("Whoopies daisy! Only Enter 1, 2 or 3 , No words please.");
				inputConsole.nextLine();
				promptRestartGameP5();
			}
		}
		return userSelection;
		
	}
	
	
	}
	
