package org.dieschnittstelle.mobile.android.todolist.model.remote;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dieschnittstelle.mobile.android.todolist.model.Todo;

public class TodoCRUDExecutor {

	protected static Logger logger = Logger.getLogger(TodoCRUDExecutor.class);

	/**
	 * the id counter
	 */
	private int currentTodoId;

	/**
	 * the file that contains the "database"
	 */
	private File todoDatabaseFile;

	/**
	 * the list of data items, note that the list is *static* as for each client
	 * request a new instance of this class will be created!
	 */
	private final List<Todo> todos = new ArrayList<Todo>();

	public TodoCRUDExecutor() {

	}

	public TodoCRUDExecutor(File file) {
		this.todoDatabaseFile = file;
	}

	public List<Todo> readAllTodos() {
		// logger.info("readAllTodos(): " + todos);

		return this.todos;
	}

	public Todo createTodo(Todo item) {
		// logger.info("createTodo(): " + item);
		// item.setId(idCount++);

		todos.add(item);
		// logger.info("createTodo(): todos are now: " + todos);

		return item;
	}

	public boolean deleteTodo(final long itemId) {
		// logger.info("deleteItem(): " + itemId);

		boolean removed = todos.remove(new Todo() {
			@Override
			public long getId() {
				return itemId;
			}
		});

		return removed;
	}

	public Todo updateTodo(Todo item) {
		// logger.info("updateItem(): " + item + ", todos are: " + todos);

		return todos.get(todos.indexOf(item)).updateFrom(item);
	}

	public Todo readTodo(final long itemId) {

		return todos.get(todos.indexOf(new Todo() {
			@Override
			public long getId() {
				return itemId;
			}
		}));
	}

	public void store() {
		// logger.info("store()...");

		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.todoDatabaseFile));

			// write the currentTodoId
			oos.writeInt(this.currentTodoId);
			// then write the todo
			for (Todo todo : todos) {
				oos.writeObject(todo);
			}
		} catch (Exception e) {
			String err = "got exception: " + e;
			logger.error(err, e);
			throw new RuntimeException(e);
		}

		// logger.info("store(): done.");
	}

	public void load() {
		// logger.info("load()...");

		try {
			if (!this.todoDatabaseFile.exists()) {
				logger.warn("the file " + this.todoDatabaseFile
						+ " does not exist yet. Will not try to read anything, but create a default todo list");
				// Todo todo = new Todo(-1,"t1","do something");
				// this.createTodo(todo);
			} else {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.todoDatabaseFile));

				// first we try to read the currentTodoId
				this.currentTodoId = ois.readInt();
				// logger.info("load(): read todoId: " + currentTodoId);

				// then try to read the objects
				Todo obj = null;
				do {
					obj = (Todo) ois.readObject();
					logger.info("load(): read object: " + obj);

					if (obj != null) {
						todos.add(obj);
					}
				} while (true);
			}
		} catch (EOFException eof) {
			logger.info("we have reached the end of the data file");
		} catch (Exception e) {
			String err = "got exception: " + e;
			logger.error(err, e);
			throw new RuntimeException(e);
		}

		// logger.info("load(): todos are: " + todos);
	}

	public boolean deletAll() {
		todos.clear();
		return true;
	}

}
