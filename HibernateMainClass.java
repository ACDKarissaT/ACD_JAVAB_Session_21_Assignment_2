package example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



/*
 * 1. create new project
 * 2. Add required hibernate lib to build path
 * 3. in default package (src) : hibernate.cfg.xml
 * 4. add a mapping resource file: <mapping resource="message.hbm.xml" />
 * 5. Create mapping resource file: message.hbm.xml
 * 
 * 
 */
public class HibernateMainClass {
	private static SessionFactory factory;
	
	public static void main(String[] args) {
		factory = new Configuration().configure().buildSessionFactory();
		
		Message message = new Message();
		message.setMessageText("Hello World!");
		Session session = factory.getCurrentSession();
		
		Transaction tnx = session.beginTransaction();
		Integer idInserted = (Integer) session.save(message);
		System.out.println("ID inserted is " + idInserted);
		tnx.commit();
		
		session = factory.getCurrentSession();
        
        session.beginTransaction();
         
        Message m = (Message) session.get(Message.class, idInserted);
        System.out.println(m.getMessageText());
         
        session.getTransaction().commit();
       
		
		session.close();
	}
}
