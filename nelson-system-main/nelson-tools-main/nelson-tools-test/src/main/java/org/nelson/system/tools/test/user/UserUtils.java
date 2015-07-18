package org.nelson.system.tools.test.user;

public interface UserUtils {
	
	void assertEquals(User expectedUser, User actualUser);
	
	User findUserById(long id);
}
