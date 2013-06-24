package org.dieschnittstelle.mobile.android.todolist.model;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/todo")
@Consumes({ "application/json" })
@Produces({ "application/json" })
public interface ITodoCRUDOperations {

	/*
	 * the operations
	 */
	@POST
	public Todo createTodo(Todo item);

	@GET
	public List<Todo> readAllTodos();

	@GET
	@Path("/{itemId}")
	public Todo readTodo(@PathParam("itemId")long dateItemId);

	@PUT
	public Todo updateTodo(Todo item);

	@DELETE
	@Path("/all")
	public boolean deleteAll();
	
	@DELETE
	@Path("/{itemId}")
	public boolean deleteTodo(@PathParam("itemId")long dataItemId);
	

}
