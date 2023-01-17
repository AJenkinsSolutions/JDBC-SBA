package jpa.SMS;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import jpa.dao.StudentDAO;
import jpa.service.HibernateUtil;
import jpa.service.StudentService;

/**
 * Unit test for our sms manager app
 */
public class SMSRunnerTest 
{
    /**
     * This test vvalidates a students credentials are present within the database test
     */
    @Test
    public void testValidateStudent()
    {
    	
    	
    	SessionFactory factory = HibernateUtil.getSessionFactory();
    	Session session = HibernateUtil.getConnection(factory);
    	StudentDAO testStdDao = new StudentService();
    	int results = testStdDao.validateStudent(session, "hluckham0@google.ru" );
    	
    	
        assertEquals( 3, results );
    }
}
