package com.aybarsacar.expensetrackerapi.services;

import com.aybarsacar.expensetrackerapi.domain.User;
import com.aybarsacar.expensetrackerapi.exceptions.EtAuthException;

public interface UserService {

  User validateUser(String email, String password) throws EtAuthException;

  User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException;

}
