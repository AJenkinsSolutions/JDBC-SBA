package jpa.dao;

import jpa.entitymodels.Course;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;

public interface CourseDAO {

	
	List<Course> getAllCourses(Session session);
}
