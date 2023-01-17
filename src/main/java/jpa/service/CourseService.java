package jpa.service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;

public class CourseService extends HibernateUtil implements CourseDAO {

	@Override
	public 	List<Course> getAllCourses(Session session) {
		

		String hql = "FROM Course";
		
		TypedQuery<Course> query = session.createQuery(hql, Course.class);
		List<Course> results = query.getResultList();
		
		//fancy iterator
		Iterator<Course> itr = results.iterator();
//		while(itr.hasNext()) {
//			Course c =itr.next();
//			
//		}
		
	
		return results;
	}

}
