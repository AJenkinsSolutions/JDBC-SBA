package jpa.service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;
/**
 * Filename: CourseService.java
 * @author Alexander Jenkins
 * 01/17/2023
 */
public class CourseService extends HibernateUtil implements CourseDAO {
	
	
	/**
	 * Returns all courses present in the data
	 */
	@Override
	public 	List<Course> getAllCourses(Session session) {
		

		String hql = "FROM Course";
		
		TypedQuery<Course> query = session.createQuery(hql, Course.class);
		List<Course> results = query.getResultList();
		
		Iterator<Course> itr = results.iterator();

		
	
		return results;
	}

}
