package com.dailymenurating.repository.interfaces;

import com.dailymenurating.model.User;

public interface UserRepository {

    User findByEmail(String email);

    User findById(Integer id);
}
