package com.mycompany.gofarm.user.dao;

import com.mycompany.gofarm.user.dto.UserDTO;

public interface UserDAO {

	public void signupUser(UserDTO dto);
	public void signupdetail(UserDTO dto);
}