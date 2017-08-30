package rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import business.TaskManager;
import entity.Task;
import net.bytebuddy.asm.Advice.Return;

@Path("/taskrest")
public class TaskRest {

	
	/**Método para camada rest para  salvar novos registros.
	 * @param task
	 * @return
	 */
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("text/html")
	@Path("/save")
	public String save(Task task) {

		TaskManager.saveTask(task);
		try {
			return "task id " + task.getId();
		} catch (Exception e) {
			return "error";
		}
	}
	
	/**Método para camada rest para  alterar registros.
	 * @param task
	 * @return
	 */
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("text/html")
	@Path("/update")
	public String update(Task task) {

		TaskManager.updateTask(task);
		try {
			return "task id " + task.getId();
		} catch (Exception e) {
			return "error";
		}
	}
	

	/**Método para camada rest para  obter registros.
	 * @return
	 */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/tasklist")
	public List<Task> getTaskList(){

		try{
			List<Task> tList= TaskManager.getAltasks();
			return tList;
		}catch (Exception e){
			List<Task> tList = new ArrayList<Task>();
		
			return tList;
		}
	}
	
	/**
	 * Método para camada rest para  remover registros
	 * @param task
	 * @return
	 */
	@DELETE
	@Consumes("application/json; charset=UTF-8")
	@Produces("text/html")
	@Path("/remove")
	public String delete(Task task){

		try{
			TaskManager.deleteTask(task);
			return "success";
		}catch (Exception e){
		
			return "error";
		}
	}
	
	
}
