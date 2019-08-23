package connection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class Mapping {
	public Mapping() {
	}

	public static void main(String[] args) {
//		delete();
//		insert();
		select();
		update();
		select();
	}

	public static void select() {
		Configuration configuration;
		configuration = new Configuration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		String sqlQuery = "select * from testtable";
		Query query = session.createSQLQuery(sqlQuery);
		List<Object> list = query.list();
		session.close();
		for (int i = 0; i < list.size(); i++) {
			Object[] listItem = (Object[]) list.get(i);
			System.out.println(listItem[0] + " " + listItem[1]);
		}
	}


	public static void update() {
		Configuration configuration;
		configuration = new Configuration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		String sqlQuery = "update TesttableEntity set content='999' where id=0";
		Query query = session.createQuery(sqlQuery);
		Transaction transaction = session.getTransaction();
		transaction.begin();
		query.executeUpdate();
		transaction.commit();
	}

	public static void insert() {
		TesttableEntity testtableEntity = new TesttableEntity();
		testtableEntity.setId(0);
		testtableEntity.setContent("ok");
		Configuration configuration;
		configuration = new Configuration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.save(testtableEntity);
		transaction.commit();
		session.close();
	}

	public static void delete() {
		TesttableEntity testtableEntity = new TesttableEntity();
		testtableEntity.setId(0);
		testtableEntity.setContent("ok");
		Configuration configuration;
		configuration = new Configuration();
		configuration.configure();
		SessionFactory factory = configuration.buildSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = session.getTransaction();
		transaction.begin();
		session.delete(testtableEntity);
		transaction.commit();
		session.close();
	}

}
