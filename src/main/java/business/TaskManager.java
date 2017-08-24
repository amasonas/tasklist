package business;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import engine.HibernateFactory;
import entity.Task;

public class TaskManager {

	/**
	 * Método para inserção de novo registro no banco de dados
	 * @param task
	 */
	public static void saveTask(Task task) {
		Session session = HibernateFactory.getSessionFactory().openSession();
		session.beginTransaction();
		session.save(task);
		session.flush();
		session.close();

	}

	/**
	 * Método para atualizar registro no banco de dados
	 * @param task
	 */
	public static void updateTask(Task task) {
		Session session = HibernateFactory.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(task);
		session.flush();
		session.close();

	}
	

	
	/**Método para retornar todas as tasks presentes no banco de dados
	 * 
	 * @return
	 */
	public static List<Task> getAltasks(){
		Session session =  HibernateFactory.getSessionFactory().openSession();
		CriteriaQuery<Task> query =  session.getCriteriaBuilder().createQuery(Task.class);
		query.from(Task.class);
		List<Task> resultList = session.createQuery(query).getResultList();
		System.out.println("Result size "+ resultList.size());
		session.close();
		return resultList;
		
		
	}
	
	/**Método para remover registro do banco de dados
	 * @param task
	 */
	public static void deleteTask(Task task){
		Session session = HibernateFactory.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(task);
		session.flush();
		session.close();
	}
}
