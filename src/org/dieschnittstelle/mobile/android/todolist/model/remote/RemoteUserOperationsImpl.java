package org.dieschnittstelle.mobile.android.todolist.model.remote;

import org.apache.log4j.Logger;
import org.dieschnittstelle.mobile.android.todolist.model.IUserOperations;
import org.dieschnittstelle.mobile.android.todolist.model.User;

public class RemoteUserOperationsImpl implements IUserOperations {

	protected static Logger logger = Logger.getLogger(RemoteUserOperationsImpl.class);
	
	/**
	 * a kind of highly simplistic authentication implementation
	 */
	@Override
	public boolean authenticateUser(User user) {
		logger.info("authenticateUser(): " + user);
		return "s@bht.de".equals(user.getEmail()) && "000000".equals(user.getPwd());
	}

}
