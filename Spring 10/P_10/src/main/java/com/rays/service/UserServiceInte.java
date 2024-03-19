package com.rays.service;

import java.util.List;

import com.rays.dto.UserDTO;

public interface UserServiceInte {

	public long add(UserDTO dto);

	public void update(UserDTO dto);

	public void delete(long id);

	public UserDTO findbyId(long pk);
	
	public UserDTO authenticate(String loginId, String password);
	
	public List search(UserDTO dto,int pageNo,int pageSize);
	
	public UserDTO findbyloginId(String loginId);

}
