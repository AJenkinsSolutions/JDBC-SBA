package jpa.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import jpa.entitymodels.Student;
import jpa.entitymodels.Course;
/**
 * Filename: StudentDAO.java
 * @author Alexander Jenkins
 * 01/17/2023
 */
public interface StudentDAO {
	
	List<Student> getAllStudents(Session session);
	
	Student getStudentByEmail(String email, Session session);
	
	int validateStudent(Session session, String emailInput);
	
	void registerStudentToCourse(Student student, Session session, int courseId, List<Course> crsList);
	
	Set<Course> getStudentCourses(Student student, Session session);

}
