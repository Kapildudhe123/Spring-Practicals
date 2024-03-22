package com.rays.autowire.byconstructor;

public class UserService {

	private UserDAO userDao;

	public UserService(UserDAO userDao) {
		this.userDao = userDao;
	}

	public void testAdd() {
		userDao.add();
	}
}
