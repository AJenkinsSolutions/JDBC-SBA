package jpa.SMS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import jpa.dao.CourseDAO;
import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.service.CourseService;
import jpa.service.MenuService;
import jpa.service.StudentService;

/**
 * Filename: SmsRunner.java
 * @author Alexander Jenkins
 * 01/17/2023
 */
public class SMSRunner 
{
	/**
	 * Sms Runnner is the main entry point
	 * hibernate.cfg.xml will automaticly create a new data
	 * RunMenu will automaticly seed data if needed
	 */
    public static void main( String[] args )
    {

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Transaction trans = session.beginTransaction();
		
	
	
		MenuService menu = new MenuService();	
		menu.runMenu(session);
			
    
        
		trans.commit();
        factory.close();
        session.close(); 
        	 
    }
}
