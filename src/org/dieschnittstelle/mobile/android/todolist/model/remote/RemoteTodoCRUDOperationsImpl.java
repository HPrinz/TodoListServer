package org.dieschnittstelle.mobile.android.todolist.model.remote;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;
import org.dieschnittstelle.mobile.android.todolist.model.Todo;
import org.dieschnittstelle.mobile.android.todolist.model.ITodoCRUDOperations;

public class RemoteTodoCRUDOperationsImpl implements
		ITodoCRUDOperations {

	protected static Logger logger = Logger
			.getLogger(RemoteTodoCRUDOperationsImpl.class);

	/**
	 * the executor that actually performs the CRUD operations
	 */
	private TodoCRUDExecutor crudExecutor;
	
	/**
	 * here we will be passed the context parameters by the resteasy framework
	 * note that the request context is only declared for illustration purposes, but will not be further used here
	 * @param servletContext
	 */	
	public RemoteTodoCRUDOperationsImpl(@Context ServletContext servletContext, @Context HttpServletRequest request) {
//		logger.info("<constructor>: " + servletContext + "/" + request);
		// read out the dataAccessor
		this.crudExecutor = (TodoCRUDExecutor)servletContext.getAttribute("todoCRUD");
		
//		logger.info("read out the todoCRUD from the servlet context: " + this.crudExecutor);		
	}

	
	@Override
	public List<Todo> readAllTodos() {
		logger.info("readAllTodos()");
		return this.crudExecutor.readAllTodos();
	}

	@Override
	public Todo createTodo(Todo item) {
		logger.info("createItem(): " + item);
		return this.crudExecutor.createTodo(item);
	}

	@Override
	public boolean deleteTodo(final long itemId) {
		logger.info("deleteItem(): " + itemId);
		return this.crudExecutor.deleteTodo(itemId);
	}

	@Override
	public Todo updateTodo(Todo item) {
		logger.info("updateItem(): " + item);
		return this.crudExecutor.updateTodo(item);
	}

	@Override
	public Todo readTodo(final long itemId) {
		logger.info("readTodo(): " + itemId);
		return this.crudExecutor.readTodo(itemId);
	}
	
	public boolean deleteAll(){
		logger.info("deletAll(): ");
		return this.crudExecutor.deletAll();
	}

}
