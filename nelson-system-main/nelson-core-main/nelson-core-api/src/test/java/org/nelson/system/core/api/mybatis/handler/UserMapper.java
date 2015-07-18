package org.nelson.system.core.api.mybatis.handler;

import org.apache.ibatis.annotations.Param;
import org.joda.time.DateTime;
import org.nelson.system.core.api.mybatis.mapper.CoreMapper;
import org.nelson.system.tools.test.user.User;

public interface UserMapper extends CoreMapper {	
	
	public User findById(@Param("id") Long id);
	
	public void insert(@Param("user") User user);
	
	public void updateBirthDate(@Param("id") Long id, @Param("birthDate") DateTime birthDate);
}
