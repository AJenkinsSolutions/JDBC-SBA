package jpa.dao;

import jpa.entitymodels.Course;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
/**
 * Filename: CourseDAO.java
 * @author Alexander Jenkins
 * 01/17/2023
 */
public interface CourseDAO {

	
	List<Course> getAllCourses(Session session);
}
