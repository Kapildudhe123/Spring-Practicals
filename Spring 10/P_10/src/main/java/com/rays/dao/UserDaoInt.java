package com.rays.dao;

import java.util.List;

import com.rays.dto.UserDTO;

public interface UserDaoInt {

	public long add(UserDTO dto);

	public void update(UserDTO dto);

	public void delete(UserDTO dto);

	public UserDTO findbyPk(long pk);

	public List search(UserDTO dto, int pageNo, int pageSize);

	public UserDTO findByUniqueKey(String attribute, Object val);
}
