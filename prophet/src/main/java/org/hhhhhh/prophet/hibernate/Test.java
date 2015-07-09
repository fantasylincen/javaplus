package org.hhhhhh.prophet.hibernate;
import org.hhhhhh.prophet.hibernate.dto.UserDto;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Test {

    public static void main(String[] args) {

//        try {
//            SessionFactory sf =
//                new Configuration().configure().buildSessionFactory();
//            Session session = sf.openSession();
//            Transaction tx = session.beginTransaction();
//
//            for (int i = 0; i < 200; i++) {
//            	UserDto d = new UserDto();
//                d.setId("customer" + i);
//                d.setPwd("customer");
//                d.setJiFen(1);
//                d.setNick("");
//                d.setEmail("111@111.com");
//                session.save(d);
//            }
//
//            tx.commit();
//            session.close();
//
//        } catch (HibernateException e) {
//            e.printStackTrace();
//        }
    	
    	
    	   try {
               SessionFactory sf =
                   new Configuration().configure().buildSessionFactory();
               Session session = sf.openSession();
               Transaction tx = session.beginTransaction();

               session.get("customer0", a);
               tx.commit();
               session.close();

           } catch (HibernateException e) {
               e.printStackTrace();
           }
    }
}