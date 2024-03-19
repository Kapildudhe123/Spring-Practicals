package com.rays.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.UserDaoInt;
import com.rays.dto.UserDTO;

@Controller
@Service
public class UserServiceImple implements UserServiceInte {

	@Autowired
	UserDaoInt dao;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(UserDTO dto) {

		dao.add(dto);

		return 0;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(UserDTO dto) {
		dao.update(dto);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {

		UserDTO dto = findbyId(id);
		dao.delete(dto);

	}

	@Transactional(readOnly = true)
	public UserDTO findbyId(long pk) {

		UserDTO dto = dao.findbyPk(pk);

		return dto;
	}

	@Transactional(readOnly = true)
	public List search(UserDTO dto, int pageNo, int pageSize) {

		List list = dao.search(dto, pageNo, pageSize);
		return list;

	}

	@Transactional(readOnly = true)
	public UserDTO authenticate(String loginId, String password) {

		UserDTO dto = findbyloginId(loginId);

		if (dto != null) {
			if (password.equals(dto.getPassword())) {

				return dto;

			}
		}
		return null;
	}

	@Override
	public UserDTO findbyloginId(String loginId) {
		return dao.findByUniqueKey("loginId", loginId);
	}

}
