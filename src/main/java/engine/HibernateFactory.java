package engine;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateFactory {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	// build session
	/**
	 * @return
	 */
	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure("/resources/META-INF/hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println(ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	// Retorna Factory da sessão
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	// Encerra Sessão
	public static void shutdown() {
		getSessionFactory().close();
	}
}
