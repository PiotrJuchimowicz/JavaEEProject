package com.company.JpaDAO;

import com.company.Models.UserDTO;

import java.util.List;

public interface UserJpaDAO {
    void add(UserDTO u);

    UserDTO get(long id);

    void update(UserDTO u);

    void remove(UserDTO u);


    List<UserDTO> findAllUsers();


}
