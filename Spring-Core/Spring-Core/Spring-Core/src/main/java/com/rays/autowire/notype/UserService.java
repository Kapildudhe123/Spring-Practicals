package com.rays.autowire.notype;

public class UserService {

	private UserDAO userDao;

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	public void testAdd() {
		userDao.add();
	}
}
