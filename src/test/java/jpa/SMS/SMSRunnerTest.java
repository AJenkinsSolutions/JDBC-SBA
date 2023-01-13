package jpa.SMS;

import static org.junit.Assert.assertTrue;

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
    	SessionFactory factory = HibernateUtil.getSessionFactory();
    	Session session = HibernateUtil.getConnection(factory);
    	StudentDAO testStdDao = new StudentService();
    	boolean results = testStdDao.validateStudent(session, "hluckham0@google.ru" );
    	
    	
        assertTrue( results );
    }
}
