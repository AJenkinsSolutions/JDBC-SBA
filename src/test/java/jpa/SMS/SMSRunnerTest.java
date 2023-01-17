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
 * Unit test for simple App.
 */
public class SMSRunnerTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testValidateStudent()
    {
    	
    	//Password need to be inputted by tester:  X1uZcoIh0dj
    	SessionFactory factory = HibernateUtil.getSessionFactory();
    	Session session = HibernateUtil.getConnection(factory);
    	StudentDAO testStdDao = new StudentService();
    	int results = testStdDao.validateStudent(session, "hluckham0@google.ru" );
    	
    	
        assertEquals( 3, results );
    }
}
