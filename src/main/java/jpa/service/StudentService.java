package jpa.service;

import java.util.ArrayList;
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

public class StudentService extends HibernateUtil implements StudentDAO {

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
				
//				HibernateUtil.disconnect(session);
				
				
			}
			
			
		} catch (NoResultException e ) {
			
			System.out.println("No Students rows found");
			return results;
			
		}
		
		
		
		return results;
		
	}

	@Override
	public Student getStudentByEmail(String email, Session session) {
		
		
		String hql = "FROM Student WHERE email = :email ";
		
		TypedQuery<Student> query = session.createQuery(hql, Student.class);
		query.setParameter("email", email);
		Student s = (Student) query.getSingleResult();
		
		System.out.println("Name: " + s.getsName() + ", Email : "+ s.getsEmail() + ", Password" + s.getsPass()+ " Course: " + s.getsCourses());

//		HibernateUtil.disconnect(session);
		
		return s;
	}

	@Override
	public boolean validateStudent(Session session, String emailInput) {
		// TODO Auto-generated method stub
		//1 connect to session 
		

		
		
		//GET uSER INPUT
		Scanner input = new Scanner(System.in); 
		System.out.println("validating Credenitals");
		
	
	
		//2 query data base
		String hql = "FROM Student WHERE email = :email ";
		
		TypedQuery<Student> query = session.createQuery(hql, Student.class);
		query.setParameter("email", emailInput);
		
		//3 get results 
		try {
			Student std = (Student)query.getSingleResult();
			System.out.println("\n>>Found Email<<");
			
			
			//Get user input
			System.out.println("Please Enter Password");
			String passwordInput = input.nextLine();
			
			//Get password for current Student
			String expectedPassword = std.getsPass();
			
			//Check if passward and passwordInput Match
			if(expectedPassword.equals(passwordInput)) {
				System.out.println("Successfully validated");
//				HibernateUtil.disconnect(session);
				return true;
				
			}else {
				System.out.println("Your Password was incorrect");
//				HibernateUtil.disconnect(session);
				return false;
			}
			
			
			
			
			
		} catch (NoResultException e) {
			e.printStackTrace();
			System.out.println("Failed: Email is incorrect ");
//			HibernateUtil.disconnect(session);
			return false;
		}
	
		
	}

	@Override
	public Set<Course> getStudentCourses(Student student, Session session) {
		//Get student object and id 
		// get Student Scourse column 
		// iterate through the list of Courses
		Set<Course> cList = student.getsCourses();
		Iterator<Course> itr = cList.iterator();
		
		while(itr.hasNext()) {
			Course c = itr.next();
			System.out.println(c.getCid() + "  " + c.getcName() + "  " + c.getcInstructorName());
		}
	
//		HibernateUtil.disconnect(session);
		return cList;
	}

	
	@Override
	public void registerStudentToCourse(Student student, Session session, int courseId, List<Course> crsList) {
		
		
		//create a courseService Object
		CourseDAO crsService = new CourseService();
		//Get all avaiable course from course Service
		
		//Display course options to screen
//		List<Course> crsList = crsService.getAllCourses(session);
		
		//Get user input
		
		//Get selected course //retieving the selected course from list of courses
		Course c1 = crsList.get(courseId);
		
		
		//+++++++++++++++++++++++++++//
		//get current students, current courses
		Set<Course> currentList = student.getsCourses();
		
		//Display the current students <courses> list
//		System.out.println("Debug");
//		for(Course c : currentList) {
//			System.out.println(c.getcName());
//		}
		
		//Add Desired course to student array
		
		//adding course to array list
		currentList.add(c1);
		//save the course
		session.save(c1);
		//set student course set
		student.setsCourses(currentList);
				
		//Store student
		session.save(student);
		
		//++++++///
	
		

	}

}
