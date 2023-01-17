package jpa.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import jpa.dao.CourseDAO;
import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
/**
 * Filename: StudentService.java
 * @author Alexander Jenkins
 * 01/17/2023
 */
public class StudentService extends HibernateUtil implements StudentDAO {

	/**
	 * Retrieves all present students from the database
	 */
	@Override
	public List<Student> getAllStudents(Session session) {
	
		String hql = "FROM Student";
		
		TypedQuery<Student> query = session.createQuery(hql, Student.class);
		
		List<Student> results = new ArrayList<>();
		
		try {
			results = query.getResultList();
			
			Iterator<Student> itr = results.iterator();
			
			while(itr.hasNext()) {
				Student s = itr.next();
				
				System.out.println("Name: " + s.getsName() + ", Email : "+ s.getsEmail() + ", Password" + s.getsPass() + " Course: " + s.getsCourses());
				
			}
			
			
		} catch (NoResultException e ) {
			
			System.out.println("No Students rows found");
			return results;
			
		}
		
		
		
		return results;
		
	}

	/**
	 * Retrieve student object by Email
	 */
	@Override
	public Student getStudentByEmail(String email, Session session) {
		
		String hql = "FROM Student WHERE email = :email ";
		
		TypedQuery<Student> query = session.createQuery(hql, Student.class);
		query.setParameter("email", email);
		
		Student s = (Student) query.getSingleResult();
		
		
		
		return s;
	}

	/**
	 * Validate wheather student is present in the databas 
	 */
	@Override
	public int validateStudent(Session session, String emailInput) {
	
	
		Scanner input = new Scanner(System.in); 
//		System.out.println("validating Credenitals");
		

		String hql = "FROM Student WHERE email = :email ";
		
		TypedQuery<Student> query = session.createQuery(hql, Student.class);
		query.setParameter("email", emailInput);
		
	
		try {
			Student std = (Student)query.getSingleResult();

			
			
			
			System.out.println("\nPlease Enter Password");
			String passwordInput = input.nextLine();
			
			
			String expectedPassword = std.getsPass();
			
		
			if(expectedPassword.equals(passwordInput)) {
				System.out.println("Successfully validated !");
 
				return 3;
				
			}else {
				System.out.println("Your Password was incorrect");

				return 2;
			}
						
		} catch (NoResultException e) {
			System.out.println("Failed: No Students matching email: " + emailInput );
			return 1;
		}
	
		
	}

	
	/**
	 * Retreive the current student courses present in the database
	 */
	@Override
	public Set<Course> getStudentCourses(Student student, Session session) {
		// iterate through the list of Courses
		Set<Course> cList = student.getsCourses();
		
		return cList;
	}

	
	/**
	 * Register current student to selected course
	 */
	@Override
	public void registerStudentToCourse(Student student, Session session, int courseId, List<Course> crsList) {
		
		
		//create a courseService Object
		CourseDAO crsService = new CourseService();
		
		//Get selected course //retieving the selected course from list of courses
		Course c1 = crsList.get(courseId -1);
		
		//get current students, current courses
		try {
			Set<Course> currentList = student.getsCourses();
			//adding course to array list
			currentList.add(c1);
			//save the course
			session.save(c1);
			//set student course set
			student.setsCourses(currentList);
					
			//Store student
			session.save(student);
			
			
		} catch (NullPointerException e) {
			Set<Course> newCrsList = new HashSet<Course>();
			
			newCrsList.add(c1);
			//adding course to array list
			//save the course
			session.save(c1);
			//set student course set
			student.setsCourses(newCrsList);
					
			//Store student
			session.save(student);
			
		}
		
		
		
		
	}

}
