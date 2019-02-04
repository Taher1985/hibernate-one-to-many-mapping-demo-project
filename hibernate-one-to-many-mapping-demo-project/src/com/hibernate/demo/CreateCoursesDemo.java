package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Course;
import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;

public class CreateCoursesDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class).buildSessionFactory();
		// create session
		Session session = sessionFactory.getCurrentSession();
		try {
			// start a transaction
			session.beginTransaction();

			// get the instructor from DB
			int id = 4;
			Instructor instructor = session.get(Instructor.class, id);

			// create some courses
			Course course1 = new Course("Cooking");

			// add courses to instructor
			instructor.add(course1);

			// save the courses
			session.save(course1);

			// commit transaction
			session.getTransaction().commit();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

}
