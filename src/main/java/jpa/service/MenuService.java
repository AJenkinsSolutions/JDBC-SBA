
package jpa.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder.Case;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.mysql.cj.Query;
import com.mysql.cj.x.protobuf.MysqlxExpect.Open.Condition.Key;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

/**
 * Filename: MenuService.java
 * @author Alexander Jenkins
 * 01/17/2023
 */
public class MenuService {
	
	
	
	public StudentService stdService =  new StudentService();
	public CourseService crsService = new CourseService();
	
	
	/**
	 * Handles the execution of our MenuService helper methods 
	 * in order to excute CRUD operation with SmsRunner.java
	 * @param session
	 */
	public  void runMenu(Session session) {
		
		Scanner consoleIn = new Scanner(System.in);
		
		boolean menuOn = true;
		int userInput = 0;
		String emailInput = null;
		Student student = null;

		//Determines if we enter next phase
		boolean phase1on = true;
		boolean phase2On = false;
		boolean phase3On = false;
		boolean phase4On = false;
		boolean phase5On = false;
		
		
		automatedDbSeeding(session);
		
		
		while(menuOn) {
			
			
			//==================Phase one=======================
			
			while(phase1on) {
				
				int p1UserInput = phase1();
				
				switch (p1UserInput) {
				case 1:
		
					phase2On = true;
					phase1on = false;
					break;
				
				case 9:
					
				
					dropDB(session);
					System.out.println("\nSuccess DataBase Reset.\n Please resart program");
					phase1on = false;
			        menuOn = false;
					break;

				default:
					
					menuOn = logout();
					phase1on = false;
					break;
				}
			}
		
			
			//==================Phase Two=======================

			while(phase2On) {
				emailInput = phase2A();
				boolean p2UserInput = phase2(session, emailInput);
				
			
				if(p2UserInput) {
					
					phase3On = true;
					phase2On = false;
				
				}else {
			
				}
				
				
			}
			//==================Phase Three=======================
			
			while(phase3On) {
//			
				student = phase3A(session, emailInput);
				int p3UserInput = phase3(session, student);
				
				switch (p3UserInput) {
				case 1:
//				
					phase4On = true;
					phase3On = false;
					break;
				
				default:
//					
					menuOn = logout();
					phase3On = false;
					break;
				}
				
				
			}
			//==================Phase Four=======================	
			
				while(phase4On) {
//				
					phase4(session, student);
					phase4On = false;
					phase5On = true;
					
					
				}
				
				
				while(phase5On) {
//					
					int p5UserInput = phase5();
					
					switch (p5UserInput) {
					case 1:
						System.out.println("1. Register Menu");
						phase3On = true;
						phase5On = false;
						break;
					case 2:
						System.out.println("2.Login Menu");
						phase2On = true;
						phase5On = false;
						break;
						
					default:
						System.out.println("3. Exit");
						menuOn = logout();
						phase5On = false;
						break;
					}
				}
			
				
				}
						
		}
	
	
	/**
	 * Will automaticly seed the data is no data is present
	 * @param session
	 */
	public void automatedDbSeeding(Session session ) {
		
		List<Course> crslistSet = crsService.getAllCourses(session);
		if(crslistSet.size() == 0 || crslistSet== null) {
			System.out.println("Seedins Required ");
			seedDataBase(session);
		}else{
			System.out.println("Seeding is not needed");
		}
	}
	
	
	/**
	 * Insertion data for SMS SBA Requirments
	 * @param session
	 */
	public void seedDataBase(Session session) {
		Student std = new Student();
		System.out.println("Seeding database ");
		Student s1 = new Student("hluckham0@google.ru", "Hazel Luckham", "X1uZcoIh0dj");
		Student s2 = new Student("sbowden1@yellowbook.com", "Sonnnie Bowden", "SJc4aWSU");
		Student s3 = new Student("qllorens2@howstuffworks.com", "Quillan Llorens", "W6rJuxd");
		Student s4 = new Student("cstartin3@flickr.com", "Clem Startin", "XYHzJ1S");
		Student s5 = new Student("tattwool4@biglobe.ne.jp", "Thornie Attwool", "Hjt0SoVmuBz");
		Student s6 = new Student("hguerre5@deviantart.com", "Harcourt Guerre", "OzcxzD1PGs");
		Student s7 = new Student("htaffley6@columbia.edu", "Holmes Taffley", "xowtOQ");
		Student s8 = new Student("aiannitti7@is.gd", "Alexandra Iannitti", "TWP4hf5j");
		Student s9 = new Student("ljiroudek8@sitemeter.com", "Laryssa Jiroudek", "bXRoLUP");
		Student s10 = new Student("cjaulme9@bing.com", "Cahra Jaulme", "FnVklVgC6r6");
		
		session.save(s1);
		session.save(s2);
		session.save(s3);
		session.save(s4);
		
		session.save(s5);
		session.save(s6);
		session.save(s7);
		session.save(s8);
		session.save(s9);
		session.save(s10);
		
		Course crs = new Course();
        Course c1 = new Course("English","Anderea Scamaden");
        Course c2 = new Course("Mathematics", "Eustace Niemetz");
        Course c3 = new Course("Anatomy", "Reynolds Pastor");
        Course c4 = new Course("Organic Chemistry", "Odessa Belcher");
        Course c5 = new Course("Physics", "Dani Swallow");
        Course c6 = new Course("Digital Logic", "Glenden Reilingen");
        Course c7 = new Course("Object Oriented Programming", "Giselle Ardy");
        Course c8 = new Course("Data Structures", "Carolan Stoller");
        Course c9 = new Course("Politics", "Carmita De Maine");
        Course c10 = new Course("Art", "Kingsly Doxsey");
       
        session.save(c1);
        session.save(c2);
		session.save(c3);
		session.save(c4);
		session.save(c5);
		
		session.save(c6);
		session.save(c7);
		session.save(c8);
		session.save(c9);
		session.save(c10);
	
		
	}
	
	
	/**
	 * Drops or student table
	 * use is conjcution with our resetting database methods
	 * @param session
	 */
	public void resetDataStudentDataBase(Session session) {
		System.out.println("Resetting Student database");
		int affectedRows = session.createSQLQuery("DROP TABLE student").executeUpdate();
		System.out.println(affectedRows);
		}
	
	/**
	 * Will drop the current database
	 * used without our reset database methods
	 * used in conjunction with our automatic seeding logic
	 * @param session
	 */
	public void dropDB(Session session) {
		int affectedRows = session.createSQLQuery("DROP DATABASE smsdb").executeUpdate();
		System.out.println(affectedRows);
	}
	
	
	/**
	 * Drops courses table
	 * used within our reseting database method
	 * @param session
	 */
	public void resetDataCoursesDataBase(Session session) {
		System.out.println("Resetting Course database");
	
	int affectedRows = session.createSQLQuery("DROP TABLE course").executeUpdate();
	System.out.println(affectedRows);
	}

	
	
	/**
	 * Very simple Validates email is correctly formatted 'lite' v1.0
	 * simply checking id string contains '@' '.'
	 * @param emaill
	 * @return
	 */
	public boolean vaildEmail(String email) {
		
		if(email.length()>2 && email.contains("@") && email.contains(".")) {
			return true;
		}else {
			return false;
		}
		
	}
	
	
	/**
	 * Exits our menu serivce
	 * @return
	 */
	public boolean logout() {
		System.out.println("Exiting... Goodbye");
		return false;
	}
	
	
	///=====Version 2========///

	/**
	 * Display phase 1 Headers and prompts 
	 * @return
	 */
	public int phase1() {
	
		displayWelcomeMessageHeaderP1();
		displayMenuOptionsP1();
		return getPhase1Input();
	}
	
	/**
	 * Handles user input for phase 1
	 * Validate input is within selection, handle exceptions
	 * We continue this logic till validate input is reached
	 * @return raw user input
	 */
	public int getPhase1Input() {

		int userSelection = 0;
		Scanner inputConsole = new Scanner(System.in);
		
		

		boolean phase1Game = true;
		
		
		while(phase1Game) {
			
			try {
		
				userSelection = inputConsole.nextInt();
				
			
				if(userSelection == 1 || userSelection == 2 || userSelection == 9) {

					if(userSelection == 1) {System.out.println("Loading Login menu....\n");}
					phase1Game = false;
				}else {
					System.out.println("Opps... Only Enter 1 or 2...");

					displayMenuOptionsP1();
				}
				
	
			} catch (InputMismatchException e) {
				System.out.println("Whoopies daisy! Only Enter 1 or 2, No words please ");
				inputConsole.nextLine();
			}
		}
		
		
		return userSelection;

	}
	
	
	 /**
	  * Displays welcome message header to user
	  */
	public void displayWelcomeMessageHeaderP1() {
		System.out.println("\n |-------------------------------------------------------------------|\n");
		System.out.println("  ____  __  __ ____    __  __    _    _   _    _    ____ _____ ____   \n"
				+ " / ___||  \\/  / ___|  |  \\/  |  / \\  | \\ | |  / \\  / ___| ____|  _ \\  \n"
				+ " \\___ \\| |\\/| \\___ \\  | |\\/| | / _ \\ |  \\| | / _ \\| |  _|  _| | |_) | \n"
				+ "  ___) | |  | |___) | | |  | |/ ___ \\| |\\  |/ ___ \\ |_| | |___|  _ <  \n"
				+ " |____/|_|  |_|____/  |_|  |_/_/   \\_\\_| \\_/_/   \\_\\____|_____|_| \\_\\ \n"
				+ "                                                                      ");
		System.out.println("\n |-----------------------------|Main Menu|---------------------------|\n");
		
	}
	
	/**
	 * Displays Main Menu Options to user
	 */
	public void displayMenuOptionsP1() {
		System.out.println("Please select an Option below\n");
		System.out.println("1. | Login          |   ");
		System.out.println("2. | Exit           |   ");
		System.out.println("9. | Reset DataBase |\n");

	}


	
	
	/**
	 * Handles portion of user email inorder to hand off object
	 * @return
	 */
	public String phase2A() {
		
		 return getPhase2Input();
	}
	
	/**
	 * Initiates a set of logical steps 
	 * To detemine if user wants to login or Exit
	 * Takes in user email string input
	 * Begins validation
	 * @param session
	 * @param emailInput
	 * @return Boolean 
	 */
	
	public boolean phase2(Session session, String emailInput) {

		displayLoginHeaderP2();
		String p2UserEmail = emailInput;
		
		boolean phase3GameOne = getEmailValidatePasswordP2(session, p2UserEmail);
		
		return phase3GameOne;
		
	}
	
	
	/**
	 * Handles user input for phase 2 'login phas '\
	 * validates email formatting is correct 
	 * Handles exceptions for type mismatches
	 * @return rsaw user email input 
	 */
	public String getPhase2Input() {

		
		String emailInput = null;
		Scanner inputConsole = new Scanner(System.in); 
		boolean phase2Game = true;
		//get user input
		displayLoginHeaderP2();
		displayLoginPromptP2();
		
		while(phase2Game) {

		
			try {

				emailInput = (String) inputConsole.nextLine();

				
				if(vaildEmail(emailInput)) {
				
					phase2Game = false;
				}else {

					System.out.println("Opps...That String is invalid...");
					displayLoginHeaderP2();
					displayLoginPromptP2();
				}
				
				
				
			} catch (InputMismatchException e) {
				System.out.println("Whoopies daisy! Only Enter Words, No Numeric values please.");
				inputConsole.nextLine();
				displayLoginHeaderP2();
				displayLoginPromptP2();
			}
		
		}

		return emailInput;
	}
	
	
	
	/**
	 * Second phase of email validation with data and password
	 * In order to match a record in our data bas both the email and password must match
	 * @param session
	 * @param emailInput
	 * @return
	 */
	public boolean getEmailValidatePasswordP2(Session session, String emailInput) {
		
		boolean phase2BGameOn = true;
		boolean phase3On = false;

		int isvald = stdService.validateStudent(session, emailInput);
		
			switch (isvald) {
			case 1:

				System.out.println("Opp... I cant find that email" + emailInput);
				phase3On = false;
				break;
			case 2:

				phase3On = false;
				break;
			case 3:

				System.out.println("Yay!!! Student Found");
				phase3On = true;
				break;
			}
		
		
		return phase3On;
	}
	
	
	
	/**
	 * Display Login Phase header to user
	 */
	public void displayLoginHeaderP2() {
		System.out.println("|-----------------------------|Login Menu|---------------------------|\n");
	}
	
	
	/**
	 * Displays Login in Prompt to user
	 */
	public void displayLoginPromptP2() {
		System.out.println("Please Enter Email");
	}
	
	
	/**
	 * Sub phase of phase 3. 
	 * We use the emailInput to reteives our student object
	 * @param session
	 * @param emailInput
	 * @return
	 */
	public Student phase3A(Session session, String emailInput) {
//		System.out.println(" ==========DEBUG: Inside phase 3 outter============ ");
		displayLoginHeaderP2();
		//Get current student
		Student student = getCurrentStudentP3(session, emailInput);
		return student;
	}
	
	/**
	 * Initates a set of logical steps 
	 * @param session
	 * @param student
	 * @return
	 */
	public int phase3(Session session,Student student){

		
		
		//display courses
		displayStudentCurrentCoursesP3(student, session);
		
		return phase3Input();
		
		
	}
	
	
	/**
	 * Handles user input for register or logout phase 'phase 3'
	 * @return
	 */
	public int phase3Input() {
		
//		System.out.println(" ==========DEBUG: Inside phase 3 Inner");
		//get user input
		displayRegisterOptionsP3();
		
		int userSelection = 0;
		Scanner inputConsole = new Scanner(System.in); 
		boolean phase3Game = true;
		
		
		while(phase3Game) {
			try {
				userSelection = inputConsole.nextInt();
				
				if(userSelection == 1 || userSelection == 2) {

				
					phase3Game = false;
				}else {
					System.out.println("Opps... Only Enter 1 or 2...");

					displayRegisterOptionsP3();
				}
				
				
			} catch (InputMismatchException e) {
				System.out.println("Whoopies daisy! Only Enter 1 or 2, No words please.");
				inputConsole.nextLine();
				displayRegisterOptionsP3();
			}
			
			
		
		}
		
		return userSelection;
		
		
	}
	
	
	/**
	 * Displays current students courses to user
	 * @param student
	 * @param session
	 */
	public void displayStudentCurrentCoursesP3(Student student, Session session) {
		Set<Course> courseList = stdService.getStudentCourses(student, session);
		
		try {
			Iterator<Course> itr = courseList.iterator();
			displayRegisterWelcomeMessageP3(student);
			displayCourseListHeaderP3();
			while(itr.hasNext()) {
				Course c = itr.next();
				System.out.println(c.getCid() + "  " + c.getcName() + "  " + c.getcInstructorName());
			}
		} catch (NullPointerException e) {
			System.out.println("No Courses");
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
		System.out.println("\n                Welcome back, "+ student.getsName());
		
	}
	public void displayCourseListHeaderP3() {
		System.out.println("\nMy Classes:\n");
		System.out.println("ID | COURSE NAME | INSTRUCTOR |\n");
	}
	
	
	/**
	 * Displays Register Phase options to user
	 */
	public void displayRegisterOptionsP3() {
		System.out.println("\n                       Select An Option\n");
		System.out.println("1. | Register to Class | ");
		System.out.println("2. | Logout |");
	}
	
	/**
	 * Displays th header UI for the Registation phase
	 */
	
	public void displayRegisterHeaderP3() {

		System.out.println("\n |-----------------------------|Register Menu|---------------------------|\n");
	}
	
	
	
	/**
	 * This method encompasses a set of logical steps.
	 * Which will give us our registaion phase 'phase 4'
	 * @param session
	 * @param student
	 */
	public void phase4(Session session, Student student) {
		
		List<Course> courseList = getAllCoursesP4(session);
		System.out.println("ID | COURSE NAME | INSTRUCTOR |\n");
		displayAllCoursesP4(courseList);
		//select course
		int courseId = Phase4Input();
		
		//need stuen object 
		registerStudentForCourse(student, session, courseId, courseList);
		displayRegisterSelectionHeaderP4();
		displayStudentCurrentCoursesP3(student, session);
	}
	/**
	 * This method will handle user input for Registration phase
	 * Validation of type int betweeen 1 and 10
	 * Using a while loop we will keep prompting for a valid input
	 * @return Users raw input for selection between 1 and 10
	 */
	
	public int Phase4Input() {
		promptRegisterSelectionP4();
		
		int userSelection = 0;
		Scanner inputConsole = new Scanner(System.in); 
		boolean phase4Game = true;
		
		while(phase4Game) {
			try {
				userSelection = inputConsole.nextInt();
				
				if(userSelection >= 1 && userSelection <=10) {
					
					phase4Game = false;
				}else {
					System.out.println("Opps... Only Enter 1 through 10");

					promptRegisterSelectionP4();
				}
				
				
			} catch (InputMismatchException e) {
				System.out.println("Whoopies daisy! Only Enter 1 through 10, No words please.");
				inputConsole.nextLine();
				promptRegisterSelectionP4();
			}
		
		}
		
		return userSelection;
	}
	
	

	/**
	 * Displays the regiater selection Headers
	 */
	public void displayRegisterSelectionHeaderP4() {
		System.out.println("My Classes:\n");
		System.out.println("ID | COURSE NAME | INSTRUCTOR |\n");
	}
	
	
	
	
	/**
	 * Get a list of all courses from smsdb.
	 * @param session
	 * @return
	 */
	public List<Course> getAllCoursesP4(Session session) {
		List <Course> crslist = crsService.getAllCourses(session);
		return crslist;
	}
	
	/**
	 * Registers current student to chosen course
	 * @param student
	 * @param session
	 * @param courseId
	 * @param courseList
	 */
	public void registerStudentForCourse(Student student, Session session, int courseId, List<Course> courseList) {
		stdService.registerStudentToCourse(student, session, courseId, courseList);
	}
	
	/**
	 * Displays register prompt to user
	 */
	public void promptRegisterSelectionP4() {
		System.out.println("\n              Select a course by ID");
	}
	
	
	
	/**
	 * 
	 * @param courseList
	 */
	public void displayAllCoursesP4(List<Course> courseList) {
		
		Iterator<Course> itr = courseList.iterator();
		while(itr.hasNext()) {
			Course c = itr.next();
			System.out.println("ID: " + c.getCid() + ", Name: " + c.getcName() + ", Instructor: " + c.getcInstructorName());

		}
	}
	
	
	
	/**
	 * Initiates a set of logical steps.
	 * Prompting user to either continuing using the SMS manager
	 * Register more courses 
	 * Login as diffferent user 
	 * Exit
	 * @return
	 */
	public int phase5() {
		promptRestartGameP5();
		return phase5Input();
	}
	
	
	/**
	 * Display prompts to user to determine next set of actions
	 */
	public void promptRestartGameP5() {
		System.out.println("\n1. | Register for course |");
		System.out.println("2. | Login With Different Account |");
		System.out.println("3. | EXIT |\n");
	}
	
	/**
	 * Handles the user input for endgame menu navigation
	 * @return
	 */
	public int phase5Input() {
		
		int userSelection = 0;
		Scanner inputConsole = new Scanner(System.in); 
		boolean phase5Game = true;
		
		while(phase5Game) {
			try {
				
				userSelection = inputConsole.nextInt();
				
				if(userSelection >=1 && userSelection <=3) {

					phase5Game = false;
				}else {
					System.out.println("Opps... Only Enter 1, 2 or 3..");
				
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
	
