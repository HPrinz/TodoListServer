package org.dieschnittstelle.mobile.android.todolist.model.remote;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

@WebListener
public class RemoteTodoServletContextListener implements ServletContextListener {

	protected static Logger logger = Logger
			.getLogger(RemoteTodoServletContextListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent evt) {
		logger.info("contextDestroyed()");

		// we read out the TouchpointCRUDExecutor and let it store its content
		TodoCRUDExecutor exec = (TodoCRUDExecutor) evt
				.getServletContext().getAttribute("todoCRUD");

		logger.info("contextDestroyed(): loaded executor from context: " + exec);

		if (exec == null) {
			logger.warn("contextDestroyed(): no executor found in context. Ignore.");
		} else {
			exec.store();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent evt) {
		logger.info("contextInitialised()");

		// we create a new executor for a file to be stored in the context root
		String rootPath = evt.getServletContext().getRealPath("/");
		TodoCRUDExecutor exec = new TodoCRUDExecutor(new File(
				rootPath, "todo.data"));

		// we call load() on the executor to load any exsisting data (if there
		// are any)
		exec.load();

		// then we put the executor into the context to make it available to the
		// other components
		evt.getServletContext().setAttribute("todoCRUD", exec);
		
	}

}
