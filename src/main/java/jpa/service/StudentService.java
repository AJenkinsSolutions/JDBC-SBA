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
		
		return s;
	}

	@Override
	public int validateStudent(Session session, String emailInput) {
	
		//Get user input
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

	@Override
	public Set<Course> getStudentCourses(Student student, Session session) {
		
		// iterate through the list of Courses
		Set<Course> cList = student.getsCourses();
		Iterator<Course> itr = cList.iterator();
		

		return cList;
	}

	
	@Override
	public void registerStudentToCourse(Student student, Session session, int courseId, List<Course> crsList) {
		
		
		//create a courseService Object
		CourseDAO crsService = new CourseService();
		
		//Get selected course //retieving the selected course from list of courses
		Course c1 = crsList.get(courseId -1);
		
		//get current students, current courses
		Set<Course> currentList = student.getsCourses();
		
		//adding course to array list
		currentList.add(c1);
		//save the course
		session.save(c1);
		//set student course set
		student.setsCourses(currentList);
				
		//Store student
		session.save(student);
		
	}

}
